package ru.evotor.framework.core.action.command.print_receipt_command

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.core.ActivityStarter
import ru.evotor.framework.core.ICanStartActivity
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl
import ru.evotor.framework.core.action.datamapper.PrintReceiptMapper
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags
import ru.evotor.framework.receipt.Receipt
import ru.evotor.framework.receipt.correction.CorrectionType
import java.math.BigDecimal
import java.util.*

/**
 * Команда печати чека коррекции прихода.
 * @param printReceipts Список чеков для печати.
 * @param extra Дополнительные данные к чеку.
 * @param clientPhone Телефон клиента.
 * @param clientEmail Электронная почта клиента.
 * @param receiptDiscount Скидка на чек.
 * @param paymentAddress Адрес места расчёта
 * @param paymentPlace Место расчёта
 * @param userUuid Идентификатор сотрудника в формате `uuid4`, от лица которого будет произведена операция. Если передано null, то будет выбран текущий авторизованный сотрудник. @see ru.evotor.framework.users.UserAPI
 * @param correctionDate Дата совершения корректируемого расчета (ТЕГ 1178)
 * @param correctionType Тип коррекции (ТЕГ 1173)
 * @param prescription Номер предписания налогового органа (ТЕГ 1179)
 */
class PrintCorrectionIncomeReceiptCommand(
    val printReceipts: List<Receipt.PrintReceipt>,
    val extra: SetExtra?,
    val clientPhone: String? = null,
    val clientEmail: String? = null,
    val receiptDiscount: BigDecimal? = BigDecimal.ZERO,
    val paymentAddress: String?,
    val paymentPlace: String?,
    val userUuid: String?,
    @FiscalRequisite(tag = FiscalTags.CORRECTABLE_SETTLEMENT_DATE)
    val correctionDate: Date,
    @FiscalRequisite(tag = FiscalTags.CORRECTION_TYPE)
    val correctionType: CorrectionType,
    @FiscalRequisite(tag = FiscalTags.PRESCRIPTION_NUMBER)
    val prescription: String? = null
) : IBundlable {

    fun process(context: Context, callback: IntegrationManagerCallback) {
        IntegrationManagerImpl
            .convertImplicitIntentToExplicitIntent(NAME, context.applicationContext)
            .firstOrNull()
            ?.let { component ->
                IntegrationManagerImpl(context.applicationContext).call(
                    NAME,
                    component,
                    this,
                    ActivityStarter(context),
                    callback,
                    Handler(Looper.getMainLooper())
                )
            }
    }

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putParcelableArrayList(
                KEY_PRINT_RECEIPTS,
                printReceipts.mapTo(
                    ArrayList(),
                    {
                        PrintReceiptMapper.toBundle(it)
                    }
                )
            )
            putBundle(KEY_RECEIPT_EXTRA, extra?.toBundle())
            putString(KEY_CLIENT_EMAIL, clientEmail)
            putString(KEY_CLIENT_PHONE, clientPhone)
            putString(
                KEY_RECEIPT_DISCOUNT,
                receiptDiscount?.toPlainString() ?: BigDecimal.ZERO.toPlainString()
            )
            putString(KEY_PAYMENT_ADDRESS, paymentAddress)
            putString(KEY_PAYMENT_PLACE, paymentPlace)
            putString(KEY_USER_UUID, userUuid)
            putLong(KEY_CORRECTION_DATE, correctionDate.time)
            putString(KEY_CORRECTION_TYPE, correctionType.name)
            putString(KEY_PRESCRIPTION, prescription)
        }
    }

    companion object {

        const val NAME = "evo.v2.receipt.correction.income.printReceipt"

        private const val KEY_PRINT_RECEIPTS = "printReceipts"
        private const val KEY_RECEIPT_EXTRA = "extra"
        private const val KEY_CLIENT_EMAIL = "clientEmail"
        private const val KEY_CLIENT_PHONE = "clientPhone"
        private const val KEY_RECEIPT_DISCOUNT = "receiptDiscount"
        private const val KEY_PAYMENT_ADDRESS = "paymentAddress"
        private const val KEY_PAYMENT_PLACE = "paymentPlace"
        private const val KEY_USER_UUID = "userUuid"
        private const val KEY_CORRECTION_DATE = "correctionDate"
        private const val KEY_CORRECTION_TYPE = "correctionType"
        private const val KEY_PRESCRIPTION = "prescription"

        @JvmStatic
        fun create(bundle: Bundle?): PrintCorrectionIncomeReceiptCommand? {
            return bundle?.let {
                PrintCorrectionIncomeReceiptCommand(
                    printReceipts = PrintReceiptCommand.getPrintReceipts(bundle),
                    extra = PrintReceiptCommand.getSetExtra(bundle),
                    clientPhone = PrintReceiptCommand.getClientPhone(bundle),
                    clientEmail = PrintReceiptCommand.getClientEmail(bundle),
                    receiptDiscount = PrintReceiptCommand.getReceiptDiscount(bundle),
                    paymentAddress = PrintReceiptCommand.getPaymentAddress(bundle),
                    paymentPlace = PrintReceiptCommand.getPaymentPlace(bundle),
                    userUuid = PrintReceiptCommand.getUserUuid(bundle),
                    correctionDate = Date(it.getLong(KEY_CORRECTION_DATE)),
                    correctionType = CorrectionType.valueOf(it.getString(KEY_CORRECTION_TYPE) as String),
                    prescription = it.getString(KEY_PRESCRIPTION)
                )
            }
        }
    }
}
