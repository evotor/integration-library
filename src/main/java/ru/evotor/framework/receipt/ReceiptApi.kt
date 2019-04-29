package ru.evotor.framework.receipt

import android.content.Context
import android.database.Cursor
import android.net.Uri
import org.json.JSONArray
import ru.evotor.framework.component.PaymentPerformer
import ru.evotor.framework.component.PaymentPerformerTable
import ru.evotor.framework.inventory.AttributeValue
import ru.evotor.framework.inventory.ProductType
import ru.evotor.framework.optLong
import ru.evotor.framework.optString
import ru.evotor.framework.payment.PaymentSystem
import ru.evotor.framework.payment.PaymentSystemTable
import ru.evotor.framework.payment.PaymentType
import ru.evotor.framework.receipt.ReceiptDiscountTable.DISCOUNT_COLUMN_NAME
import ru.evotor.framework.receipt.ReceiptDiscountTable.POSITION_DISCOUNT_UUID_COLUMN_NAME
import ru.evotor.framework.receipt.mapper.FiscalReceiptMapper
import ru.evotor.framework.receipt.position.mapper.AgentRequisitesMapper
import ru.evotor.framework.receipt.position.SettlementMethod
import ru.evotor.framework.receipt.position.mapper.SettlementMethodMapper
import ru.evotor.framework.receipt.provider.FiscalReceiptContract
import ru.evotor.framework.safeValueOf
import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

object ReceiptApi {
    @Deprecated(message = "Используйте методы API")
    const val AUTHORITY = "ru.evotor.evotorpos.receipt"

    @Deprecated(message = "Используйте методы API. Данная константа будет удалена")
    @JvmField
    val BASE_URI = Uri.parse("content://$AUTHORITY")

    private const val AUTHORITY_V2 = "ru.evotor.evotorpos.v2.receipt"
    private const val RECEIPTS_PATH = "receipts"
    private const val CURRENT_SELL_PATH = "sell"
    private const val CURRENT_PAYBACK_PATH = "payback"
    private const val CURRENT_BUY_PATH = "buy"
    private const val CURRENT_BUYBACK_PATH = "buyback"
    private const val POSITIONS_PATH = "positions"
    private const val PAYMENTS_PATH = "payments"
    private const val DISCOUNTS_PATH = "discounts"

    private val BASE_URI_V2 = Uri.parse("content://$AUTHORITY_V2")
    private val RECEIPTS_URI = Uri.withAppendedPath(BASE_URI_V2, RECEIPTS_PATH)
    private val CURRENT_SELL_RECEIPT_URI = Uri.withAppendedPath(BASE_URI_V2, CURRENT_SELL_PATH)
    private val CURRENT_PAYBACK_RECEIPT_URI = Uri.withAppendedPath(BASE_URI_V2, CURRENT_PAYBACK_PATH)
    private val CURRENT_BUY_RECEIPT_URI = Uri.withAppendedPath(BASE_URI_V2, CURRENT_BUY_PATH)
    private val CURRENT_BUYBACK_RECEIPT_URI = Uri.withAppendedPath(BASE_URI_V2, CURRENT_BUYBACK_PATH)

