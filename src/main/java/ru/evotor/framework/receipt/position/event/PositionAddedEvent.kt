package ru.evotor.framework.receipt.position.event

import android.os.Bundle

import ru.evotor.framework.receipt.Position

/**
 * Событие добавления позиции в чек.
 *
 * Происходит при добавлении позиции в чек, который в данный момент формируется в системе смарт-терминала.
 *
 * Обрабатывать это событие можно с помощью следующих широковещательных приёмников:
 * [ru.evotor.framework.receipt.event.handler.receiver.SellReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.PaybackReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuyReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuybackReceiptBroadcastReceiver]
 *
 * @param receiptUuid uuid чека
 * @param position добавленная позиция
 */
class PositionAddedEvent(receiptUuid: String, position: Position) : PositionEvent(receiptUuid, position) {
    companion object {
        fun from(bundle: Bundle?): PositionAddedEvent? = bundle?.let {
            PositionAddedEvent(
                    PositionEvent.getReceiptUuid(it) ?: return null,
                    PositionEvent.getPosition(it) ?: return null
            )
        }
    }
}