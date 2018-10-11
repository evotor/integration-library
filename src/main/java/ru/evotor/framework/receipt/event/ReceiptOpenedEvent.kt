package ru.evotor.framework.receipt.event

import android.os.Bundle

class ReceiptOpenedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptOpenedEvent? = bundle?.let {
            return ReceiptOpenedEvent(ReceiptEvent.getReceiptUuid(bundle) ?: return null)
        }
    }
}
