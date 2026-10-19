package ru.evotor.framework.receipt.event

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.PaymentMapper
import ru.evotor.framework.getMoney
import ru.evotor.framework.receipt.Payment
import java.math.BigDecimal

/**
 * Событие завершённой конкретной оплаты чека (картой, наличными и т.д.).
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
 * @param payment оплата
 */
class ReceiptPaymentPaidEvent(receiptUuid: String, val payment: Payment, val isPaymentIntent: Boolean) : ReceiptEvent(receiptUuid) {
    override fun toBundle(): Bundle {
        return super.toBundle().apply {
            putBundle(KEY_PAYMENT, PaymentMapper.toBundle(payment))
            putBoolean(KEY_IS_PAYMENT_INTENT, isPaymentIntent)
        }
    }

    companion object {
        private const val KEY_PAYMENT = "PAYMENT"

        private const val KEY_IS_PAYMENT_INTENT = "IS_PAYMENT_INTENT"

        fun from(bundle: Bundle?): ReceiptPaymentPaidEvent? = bundle?.let {
            val uuid = getReceiptUuid(it) ?: return null
            val isPaymentIntent = if (it.containsKey(KEY_IS_PAYMENT_INTENT)) {
                it.getBoolean(KEY_IS_PAYMENT_INTENT)
            } else {
                return null
            }
            val payment = bundle.getBundle(KEY_PAYMENT)?.let(PaymentMapper::from) ?: return null
            ReceiptPaymentPaidEvent(uuid, payment, isPaymentIntent)
        }
    }
}
