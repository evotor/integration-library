package ru.evotor.framework.receipt.event

import android.os.Bundle

/**
 * Событие отмены всех оплат чека.
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
 * @param isPaymentIntent является ли оплата оплатой намерением: true - оплата намерением, false - реальная оплата
 */
class ReceiptPaymentsCanceledEvent(receiptUuid: String, val isPaymentIntent: Boolean) : ReceiptEvent(receiptUuid) {
    override fun toBundle(): Bundle {
        return super.toBundle().apply {
            putBoolean(KEY_IS_PAYMENT_INTENT, isPaymentIntent)
        }
    }

    companion object {
        private const val KEY_IS_PAYMENT_INTENT = "IS_PAYMENT_INTENT"

        fun from(bundle: Bundle?): ReceiptPaymentsCanceledEvent? = bundle?.let {
            val isPaymentIntent = if (it.containsKey(KEY_IS_PAYMENT_INTENT)) {
                it.getBoolean(KEY_IS_PAYMENT_INTENT)
            } else {
                return null
            }
            ReceiptPaymentsCanceledEvent(getReceiptUuid(it) ?: return null, isPaymentIntent)
        }
    }
}
