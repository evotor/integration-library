package ru.evotor.framework.receipt.event

import android.os.Bundle

/**
 * Событие перехода к экрану оплаты чека.
 *
 * @param receiptUuid uuid чека
 */
class ReceiptPaymentScreenOpenedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptPaymentScreenOpenedEvent? = bundle?.let {
            ReceiptPaymentScreenOpenedEvent(ReceiptEvent.getReceiptUuid(it) ?: return null)
        }
    }
}
