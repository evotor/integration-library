package ru.evotor.framework.core.action.command.pay_and_print_receipt_command

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable
import ru.evotor.framework.component.PaymentDelegator
import ru.evotor.framework.component.PaymentPerformer
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.IntegrationManagerImpl

/**
 * Команда запуска процесса оплаты и печати текущего чека
 * @param receiptUuid Уникальный идентификатор чека (должен совпадать с текущим)
 * @param paymentPerformer Интеграционный компонент приложения, осуществляющий оплату
 * @param paymentDelegator Интеграционный компонент приложения, осуществляющий
 * делегирование оплаты другим платежным приложениям (a.k.a. компонент приложения комбооплаты)
 *
 * Если указаны и paymentPerformer, и paymentDelegator одновременно, процесс вызовется
 * только с применением данных paymentDelegator'а
 */
abstract class PayAndPrintReceiptCommand(
        val receiptUuid: String,
        val paymentPerformer: PaymentPerformer?,
        val paymentDelegator: PaymentDelegator?
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
        bundle.putString(KEY_RECEIPT_UUID, receiptUuid)
        bundle.putParcelable(KEY_PAYMENT_PERFORMER, paymentPerformer)
        bundle.putParcelable(KEY_PAYMENT_DELEGATOR, paymentDelegator)
        return bundle
    }

    companion object {
        const val NAME_PERMISSION = "ru.evotor.permission.receipt.print.PAYMENT_PROCESS"

        private const val KEY_RECEIPT_UUID = "receiptUuid"
        private const val KEY_PAYMENT_PERFORMER = "paymentPerformer"
        private const val KEY_PAYMENT_DELEGATOR = "paymentDelegator"

        internal fun getReceiptUuid(bundle: Bundle): String? = bundle.getString(KEY_RECEIPT_UUID)
        internal fun getPaymentPerformer(bundle: Bundle): PaymentPerformer? = bundle.getParcelable<PaymentPerformer?>(KEY_PAYMENT_PERFORMER)
        internal fun getPaymentDelegator(bundle: Bundle): PaymentDelegator? = bundle.getParcelable<PaymentDelegator?>(KEY_PAYMENT_DELEGATOR)
    }
}