package ru.evotor.framework.kkt.api

import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.evotor.framework.*
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.startIntegrationService
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Agent
import ru.evotor.framework.counterparties.collaboration.agent_scheme.Subagent
import ru.evotor.framework.kkt.FfdVersion
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags
import ru.evotor.framework.kkt.event.CorrectionReceiptRegistrationRequestedEvent
import ru.evotor.framework.kkt.event.handler.service.KktBacksideIntegrationService
import ru.evotor.framework.kkt.provider.KktContract
import ru.evotor.framework.kkt.provider.KktContract.COLUMN_SESSION_STATUS_CLOSE_DATE
import ru.evotor.framework.kkt.provider.KktContract.COLUMN_SESSION_STATUS_IS_EXPIRED
import ru.evotor.framework.kkt.provider.KktContract.COLUMN_SESSION_STATUS_IS_OPEN
import ru.evotor.framework.kkt.provider.KktContract.COLUMN_SESSION_STATUS_OPEN_DATE
import ru.evotor.framework.kkt.provider.KktContract.COLUMN_SESSION_STATUS_SESSION_NUMBER
import ru.evotor.framework.optBoolean
import ru.evotor.framework.optInt
import ru.evotor.framework.optList
import ru.evotor.framework.optString
import ru.evotor.framework.payment.PaymentType
import ru.evotor.framework.receipt.SettlementType
import ru.evotor.framework.receipt.TaxationSystem
import ru.evotor.framework.receipt.correction.CorrectionType
import ru.evotor.framework.receipt.position.VatRate
import java.math.BigDecimal
import java.util.*

/**
 * Интерфейс для работы с кассой.
 */
object KktApi {

    private val stringGetter: (Cursor, String) -> String? = { cursor, name -> cursor.optString(name) }
    private val booleanGetter: (Cursor, String) -> Boolean? = { cursor, name -> cursor.optBoolean(name) }

    private var fsSerialNumber: String? = null

    /**
     * Получает версию ФФД, на которую была зарегистрирована касса.
     * @return FfdVersion или null, если не удалось связаться с кассой
     * @throws IntegrationLibraryMappingException, если не удалось распознать полученную версию ФФД
     */
    @JvmStatic
    fun getRegisteredFfdVersion(context: Context): FfdVersion? =
        getValue(context, KktContract.COLUMN_SUPPORTED_FFD_VERSION) { cursor, name ->
            cursor.optInt(name)?.let { version ->
                if (version !in 0 until FfdVersion.values().size) {
                    throw IntegrationLibraryMappingException(FfdVersion::class.java.name)
                }
                FfdVersion.values()[version]
            }
        }

    /**
     * Получает список типов агентов, которые были указаны при регистрации кассы.
     * @return List<Agent.Type> или null, если в кассе не зарегистрированы типы агентов, либо если
     * не удалось связаться с кассой.
     * @throws IntegrationLibraryMappingException, если не удалось распознать полученные типы
     * агентов.
     */
    @JvmStatic
    fun getRegisteredAgentTypes(context: Context): List<Agent.Type>? =
        getValue(context, KktContract.COLUMN_REGISTERED_AGENT_TYPES) { cursor, name ->
            cursor.optList(name)?.map { item ->
                item.toInt().let { index ->
                    if (index !in 0..Agent.Type.values().size) {
                        throw IntegrationLibraryMappingException(Agent.Type::class.java.name)
                    }
                    Agent.Type.values()[index]
                }
            }
        }

    /**
     * Получает список типов субагентов, которые были указаны при регистрации кассы.
     * @return List<Subagent.Type> или null, если в кассе не зарегистрированы типы субагентов, либо
     * если не удалось связаться с кассой.
     * @throws IntegrationLibraryMappingException, если не удалось распознать полученные типы
     * субагентов.
     */
    @JvmStatic
    fun getRegisteredSubagentTypes(context: Context): List<Subagent.Type>? =
        getValue(context, KktContract.COLUMN_REGISTERED_SUBAGENT_TYPES) { cursor, name ->
            cursor.optList(name)?.map { item ->
                item.toInt().let { index ->
                    if (index !in 0..Subagent.Type.values().size) {
                        throw IntegrationLibraryMappingException(Subagent.Type::class.java.name)
                    }
                    Subagent.Type.values()[index]
                }
            }
        }

