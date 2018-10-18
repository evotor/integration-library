package ru.evotor.framework.receipt.position.event

import android.os.Bundle

import ru.evotor.framework.receipt.Position

class PositionRemovedEvent(receiptUuid: String, position: Position) : PositionEvent(receiptUuid, position) {
    companion object {
        fun from(bundle: Bundle?): PositionRemovedEvent? = bundle?.let {
            PositionRemovedEvent(
                    PositionEvent.getReceiptUuid(it) ?: return null,
                    PositionEvent.getPosition(it) ?: return null
            )
        }
    }
}