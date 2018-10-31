package ru.evotor.framework.receipt.event

import android.os.Bundle

/**
 * Событие удаления чека.
 *
 * Происходит при удалении незавершенного чека из системы смарт-терминала,
 * например, при нажатии на кнопку "Удалить чек" в интерфейсе приложения "Продажа".
 *
 * Обрабатывать это событие можно с помощью следующих широковещательных приёмников:
 * [ru.evotor.framework.receipt.event.handler.receiver.SellReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.PaybackReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuyReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuybackReceiptBroadcastReceiver]
 *
 * @param receiptUuid uuid чека
 */
class ReceiptDeletedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptDeletedEvent? = bundle?.let {
            ReceiptDeletedEvent(ReceiptEvent.getReceiptUuid(it) ?: return null)
        }
    }
}
