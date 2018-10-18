package ru.evotor.framework.receipt.event

import android.os.Bundle

class ReceiptCreatedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptCreatedEvent? = bundle?.let {
            return ReceiptCreatedEvent(ReceiptEvent.getReceiptUuid(bundle) ?: return null)
        }
    }
}