    /**
     * Проверяет, установлен ли на терминал пакет обновлений с возможностью пробивать фискальные документы по
     * ставке НДС 20%.
     * @return Boolean или null, если не удалось связаться с кассой.
     * @throws IntegrationLibraryMappingException, если не удалось распознать полученное значение
     */
    @JvmStatic
    fun isVatRate20Available(context: Context): Boolean? =
        getValue(context, KktContract.COLUMN_IS_VAT_RATE_20_AVAILABLE, booleanGetter)

    /**
     * Возвращает серийный номер ККТ в функцию обратного вызова (асинхронная операция)
     *
     * @param context текущий контекст
     * @param callback функция обратного вызова, получает в качестве параметра серийный номер
     * или null если попытка завершилась неудачей
     */
    @Deprecated("Устаревший с 01.08.19, используйте receiveKktSerialNumber(context: Context): String?")
    @JvmStatic
    fun receiveKktSerialNumber(context: Context, callback: (String?) -> Unit) {
        callback(receiveKktSerialNumber(context))
    }

    /**
     * Возвращает серийный номер ККТ или null если попытка завершилась неудачей,
     * следует вызывать асинхронно
     *
     * @param context текущий контекст
     * @return строку серийный номер или null
     */
    @JvmStatic
    fun receiveKktSerialNumber(context: Context): String? =
        getValue(context, KktContract.COLUMN_SERIAL_NUMBER, stringGetter)

    /**
     * Возвращает регистрационный номер ККТ в функцию обратного вызова (асинхронная операция)
     *
     * @param context текущий контекст
     * @param callback функция обратного вызова, получает в качестве параметра регистрационный номер
     * или null если попытка завершилась неудачей
     */
    @Deprecated("Устаревший с 01.08.19, используйте receiveKktRegNumber(context: Context): String?")
    @JvmStatic
    fun receiveKktRegNumber(context: Context, callback: (String?) -> Unit) {
        callback(receiveKktRegNumber(context))
    }

    /**
     * Возвращает регистрационный номер ККТ или null если попытка завершилась неудачей,
     * следует вызывать асинхронно
     *
     * @param context текущий контекст
     * @return строку регистрационный номер или null
     */
    @JvmStatic
    fun receiveKktRegNumber(context: Context): String? =
        getValue(context, KktContract.COLUMN_REGISTER_NUMBER, stringGetter)

    /**
     * Проверяет, готова ли касса для работы в разъездной торговле.
     * Результатом является логическое "И" всех необходимых условий.
     *
     * @param context текущий контекст
     * @return  true    - если все условия для работы в разъездной торговле выполнены,
     *          false   - если хотя бы одно условие не выполнено
     * @throws IntegrationLibraryMappingException, если не удалось распознать полученное значение
     */
    @JvmStatic
    fun isKktReadyForDelivery(context: Context): Boolean? =
        getValue(context, KktContract.COLUMN_IS_DELIVERY_AVAILABLE, booleanGetter)

    /**
     * Возвращает серийный номер фискального накопителя или null, если фискальный накопитель отсутствует
     * или попытка завершилась неудачей
     *
     * @param context текущий контекст
     * @return серийный номер фискального накопителя или null
     */
    @Deprecated(
        message = "Используйте FsApi.getFsSerialNumber(context)",
        replaceWith = ReplaceWith(expression = "FsApi.getFsSerialNumber(context)", imports = ["ru.evotor.framework.fs.api.FsApi"])
    )
    @JvmStatic
    fun getFsSerialNumber(context: Context): String? {
        if (fsSerialNumber == null) getKktFsInfo(context)

        return fsSerialNumber
    }

    /**
     *
     * Возвращает количество наличности в денежном ящике кассы или null, если не удалось получить данные
     *
     * @param context текущий контекст
     * @return BigDecimal количество наличности в денежном ящике кассы или null, если не удалось получить данные
     */
    @JvmStatic
    fun getCurrentCashSum(context: Context): BigDecimal? {
        val uri = Uri.parse("${KktContract.BASE_URI}${KktContract.PATH_KKT_COUNTERS}")
        return context.contentResolver.query(
            uri,
            arrayOf(KktContract.COLUMN_CURRENT_CASH_SUM),
            null,
            null,
            null
        )?.use { cursor ->
            cursor.moveToFirst()
            cursor.getMoney(KktContract.COLUMN_CURRENT_CASH_SUM)
        }
    }

