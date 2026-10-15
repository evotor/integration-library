package ru.evotor.framework.receipt.event

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.PaymentMapper
import ru.evotor.framework.receipt.Payment

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
class ReceiptPaymentPerformedEvent(receiptUuid: String, val payment: Payment) : ReceiptEvent(receiptUuid) {
    override fun toBundle(): Bundle {
        return super.toBundle().apply {
            putBundle(KEY_PAYMENT, PaymentMapper.toBundle(payment))
        }
    }

    companion object {
        private const val KEY_PAYMENT = "PAYMENT"

        fun from(bundle: Bundle?): ReceiptPaymentPerformedEvent? = bundle?.let {
            val uuid = getReceiptUuid(it) ?: return null
            val payment = bundle.getBundle(KEY_PAYMENT)?.let(PaymentMapper::from) ?: return null
            ReceiptPaymentPerformedEvent(uuid, payment)
        }
    }
}
