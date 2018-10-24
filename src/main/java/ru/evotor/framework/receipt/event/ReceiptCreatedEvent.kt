package ru.evotor.framework.receipt.event

import android.os.Bundle

/**
 * Событие создания чека.
 *
 * Происходит при создании нового чека в системе смарт-терминала,
 * например, при открытии приложения "Продажа".
 *
 * Обрабатывать это событие можно с помощью следующих широковещательных приёмников:
 * [ru.evotor.framework.receipt.event.handler.receiver.SellReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.PaybackReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuyReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuybackReceiptBroadcastReceiver]
 *
 * @param receiptUuid uuid чека
 */
class ReceiptCreatedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptCreatedEvent? = bundle?.let {
            ReceiptCreatedEvent(ReceiptEvent.getReceiptUuid(it) ?: return null)
        }
    }
}