package ru.evotor.framework.core.action.command.print_receipt_command

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.calculator.MoneyCalculator
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl
import ru.evotor.framework.core.action.datamapper.PrintReceiptMapper
import ru.evotor.framework.core.action.datamapper.getMoney
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra
import ru.evotor.framework.min
import ru.evotor.framework.payment.PaymentType
import ru.evotor.framework.receipt.Payment
import ru.evotor.framework.receipt.Receipt
import ru.evotor.framework.sumByBigDecimal
import java.math.BigDecimal
import java.util.*

/**
 * Команда печати чека продажи
 * @param printReceipts Список печатных чеков
 * @param extra Экстра данные к чеку
 * @param clientPhone Телефон клиента
 * @param clientEmail Эл.почта клиента
 * @param receiptDiscount Скидка на чек
 */
abstract class PrintReceiptCommand(
        val printReceipts: List<Receipt.PrintReceipt>,
        val extra: SetExtra?,
        val clientPhone: String?,
        val clientEmail: String?,
        val receiptDiscount: BigDecimal?
) : IBundlable {

    internal fun process(activity: Activity, callback: IntegrationManagerCallback, action: String) {
        val componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(action, activity.applicationContext)
        if (componentNameList == null || componentNameList.isEmpty()) {
            return
        }
        IntegrationManagerImpl(activity.applicationContext)
                .call(action,
                        componentNameList[0],
                        this,
                        activity,
                        callback,
                        Handler(Looper.getMainLooper())
                )
    }

    override fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putParcelableArrayList(KEY_PRINT_RECEIPTS, printReceipts.mapTo(ArrayList(), { PrintReceiptMapper.toBundle(it) }))
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra?.toBundle())
        bundle.putString(KEY_CLIENT_EMAIL, clientEmail)
        bundle.putString(KEY_CLIENT_PHONE, clientPhone)
        bundle.putString(KEY_RECEIPT_DISCOUNT, receiptDiscount?.toPlainString() ?: BigDecimal.ZERO.toPlainString())
        return bundle
    }

    companion object {

        const val NAME_PERMISSION = "ru.evotor.permission.receipt.print.INTERNET_RECEIPT"
        private const val KEY_PRINT_RECEIPTS = "printReceipts"
        private const val KEY_RECEIPT_EXTRA = "extra"
        private const val KEY_CLIENT_EMAIL = "clientEmail"
        private const val KEY_CLIENT_PHONE = "clientPhone"
        private const val KEY_RECEIPT_DISCOUNT = "receiptDiscount"

        internal fun getPrintReceipts(bundle: Bundle): List<Receipt.PrintReceipt> {
            return bundle.getParcelableArrayList<Bundle>(KEY_PRINT_RECEIPTS)
                    .map { PrintReceiptMapper.from(it) }
                    .filterNotNull()
        }

        internal fun getSetExtra(bundle: Bundle): SetExtra? {
            return SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA))
        }

        internal fun getClientPhone(bundle: Bundle): String? {
            return bundle.getString(KEY_CLIENT_PHONE, null)
        }

        internal fun getClientEmail(bundle: Bundle): String? {
            return bundle.getString(KEY_CLIENT_EMAIL, null)
        }

        internal fun getReceiptDiscount(bundle: Bundle): BigDecimal? {
            return bundle.getMoney(KEY_RECEIPT_DISCOUNT, BigDecimal.ZERO)
        }

        internal fun calculateChanges(sum: BigDecimal, payments: List<Payment>): Map<Payment, BigDecimal> {
            var remaining = MoneyCalculator.subtract(payments.sumByBigDecimal { it.value }, sum)
            val result = HashMap<Payment, BigDecimal>()
            for (payment in payments) {
                if (payment.system?.paymentType != PaymentType.CASH) {
                    result.put(payment, BigDecimal.ZERO)
                    continue
                }

                val change = min(payment.value, remaining)
                remaining = MoneyCalculator.subtract(remaining, change)
                result.put(payment, change)
            }

            return result
        }
    }
}