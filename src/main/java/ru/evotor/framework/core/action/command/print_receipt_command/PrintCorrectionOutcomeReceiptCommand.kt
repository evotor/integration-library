package ru.evotor.framework.core.action.command.print_receipt_command

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.core.ICanStartActivity
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl
import ru.evotor.framework.core.action.datamapper.PrintReceiptMapper
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra
import ru.evotor.framework.receipt.Receipt
import java.math.BigDecimal
import java.util.*

/**
 * Команда печати чека коррекции расхода.
 * @param printReceipts Список чеков для печати.
 * @param extra Дополнительные данные к чеку.
 * @param clientPhone Телефон клиента.
 * @param clientEmail Электронная почта клиента.
 * @param receiptDiscount Скидка на чек.
 * @param paymentAddress Адрес места расчёта
 * @param paymentPlace Место расчёта
 * @param userUuid Идентификатор сотрудника в формате `uuid4`, от лица которого будет произведена операция. Если передано null, то будет выбран текущий авторизованный сотрудник. @see ru.evotor.framework.users.UserAPI
 */
class PrintCorrectionOutcomeReceiptCommand(
    val printReceipts: List<Receipt.PrintReceipt>,
    val extra: SetExtra?,
    val clientPhone: String? = null,
    val clientEmail: String? = null,
    val receiptDiscount: BigDecimal? = BigDecimal.ZERO,
    val paymentAddress: String?,
    val paymentPlace: String?,
    val userUuid: String?
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
                    ICanStartActivity {
                        context.startActivity(
                            it.apply {
                                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            }
                        )
                    },
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
        }
    }

    companion object {

        const val NAME = "evo.v2.receipt.correction.outcome.printReceipt"

        private const val KEY_PRINT_RECEIPTS = "printReceipts"
        private const val KEY_RECEIPT_EXTRA = "extra"
        private const val KEY_CLIENT_EMAIL = "clientEmail"
        private const val KEY_CLIENT_PHONE = "clientPhone"
        private const val KEY_RECEIPT_DISCOUNT = "receiptDiscount"
        private const val KEY_PAYMENT_ADDRESS = "paymentAddress"
        private const val KEY_PAYMENT_PLACE = "paymentPlace"
        private const val KEY_USER_UUID = "userUuid"

        @JvmStatic
        fun create(bundle: Bundle?): PrintCorrectionOutcomeReceiptCommand? {
            return bundle?.let {
                PrintCorrectionOutcomeReceiptCommand(
                    printReceipts = PrintReceiptCommand.getPrintReceipts(bundle),
                    extra = PrintReceiptCommand.getSetExtra(bundle),
                    clientPhone = PrintReceiptCommand.getClientPhone(bundle),
                    clientEmail = PrintReceiptCommand.getClientEmail(bundle),
                    receiptDiscount = PrintReceiptCommand.getReceiptDiscount(bundle),
                    paymentAddress = PrintReceiptCommand.getPaymentAddress(bundle),
                    paymentPlace = PrintReceiptCommand.getPaymentPlace(bundle),
                    userUuid = PrintReceiptCommand.getUserUuid(bundle)
                )
            }
        }
    }
}