    /**
     * Печатает чек коррекции.
     * ВАЖНО! Чек коррекции необходимо печатать в промежутке между документом открытия смены и отчётом о закрытии смены.
     * @param context контекст приложения
     * @param settlementType тип (признак) расчета
     * @param taxationSystem применяемая система налогообложения (одна из тех, которые были указаны при регистрации кассы)
     * @param correctionType тип коррекции
     * @param basisForCorrection основание для коррекции
     * @param prescriptionNumber номер предписания налогового органа
     * @param correctableSettlementDate дата совершения корректируемого расчета
     * @param amountPaid уплаченная сумма
     * @param paymentType платёжное средство, использованное для оплаты
     * @param vatRate ставка НДС
     * @param correctionDescription описание коррекции
     * @param callback
     */
    @Deprecated(
        message = "Неприменим для касс, использующих ФФД 1.1 и выше",
        replaceWith = ReplaceWith("PrintCorrectionIncomeReceiptCommand \nPrintCorrectionOutcomeReceiptCommand")
    )
    @JvmStatic
    fun registerCorrectionReceipt(
        context: Context,

        @FiscalRequisite(FiscalTags.SETTLEMENT_TYPE)
        settlementType: SettlementType,

        @FiscalRequisite(FiscalTags.TAXATION_SYSTEM)
        taxationSystem: TaxationSystem,

        @FiscalRequisite(FiscalTags.CORRECTION_TYPE)
        correctionType: CorrectionType,

        @FiscalRequisite(FiscalTags.BASIS_FOR_CORRECTION)
        basisForCorrection: String,

        @FiscalRequisite(FiscalTags.PRESCRIPTION_NUMBER)
        prescriptionNumber: String,

        @FiscalRequisite(FiscalTags.CORRECTABLE_SETTLEMENT_DATE)
        correctableSettlementDate: Date,

        amountPaid: BigDecimal,

        paymentType: PaymentType,

        @FiscalRequisite(FiscalTags.VAT_RATE)
        vatRate: VatRate,

        @FiscalRequisite(FiscalTags.CORRECTION_DESCRIPTION)
        correctionDescription: String,

        callback: DocumentRegistrationCallback
    ) {
        if (correctableSettlementDate >= Date()) {
            return callback.onError(
                DocumentRegistrationException(
                    DocumentRegistrationException.CODE_INVALID_INPUT_DATA,
                    "Указана некорректная дата корректируемого расчёта"
                )
            )
        }
        if (settlementType == SettlementType.RETURN_OF_INCOME || settlementType == SettlementType.RETURN_OF_OUTCOME) {
            return callback.onError(
                DocumentRegistrationException(
                    DocumentRegistrationException.CODE_INVALID_INPUT_DATA,
                    "Указанный тип расчёта не поддерживается"
                )
            )
        }
        if (amountPaid.compareTo(BigDecimal.ZERO) == 0) {
            return callback.onError(
                DocumentRegistrationException(
                    DocumentRegistrationException.CODE_INVALID_INPUT_DATA,
                    "Уплаченная сумма не может быть равной нулю"
                )
            )
        }
        context.startIntegrationService(
            KktBacksideIntegrationService.ACTION_CORRECTION_RECEIPT_REGISTRATION_REQUESTED,
            CorrectionReceiptRegistrationRequestedEvent(
                settlementType,
                taxationSystem,
                correctionType,
                basisForCorrection,
                prescriptionNumber,
                correctableSettlementDate,
                amountPaid,
                paymentType,
                vatRate,
                correctionDescription
            ),
            IntegrationManagerCallback {
                it?.result?.error?.let { error ->
                    callback.onError(DocumentRegistrationException(error.code, error.message))
                } ?: run {
                    callback.onSuccess(null)
                }
            }
        )
    }