    @JvmStatic
    fun getPositionsByBarcode(context: Context, barcode: String): List<Position> {
        val positionsList = ArrayList<Position>()

        val cursor: Cursor? = context.contentResolver.query(
                Uri.withAppendedPath(PositionTable.URI, barcode),
                null, null, null, null)

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    createPosition(cursor)?.let { positionsList.add(it) }
                }
            } finally {
                cursor.close()
            }
        }

        return positionsList
    }

    /**
     * Получить текущий открытый чек.
     * @param context контекст приложения
     * @param type тип чека
     * @return чек или null, если чек закрыт
     */
    @JvmStatic
    fun getReceipt(context: Context, type: Receipt.Type): Receipt? {
        return getReceipt(context, type, null)
    }

    /**
     * Получить чек по uuid. Чек может быть уже закрыт
     * @param context контекст приложения
     * @param uuid uuid чека
     * @return чек или null, если чек не найден
     */
    @JvmStatic
    fun getReceipt(context: Context, uuid: String): Receipt? {
        return getReceipt(context, null, uuid)
    }

    private fun getReceipt(context: Context, type: Receipt.Type?, uuid: String? = null): Receipt? {
        if (type == null && uuid == null) {
            throw IllegalArgumentException("type or uuid should be not null")
        }

        val baseUri = when (type) {
            Receipt.Type.SELL -> CURRENT_SELL_RECEIPT_URI
            Receipt.Type.PAYBACK -> CURRENT_PAYBACK_RECEIPT_URI
            Receipt.Type.BUY -> CURRENT_BUY_RECEIPT_URI
            Receipt.Type.BUYBACK -> CURRENT_BUYBACK_RECEIPT_URI
            else -> Uri.withAppendedPath(RECEIPTS_URI, uuid)
        }

        val header = context.contentResolver.query(
                baseUri,
                null,
                null,
                null,
                null
        )?.use {
            if (it.moveToNext()) {
                return@use createReceiptHeader(it)
            } else {
                return null
            }
        } ?: return null

        val printGroups = HashSet<PrintGroup?>()
        val getPositionResults = ArrayList<GetPositionResult>()
        val getSubpositionResults = ArrayList<GetSubpositionResult>()
        context.contentResolver.query(
                Uri.withAppendedPath(baseUri, POSITIONS_PATH),
                null,
                null,
                null,
                null
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                createGetPositionResult(cursor)?.let {
                    getPositionResults.add(it)
                    printGroups.add(it.printGroup)
                } ?: createGetSubpositionResult(cursor)?.let {
                    getSubpositionResults.add(it)
                }
            }
        }

        for (getPositionResult in getPositionResults) {
            val subpositions = getSubpositionResults
                    .filter { it.parentUuid == getPositionResult.position.uuid }
                    .map { it.position }
            getPositionResult.position = Position.Builder
                    .copyFrom(getPositionResult.position)
                    .setSubPositions(subpositions)
                    .build()
        }

        val getPaymentsResults = ArrayList<GetPaymentsResult>()
        context.contentResolver.query(
                Uri.withAppendedPath(baseUri, PAYMENTS_PATH),
                null,
                null,
                null,
                null
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                createGetPaymentResult(cursor)?.let {
                    getPaymentsResults.add(it)
                    printGroups.add(it.printGroup)
                }
            }
        }

        val receiptDiscount = try {
            val discountMap = HashMap<String, BigDecimal>()

            context.contentResolver.query(
                    Uri.withAppendedPath(baseUri, DISCOUNTS_PATH),
                    null,
                    null,
                    null,
                    null
            )?.use { cursor ->
                while (cursor.moveToNext()) {
                    val posDiscountUuid = cursor.getString(cursor.getColumnIndex(POSITION_DISCOUNT_UUID_COLUMN_NAME))
                    val discount = BigDecimal(cursor.getLong(cursor.getColumnIndex(DISCOUNT_COLUMN_NAME)))
                            .divide(BigDecimal(100))

                    discountMap[posDiscountUuid] = discount
                }
            }

            discountMap
        } catch (error: IllegalArgumentException) {
            //old version of evopos, does not support discounts
            error.printStackTrace()
            null
        }

        val printDocuments = ArrayList<Receipt.PrintReceipt>()
        val groupByPrintGroupPaymentResults = getPaymentsResults
                .groupBy { it.printGroup }
        for (printGroup in printGroups) {
            val payments = groupByPrintGroupPaymentResults[printGroup]?.associateBy { it.payment }
                    ?: HashMap<Payment, ReceiptApi.GetPaymentsResult>()
            printDocuments.add(Receipt.PrintReceipt(
                    printGroup,
                    getPositionResults
                            .filter { it.printGroup == printGroup }
                            .map { it.position },
                    payments.mapValues { it.value.value },
                    payments.mapValues { it.value.change },
                    receiptDiscount
            ))
        }

        return Receipt(
                header,
                printDocuments
        )
    }

    /**
     * Запрос списка заголовков чека
     * @param context контекст приложения
     * @param type фильтр по типу чека
     * @return курсор с заголовками чека
     */
    @JvmStatic
    fun getReceiptHeaders(context: Context, type: Receipt.Type? = null): ru.evotor.query.Cursor<Receipt.Header?>? {
        return context.contentResolver.query(
                RECEIPTS_URI,
                null,
                type?.let { "${ReceiptHeaderTable.COLUMN_TYPE} = ?" },
                type?.let { arrayOf(it.name) },
                null
        )?.let {
            object : ru.evotor.query.Cursor<Receipt.Header?>(it) {
                override fun getValue(): Receipt.Header? {
                    return createReceiptHeader(this)
                }
            }
        }
    }

    /**
     * Получить фискальные чеки по идентификатору ["чека"][ru.evotor.framework.receipt.Receipt].
     * @param context контекст приложения
     * @param receiptUuid uuid ["чека"][ru.evotor.framework.receipt.Receipt]
     */
    @JvmStatic
    fun getFiscalReceipts(context: Context, receiptUuid: String): ru.evotor.query.Cursor<FiscalReceipt>? =
            context.contentResolver.query(FiscalReceiptContract.URI, null, null, arrayOf(receiptUuid), null)
                    ?.let {
                        object : ru.evotor.query.Cursor<FiscalReceipt>(it) {
                            override fun getValue(): FiscalReceipt = FiscalReceiptMapper.read(this)
                        }
                    }


    private fun createGetPositionResult(cursor: Cursor): GetPositionResult? {
        return if (cursor.getString(cursor.getColumnIndex(PositionTable.COLUMN_PARENT_POSITION_UUID)) == null)
            GetPositionResult(
                    createPosition(cursor) ?: return null,
                    createPrintGroup(cursor)
            )
        else
            null
    }

    private fun createGetSubpositionResult(cursor: Cursor): GetSubpositionResult? {
        val parentUuid = cursor.getString(cursor.getColumnIndex(PositionTable.COLUMN_PARENT_POSITION_UUID))
        return if (parentUuid != null)
            GetSubpositionResult(
                    createPosition(cursor) ?: return null,
                    parentUuid
            )
        else
            null
    }

    private fun createGetPaymentResult(cursor: Cursor): GetPaymentsResult? {
        return GetPaymentsResult(
                createPayment(cursor) ?: return null,
                createPrintGroup(cursor),
                BigDecimal(cursor.getLong(cursor.getColumnIndex(PaymentTable.COLUMN_VALUE_BY_PRINT_GROUP))).divide(BigDecimal(100)),
                BigDecimal(cursor.getLong(cursor.getColumnIndex(PaymentTable.COLUMN_CHANGE_BY_PRINT_GROUP))).divide(BigDecimal(100))
        )
    }

    private fun createPrintGroup(cursor: Cursor): PrintGroup? {
        return PrintGroup(
                cursor.getString(cursor.getColumnIndex(PrintGroupSubTable.COLUMN_IDENTIFIER))
                        ?: return null,
                safeValueOf<PrintGroup.Type>(cursor.getString(cursor.getColumnIndex(PrintGroupSubTable.COLUMN_TYPE))),
                cursor.getString(cursor.getColumnIndex(PrintGroupSubTable.COLUMN_ORG_NAME)),
                cursor.getString(cursor.getColumnIndex(PrintGroupSubTable.COLUMN_ORG_INN)),
                cursor.getString(cursor.getColumnIndex(PrintGroupSubTable.COLUMN_ORG_ADDRESS)),
                safeValueOf<TaxationSystem>(cursor.getString(cursor.getColumnIndex(PrintGroupSubTable.COLUMN_TAXATION_SYSTEM))),
                cursor.getInt(cursor.getColumnIndex(PrintGroupSubTable.COLUMN_SHOULD_PRINT_RECEIPT)) == 1
        )
    }

    private fun createPosition(cursor: Cursor): Position? {
        val price = BigDecimal(cursor.getLong(cursor.getColumnIndex(PositionTable.COLUMN_PRICE))).divide(BigDecimal(100))
        val priceWithDiscountPosition = if (cursor.getColumnIndex(PositionTable.COLUMN_PRICE_WITH_DISCOUNT_POSITION) != -1) {
            BigDecimal(cursor.getLong(cursor.getColumnIndex(PositionTable.COLUMN_PRICE_WITH_DISCOUNT_POSITION))).divide(BigDecimal(100))
        } else {
            price
        }
        val extraKeys = cursor.optString(PositionTable.COLUMN_EXTRA_KEYS)?.let {
            createExtraKeysFromDBFormat(cursor.optString(cursor.getColumnIndex(PositionTable.COLUMN_EXTRA_KEYS)))
        }
        val attributes = cursor.optString(PositionTable.COLUMN_ATTRIBUTES)?.let {
            createAttributesFromDBFormat(cursor.optString(cursor.getColumnIndex(PositionTable.COLUMN_ATTRIBUTES)))
        }
        val builder = Position.Builder
                .copyFrom(Position(
                        cursor.getString(cursor.getColumnIndex(PositionTable.COLUMN_POSITION_UUID)),
                        cursor.getString(cursor.getColumnIndex(PositionTable.COLUMN_PRODUCT_UUID)),
                        cursor.getString(cursor.getColumnIndex(PositionTable.COLUMN_PRODUCT_CODE)),
                        ProductType.valueOf(cursor.getString(cursor.getColumnIndex(PositionTable.COLUMN_PRODUCT_TYPE))),
                        cursor.getString(cursor.getColumnIndex(PositionTable.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(PositionTable.COLUMN_MEASURE_NAME)),
                        cursor.getInt(cursor.getColumnIndex(PositionTable.COLUMN_MEASURE_PRECISION)),
                        cursor.optString(PositionTable.COLUMN_TAX_NUMBER)?.let { TaxNumber.valueOf(cursor.getString(cursor.getColumnIndex(PositionTable.COLUMN_TAX_NUMBER))) },
                        price,
                        priceWithDiscountPosition,
                        BigDecimal(cursor.getLong(cursor.getColumnIndex(PositionTable.COLUMN_QUANTITY))).divide(BigDecimal(1000)),
                        cursor.optString(cursor.getColumnIndex(PositionTable.COLUMN_BARCODE)),
                        cursor.optString(PositionTable.COLUMN_MARK)?.let { cursor.getString(cursor.getColumnIndex(PositionTable.COLUMN_MARK)) },
                        cursor.getLong(cursor.getColumnIndex(PositionTable.COLUMN_ALCOHOL_BY_VOLUME)).let { BigDecimal(it).divide(BigDecimal(1000)) },
                        cursor.getLong(cursor.getColumnIndex(PositionTable.COLUMN_ALCOHOL_PRODUCT_KIND_CODE)),
                        cursor.getLong(cursor.getColumnIndex(PositionTable.COLUMN_TARE_VOLUME)).let { BigDecimal(it).divide(BigDecimal(1000)) },
                        extraKeys,
                        emptyList()
                ))
                .setAttributes(attributes)
                .setAgentRequisites(AgentRequisitesMapper.read(cursor))
                .setSettlementMethod(SettlementMethodMapper.fromCursor(cursor))
        return builder.build()
    }

    private fun createAttributesFromDBFormat(value: String?): Map<String, AttributeValue> {
        if (value == null) return emptyMap()
        val array = JSONArray(value)
        return (0 until array.length()).toList()
                .map { array.getJSONObject(it) }
                .map {
                    val attributeUuid = it.optString(PositionTable.AttributeJSONKeys.DICTIONARY_UUID)
                    attributeUuid to AttributeValue(
                            attributeUuid,
                            it.optString(PositionTable.AttributeJSONKeys.DICTIONARY_NAME),
                            it.optString(PositionTable.AttributeJSONKeys.UUID),
                            it.optString(PositionTable.AttributeJSONKeys.NAME)
                    )
                }.toMap()
    }

    private fun createPayment(cursor: Cursor): Payment? {
        val identifierColumnIndex = cursor.getColumnIndex(PaymentTable.COLUMN_IDENTIFIER)
        val identifier = if (identifierColumnIndex != -1) {
            cursor.getString(identifierColumnIndex)
        } else {
            null
        }

        return Payment(
                cursor.getString(cursor.getColumnIndex(PaymentTable.COLUMN_UUID)),
                BigDecimal(cursor.getLong(cursor.getColumnIndex(PaymentTable.COLUMN_VALUE))).divide(BigDecimal(100)),
                createPaymentSystem(cursor),
                createPaymentPerformer(cursor) ?: return null,
                cursor.getString(cursor.getColumnIndex(PaymentTable.COLUMN_PURPOSED_IDENTIFIER)),
                cursor.getString(cursor.getColumnIndex(PaymentTable.COLUMN_ACCOUNT_ID)),
                cursor.getString(cursor.getColumnIndex(PaymentTable.COLUMN_ACCOUNT_USER_DESCRIPTION)),
                identifier)
    }

    private fun createPaymentPerformer(cursor: Cursor): PaymentPerformer? {
        return PaymentPerformer(
                createPaymentSystem(cursor) ?: return null,
                cursor.getString(cursor.getColumnIndex(PaymentPerformerTable.COLUMN_PACKAGE_NAME)),
                cursor.getString(cursor.getColumnIndex(PaymentPerformerTable.COLUMN_COMPONENT_NAME)),
                cursor.getString(cursor.getColumnIndex(PaymentPerformerTable.COLUMN_APP_UUID)),
                cursor.getString(cursor.getColumnIndex(PaymentPerformerTable.COLUMN_APP_NAME))
        )
    }

    private fun createPaymentSystem(cursor: Cursor): PaymentSystem? {
        return PaymentSystem(
                safeValueOf<PaymentType>(cursor.getString(cursor.getColumnIndex(PaymentSystemTable.COLUMN_PAYMENT_TYPE)), null)
                        ?: return null,
                cursor.getString(cursor.getColumnIndex(PaymentSystemTable.COLUMN_PAYMENT_SYSTEM_USER_DESCRIPTION))
                        ?: return null,
                cursor.getString(cursor.getColumnIndex(PaymentSystemTable.COLUMN_PAYMENT_SYSTEM_ID))
                        ?: return null
        )
    }

    private fun createExtraKeysFromDBFormat(value: String?): Set<ExtraKey> {
        val result = HashSet<ExtraKey>()
        value ?: return result
        val jsonExtraKeys = JSONArray(value)
        for (i in 0 until jsonExtraKeys.length()) {
            jsonExtraKeys.getJSONObject(i).let {
                result.add(ExtraKey(
                        it.optString(PositionTable.ExtraKeyJSONKeys.KEY_IDENTITY),
                        it.optString(PositionTable.ExtraKeyJSONKeys.KEY_APP_ID),
                        it.optString(PositionTable.ExtraKeyJSONKeys.KEY_DESCRIPTION)
                ))
            }
        }

        return result
    }

    private fun createReceiptHeader(cursor: Cursor): Receipt.Header? {
        val extraIndex = cursor.getColumnIndex(ReceiptHeaderTable.COLUMN_EXTRA)
        val extra = if (extraIndex == -1) null else cursor.getString(extraIndex)

        return Receipt.Header(
                cursor.getString(cursor.getColumnIndex(ReceiptHeaderTable.COLUMN_UUID)),
                cursor.getString(cursor.getColumnIndex(ReceiptHeaderTable.COLUMN_NUMBER)),
                safeValueOf<Receipt.Type>(cursor.getString(cursor.getColumnIndex(ReceiptHeaderTable.COLUMN_TYPE)))
                        ?: return null,
                cursor.optLong(ReceiptHeaderTable.COLUMN_DATE)?.let { Date(it) },
                cursor.optString(ReceiptHeaderTable.COLUMN_CLIENT_EMAIL),
                cursor.optString(ReceiptHeaderTable.COLUMN_CLIENT_PHONE),
                extra
        )
    }

    private data class GetPositionResult(var position: Position, val printGroup: PrintGroup?)
    private data class GetSubpositionResult(val position: Position, val parentUuid: String?)
    private data class GetPaymentsResult(val payment: Payment, val printGroup: PrintGroup?, val value: BigDecimal, val change: BigDecimal)

    @Deprecated(message = "Используйте метод getSellReceipt")
    object Description {

        const val PATH_RECEIPT_DESCRIPTION = "information"

        @JvmField
        val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_DESCRIPTION)

        const val ROW_ID = "_id"
        const val ROW_UUID = "uuid"
        const val ROW_DISCOUNT = "discount"

    }

    @Deprecated(message = "Используйте метод getSellReceipt")
    object Positions {

        const val PATH_RECEIPT_POSITIONS = "positions"

        @JvmField
        val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_POSITIONS)

        const val ROW_UUID = "uuid"
        const val ROW_PRODUCT_UUID = "productUuid"
        const val ROW_TYPE = "type"
        const val ROW_CODE = "code"
        const val ROW_MEASURE_NAME = "measureName"
        const val ROW_MEASURE_PRECISION = "measurePrecision"
        const val ROW_PRICE = "price"
        const val ROW_QUANTITY = "quantity"
        const val ROW_MARK = "mark"
        const val ROW_NAME = "name"
    }

    @Deprecated(message = "Используйте метод getSellReceipt")
    object Payments {

        const val PATH_RECEIPT_PAYMENTS = "payments"

        @JvmField
        val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_PAYMENTS)

        const val ROW_ID = "_id"
        const val ROW_UUID = "uuid"
        const val ROW_SUM = "sum"
        const val ROW_TYPE = "type"
        const val ROW_RRN = "rrn"
        const val ROW_PIN_PAD_UUID = "pin_pad_uuid"

        object Type {
            const val TYPE_CASH = 0
            const val TYPE_CARD = 1
        }

    }
}