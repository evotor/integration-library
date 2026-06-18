package ru.evotor.framework.receipt.event

import android.os.Bundle

/**
 * Событие перехода к экрану редактирования позиций чека.
 *
 * @param receiptUuid uuid чека
 */
class ReceiptEditScreenOpenedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptEditScreenOpenedEvent? = bundle?.let {
            ReceiptEditScreenOpenedEvent(ReceiptEvent.getReceiptUuid(it) ?: return null)
        }
    }
}