    /**
     * Печатает чек коррекции.
     * ВАЖНО! Чек коррекции необходимо печатать в промежутке между документом открытия смены и отчётом о закрытии смены.
     * @param context контекст приложения
     * @param settlementType тип (признак) расчета
     * @param taxationSystem применяемая система налогообложения (одна из тех, которые были указаны при регистрации кассы)
     * @param correctionType тип коррекции
     * @param basisForCorrection основание для коррекции
     * @param prescriptionNumber номер предписания налогового органа
     * @param correctableSettlementDate дата совершения корректируемого расчета
     * @param amountPaid уплаченная сумма
     * @param paymentType платёжное средство, использованное для оплаты
     * @param vatRate ставка НДС
     * @param correctionDescription описание коррекции
     * @param paymentAddress адрес места расчёта
     * @param paymentPlace наименование места расчёта
     * @param callback
     */
    @Deprecated(
        message = "Неприменим для касс, использующих ФФД 1.1 и выше",
        replaceWith = ReplaceWith("PrintCorrectionIncomeReceiptCommand \nPrintCorrectionOutcomeReceiptCommand")
    )
    @JvmStatic
    fun registerCorrectionReceipt(
        context: Context,

        @FiscalRequisite(FiscalTags.SETTLEMENT_TYPE)
        settlementType: SettlementType,

        @FiscalRequisite(FiscalTags.TAXATION_SYSTEM)
        taxationSystem: TaxationSystem,

        @FiscalRequisite(FiscalTags.CORRECTION_TYPE)
        correctionType: CorrectionType,

        @FiscalRequisite(FiscalTags.BASIS_FOR_CORRECTION)
        basisForCorrection: String,

        @FiscalRequisite(FiscalTags.PRESCRIPTION_NUMBER)
        prescriptionNumber: String,

        @FiscalRequisite(FiscalTags.CORRECTABLE_SETTLEMENT_DATE)
        correctableSettlementDate: Date,

        amountPaid: BigDecimal,

        paymentType: PaymentType,

        @FiscalRequisite(FiscalTags.VAT_RATE)
        vatRate: VatRate,

        @FiscalRequisite(FiscalTags.CORRECTION_DESCRIPTION)
        correctionDescription: String,

        @FiscalRequisite(FiscalTags.PAYMENT_ADDRESS)
        paymentAddress: String,

        @FiscalRequisite(FiscalTags.PAYMENT_PLACE)
        paymentPlace: String,

        callback: DocumentRegistrationCallback
    ) {
        if (correctableSettlementDate >= Date()) {
            return callback.onError(
                DocumentRegistrationException(
                    DocumentRegistrationException.CODE_INVALID_INPUT_DATA,
                    "Указана некорректная дата корректируемого расчёта"
                )
            )
        }
        if (settlementType == SettlementType.RETURN_OF_INCOME || settlementType == SettlementType.RETURN_OF_OUTCOME) {
            return callback.onError(
                DocumentRegistrationException(
                    DocumentRegistrationException.CODE_INVALID_INPUT_DATA,
                    "Указанный тип расчёта не поддерживается"
                )
            )
        }
        if (amountPaid.compareTo(BigDecimal.ZERO) == 0) {
            return callback.onError(
                DocumentRegistrationException(
                    DocumentRegistrationException.CODE_INVALID_INPUT_DATA,
                    "Уплаченная сумма не может быть равной нулю"
                )
            )
        }
        context.startIntegrationService(
            KktBacksideIntegrationService.ACTION_CORRECTION_RECEIPT_REGISTRATION_REQUESTED,
            CorrectionReceiptRegistrationRequestedEvent(
                settlementType,
                taxationSystem,
                correctionType,
                basisForCorrection,
                prescriptionNumber,
                correctableSettlementDate,
                amountPaid,
                paymentType,
                vatRate,
                correctionDescription,
                paymentAddress,
                paymentPlace
            ),
            IntegrationManagerCallback {
                it?.result?.error?.let { error ->
                    callback.onError(DocumentRegistrationException(error.code, error.message))
                } ?: run {
                    callback.onSuccess(null)
                }
            }
        )
    }

