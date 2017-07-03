package ru.evotor.framework.receipt

import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.evotor.framework.inventory.ProductType
import ru.evotor.framework.safeValueOf
import java.math.BigDecimal

object ReceiptApi {
    @Deprecated(message = "Используйте методы API")
    const val AUTHORITY = "ru.evotor.evotorpos.receipt"

    @Deprecated(message = "Используйте методы API. Данная константа останется только для внутреннего использования")
    @JvmField val BASE_URI = Uri.parse("content://$AUTHORITY")

    internal const val AUTHORITY_V2 = "ru.evotor.evotorpos.v2.receipt"
    internal const val RECEIPTS_PATH = "receipts"

    internal val BASE_URI_V2 = Uri.parse("content://$AUTHORITY_V2")
    internal val RECEIPTS_URI = Uri.withAppendedPath(BASE_URI_V2, RECEIPTS_PATH)

    @JvmStatic
    fun getPositionsByBarcode(context: Context, barcode: String): List<Position> {
        val positionsList = ArrayList<Position>()

        val cursor: Cursor? = context.contentResolver.query(
                Uri.withAppendedPath(PositionTable.URI, barcode),
                null, null, null, null)

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    val position: Position = Position(
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_POSITION_UUID)),
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_PRODUCT_UUID)),
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_PRODUCT_CODE)),
                            ProductType.valueOf(cursor.getString(cursor.getColumnIndex(PositionTable.ROW_PRODUCT_TYPE))),
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_NAME)),
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_MEASURE_NAME)),
                            cursor.getInt(cursor.getColumnIndex(PositionTable.ROW_MEASURE_PRECISION)),
                            BigDecimal(cursor.getLong(cursor.getColumnIndex(PositionTable.ROW_PRICE))).divide(BigDecimal(100)),
                            BigDecimal(cursor.getLong(cursor.getColumnIndex(PositionTable.ROW_PRICE))).divide(BigDecimal(100)),
                            BigDecimal(cursor.getLong(cursor.getColumnIndex(PositionTable.ROW_QUANTITY))).divide(BigDecimal(1000)),
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_BARCODE)),
                            null,
                            BigDecimal(cursor.getLong(cursor.getColumnIndex(PositionTable.ROW_ALCOHOL_BY_VOLUME))).divide(BigDecimal(1000)),
                            cursor.getLong(cursor.getColumnIndex(PositionTable.ROW_ALCOHOL_PRODUCT_KIND_CODE)),
                            BigDecimal(cursor.getString(cursor.getColumnIndex(PositionTable.ROW_TARE_VOLUME))).divide(BigDecimal(1000)),
                            null,
                            null
                    )
                    positionsList.add(position)
                }
            } finally {
                cursor.close()
            }
        }

        return positionsList
    }

    @JvmStatic
    fun getReceipt(context: Context, type: Receipt.Type): Receipt? {
        return getReceipt(context, type, null);
    }

    @JvmStatic
    fun getReceipt(context: Context, uuid: String): Receipt? {
        return getReceipt(context, null, uuid);
    }

    fun getReceipt(context: Context, type: Receipt.Type?, uuid: String? = null): Receipt? {
        if (type == null && uuid == null) {
            throw IllegalArgumentException("type or uuid should be not null")
        }
//TODO
        throw NotImplementedError()
    }

    @JvmStatic
    fun getReceiptHeaders(context: Context, type: Receipt.Type? = null): ru.evotor.framework.Cursor<Receipt.Header?>? {
        return context.contentResolver.query(
                RECEIPTS_URI,
                arrayOf(
                        ReceiptHeaderTable.COLUMN_UUID,
                        ReceiptHeaderTable.COLUMN_NUMBER,
                        ReceiptHeaderTable.COLUMN_TYPE
                ),
                type?.let { "${ReceiptHeaderTable.COLUMN_TYPE} = ?" },
                type?.let { arrayOf(it.name) },
                null
        )?.let {
            object : ru.evotor.framework.Cursor<Receipt.Header?>(it) {
                override fun getValue(): Receipt.Header? {
                    return Receipt.Header(
                            getString(getColumnIndex(ReceiptHeaderTable.COLUMN_UUID)),
                            getString(getColumnIndex(ReceiptHeaderTable.COLUMN_NUMBER)),
                            safeValueOf<Receipt.Type>(getString(getColumnIndex(ReceiptHeaderTable.COLUMN_TYPE))) ?: return null
                    )
                }
            }
        }
    }

    @Deprecated(message = "Используйте метод getSellReceipt")
    object Description {

        const val PATH_RECEIPT_DESCRIPTION = "information"

        @JvmField val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_DESCRIPTION)

        const val ROW_ID = "_id"
        const val ROW_UUID = "uuid"
        const val ROW_DISCOUNT = "discount"

    }

    @Deprecated(message = "Используйте метод getSellReceipt")
    object Positions {

        const val PATH_RECEIPT_POSITIONS = "positions"

        @JvmField val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_POSITIONS)

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

        @JvmField val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_PAYMENTS)

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