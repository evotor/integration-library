package ru.evotor.framework.receipt.event

import android.os.Bundle

/**
 * Событие применения скидки к чеку.
 *
 * Происходит в результате применения скидки на чек.
 *
 * Обрабатывать это событие можно с помощью следующих широковещательных приёмников:
 * [ru.evotor.framework.receipt.event.handler.receiver.SellReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.PaybackReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuyReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuybackReceiptBroadcastReceiver]
 *
 * @param receiptUuid uuid чека
 */
class ApplyDiscountToReceiptEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ApplyDiscountToReceiptEvent? = bundle?.let {
            ApplyDiscountToReceiptEvent(ReceiptEvent.getReceiptUuid(it) ?: return null)
        }
    }
}
