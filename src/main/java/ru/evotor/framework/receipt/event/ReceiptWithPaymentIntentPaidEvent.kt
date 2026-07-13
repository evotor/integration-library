package ru.evotor.framework.receipt.event

import android.os.Bundle
import kotlin.let

class ReceiptWithPaymentIntentPaidEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptWithPaymentIntentPaidEvent? = bundle?.let {
            ReceiptWithPaymentIntentPaidEvent(getReceiptUuid(it) ?: return null)
        }
    }
}
