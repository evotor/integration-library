package ru.evotor.framework.receipt.event

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.PaymentMapper
import ru.evotor.framework.getMoney
import ru.evotor.framework.receipt.Payment
import java.math.BigDecimal

/**
 * Событие полностью завершённой оплаты чека.
 *
 * Происходит после того, как чек был полностью оплачен (это ***НЕ*** означает, что он был фискализован).
 *
 * Обрабатывать это событие можно с помощью следующих широковещательных приёмников:
 * [ru.evotor.framework.receipt.event.handler.receiver.SellReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.PaybackReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuyReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuybackReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.CorrectionIncomeReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.CorrectionOutcomeReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.CorrectionReturnIncomeReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.CorrectionReturnOutcomeReceiptBroadcastReceiver]
 *
 * @param receiptUuid uuid чека
 * @param payments оплаты
 * @param isPaymentIntent является ли оплата оплатой намерением: true - оплата намерением, false - реальная оплата
 */
class ReceiptPaidEvent(receiptUuid: String, val payments: Map<Payment, BigDecimal>, val isPaymentIntent: Boolean) : ReceiptEvent(receiptUuid) {
    override fun toBundle() = super.toBundle().apply {
        val paymentsArray = ArrayList<Bundle>()
        for (paymentEntry in payments) {
            val paymentBundle = PaymentMapper.toBundle(paymentEntry.key)
            paymentsArray.add(
                Bundle().apply {
                    putBundle(KEY_PAYMENT, paymentBundle)
                    putString(KEY_PAYMENT_VALUE, paymentEntry.value.toPlainString())
                }
            )
        }
        putParcelableArrayList(KEY_PAYMENTS, paymentsArray)
        putBoolean(KEY_IS_PAYMENT_INTENT, isPaymentIntent)
    }

    companion object {
        private const val KEY_PAYMENTS = "PAYMENTS"

        private const val KEY_PAYMENT = "PAYMENT"

        private const val KEY_PAYMENT_VALUE = "PAYMENT_VALUE"

        private const val KEY_IS_PAYMENT_INTENT = "IS_PAYMENT_INTENT"

        fun from(bundle: Bundle?): ReceiptPaidEvent? = bundle?.let {
            val uuid = getReceiptUuid(it) ?: return null

            val payments = mutableMapOf<Payment, BigDecimal>()
            val paymentsParcelableList = bundle.getParcelableArrayList<Bundle>(KEY_PAYMENTS) ?: return null
            paymentsParcelableList.forEach { paymentBundle ->
                val payment = PaymentMapper.from(paymentBundle.getBundle(KEY_PAYMENT))
                val paymentValue = paymentBundle.getMoney(KEY_PAYMENT_VALUE)
                if (payment != null && paymentValue != null) {
                    payments[payment] = paymentValue
                }
            }

            val isPaymentIntent = if (it.containsKey(KEY_IS_PAYMENT_INTENT)) {
                it.getBoolean(KEY_IS_PAYMENT_INTENT)
            } else {
                return null
            }

            ReceiptPaidEvent(uuid, payments, isPaymentIntent)
        }
    }
}
