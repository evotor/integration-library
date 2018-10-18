package ru.evotor.framework.receipt.event

import android.os.Bundle

class ReceiptDeletedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptDeletedEvent? = bundle?.let {
            return ReceiptDeletedEvent(ReceiptEvent.getReceiptUuid(bundle) ?: return null)
        }
    }
}