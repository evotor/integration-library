package ru.evotor.framework.receipt.position.event

import android.os.Bundle

import ru.evotor.framework.receipt.Position

class PositionAddedEvent(receiptUuid: String, position: Position) : PositionEvent(receiptUuid, position) {
    companion object {
        fun from(bundle: Bundle?): PositionAddedEvent? = bundle?.let {
            PositionAddedEvent(
                    PositionEvent.getReceiptUuid(bundle) ?: return null,
                    PositionEvent.getPosition(bundle) ?: return null)
        }
    }
}
