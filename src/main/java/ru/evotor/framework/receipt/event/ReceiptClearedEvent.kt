package ru.evotor.framework.receipt.event

import android.os.Bundle

class ReceiptClearedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptClearedEvent? = bundle?.let {
            return ReceiptClearedEvent(ReceiptEvent.getReceiptUuid(bundle) ?: return null)
        }
    }
}
