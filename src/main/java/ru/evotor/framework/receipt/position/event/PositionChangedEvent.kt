package ru.evotor.framework.receipt.position.event

import android.os.Bundle

import ru.evotor.framework.receipt.Position

class PositionChangedEvent(receiptUuid: String, position: Position) : PositionEvent(receiptUuid, position) {
    companion object {
        fun from(bundle: Bundle?): PositionChangedEvent? = bundle?.let {
            PositionChangedEvent(
                    PositionEvent.getReceiptUuid(bundle) ?: return null,
                    PositionEvent.getPosition(bundle) ?: return null)
        }
    }
}
