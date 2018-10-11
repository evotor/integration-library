package ru.evotor.framework.receipt.event

import android.os.Bundle

class ReceiptClosedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptClosedEvent? = bundle?.let {
            return ReceiptClosedEvent(ReceiptEvent.getReceiptUuid(bundle) ?: return null)
        }
    }
}
