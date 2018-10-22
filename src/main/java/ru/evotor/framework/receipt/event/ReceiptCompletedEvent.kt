package ru.evotor.framework.receipt.event

import android.os.Bundle

/**
 * Событие завершения чека.
 *
 * Происходит при успешном завершении формирования чека в системе смарт-терминала,
 * например, при успешном совершении продажи.
 *
 * Обрабатывать это событие можно с помощью следующих широковещательных приёмников:
 * [ru.evotor.framework.receipt.event.handler.receiver.SellReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.PaybackReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuyReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuybackReceiptBroadcastReceiver]
 *
 * @param receiptUuid uuid чека
 */
class ReceiptCompletedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptCompletedEvent? = bundle?.let {
            ReceiptCompletedEvent(ReceiptEvent.getReceiptUuid(it) ?: return null)
        }
    }
}