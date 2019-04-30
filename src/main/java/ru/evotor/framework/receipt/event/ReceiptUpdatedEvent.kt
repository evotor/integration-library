package ru.evotor.framework.receipt.event

import android.os.Bundle

/**
 * Событие обновления чека.
 *
 * Происходит при обновлении незавершенного чека,
 * например, как результат применения скидки на чек.
 *
 * Обрабатывать это событие можно с помощью следующих широковещательных приёмников:
 * [ru.evotor.framework.receipt.event.handler.receiver.SellReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.PaybackReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuyReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuybackReceiptBroadcastReceiver]
 *
 * @param receiptUuid uuid чека
 */
class ReceiptUpdatedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptUpdatedEvent? = bundle?.let {
            ReceiptUpdatedEvent(ReceiptEvent.getReceiptUuid(it) ?: return null)
        }
    }
}