    @JvmStatic
    fun isSessionOpen(context: Context): Boolean? {
        return getValue(context, KktContract.PATH_SESSION_STATUS) { cursor, name ->
            cursor.optBoolean(name)
        }
    }

    @JvmStatic
    fun isSessionExpired(context: Context): Boolean? {
        return getValue(context, KktContract.PATH_SESSION_STATUS) { cursor, name ->
            cursor.optBoolean(name)
        }
    }

    @JvmStatic
    fun sessionOpenDate(context: Context): Date? {
        return getValue(context, KktContract.COLUMN_SESSION_STATUS_OPEN_DATE) { cursor, name ->
            val dateLong = cursor.optLong(name)
            if(dateLong == null)
                null
            else{
                Date(dateLong)
            }
        }
    }

    @JvmStatic
    fun sessionExpireDate(context: Context): Date? {
        return getValue(context, KktContract.COLUMN_SESSION_STATUS_CLOSE_DATE) { cursor, name ->
            val dateLong = cursor.optLong(name)
            if(dateLong == null)
                null
            else{
                Date(dateLong)
            }
        }
    }

    @JvmStatic
    fun sessionNumber(context: Context): Long? {
        return getValue(context, KktContract.COLUMN_SESSION_STATUS_SESSION_NUMBER) { cursor, name ->
            cursor.optLong(name)
        }
    }

    private fun <T> getValue(context: Context, valueName: String, parser: (Cursor, String) -> T?): T? {
        return context.contentResolver.query(
            KktContract.BASE_URI,
            arrayOf(valueName),
            null,
            null,
            null
        )?.use {
            it.moveToFirst()
            parser(it, valueName) ?: throw IntegrationLibraryMappingException(valueName)
        }
    }

    private fun getKktFsInfo(context: Context) {
        val uri = Uri.parse("${KktContract.BASE_URI}${KktContract.PATH_KKT_FS_INFO}")
        val cursor = context.contentResolver.query(
            uri,
            arrayOf(
                KktContract.COLUMN_FS_SERIAL_NUMBER
            ),
            null,
            null,
            null
        )

        cursor?.use {
            it.moveToFirst()
            fsSerialNumber = it.optString(KktContract.COLUMN_FS_SERIAL_NUMBER)
        }
    }

    private fun getKktInfoStatus(
        context: Context,
        uri: Uri
    ): GetKktSession {
        val cursor = context.contentResolver.query(
            uri,
            arrayOf(
                COLUMN_SESSION_STATUS_IS_OPEN,
                COLUMN_SESSION_STATUS_IS_EXPIRED,
                COLUMN_SESSION_STATUS_OPEN_DATE,
                COLUMN_SESSION_STATUS_CLOSE_DATE,
                COLUMN_SESSION_STATUS_SESSION_NUMBER
            ),
            null,
            null,
            null
        )

        cursor ?: return GetKktSession.Unsupported
        cursor.use {
            return GetKktSession.Success(mapCursorToKktSession(it))
        }
    }

    private fun mapCursorToKktSession(cursor: Cursor): KktSession? {
        if (!cursor.moveToFirst()) {
            return null
        }

        val isOpen: Boolean? = cursor.optBoolean(COLUMN_SESSION_STATUS_IS_OPEN)
        val isExpired: Boolean? = cursor.optBoolean(COLUMN_SESSION_STATUS_IS_EXPIRED)
        val openDate: Long? = cursor.optLong(COLUMN_SESSION_STATUS_OPEN_DATE)
        val closeDate: Long? = cursor.optLong(COLUMN_SESSION_STATUS_CLOSE_DATE)
        val sessionNumber: Int? = cursor.optInt(COLUMN_SESSION_STATUS_SESSION_NUMBER)

        return KktSession(
            isOpen,
            isExpired,
            openDate?.let { Date(it) },
            closeDate?.let { Date(it) },
            sessionNumber
        )
    }

    data class KktSession(
        val isOpen: Boolean?,
        val isExpired: Boolean?,
        val openDate: Date?,
        val closeDate: Date?,
        val sessionNumber: Int?
    )

    sealed class GetKktSession() {
        class Success(val session: KktSession?) : GetKktSession()
        object Unsupported : GetKktSession()
    }
}
