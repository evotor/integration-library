package ru.evotor.framework.receipt.event.handler.receiver

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.BroadcastEventReceiver
import ru.evotor.framework.receipt.event.ReceiptClearedEvent
import ru.evotor.framework.receipt.event.ReceiptClosedEvent
import ru.evotor.framework.receipt.event.ReceiptOpenedEvent
import ru.evotor.framework.receipt.position.event.PositionAddedEvent
import ru.evotor.framework.receipt.position.event.PositionEditedEvent
import ru.evotor.framework.receipt.position.event.PositionRemovedEvent

abstract class ReceiptBroadcastReceiver(
        private val actionReceiptOpened: String,
        private val actionPositionAdded: String,
        private val actionPositionEdited: String,
        private val actionPositionRemoved: String,
        private val actionReceiptCleared: String,
        private val actionReceiptClosed: String
) : BroadcastEventReceiver() {

    protected abstract fun handleReceiptOpenedEvent(context: Context, event: ReceiptOpenedEvent)

    protected abstract fun handlePositionAddedEvent(context: Context, event: PositionAddedEvent)

    protected abstract fun handlePositionEditedEvent(context: Context, event: PositionEditedEvent)

    protected abstract fun handlePositionRemovedEvent(context: Context, event: PositionRemovedEvent)

    protected abstract fun handleReceiptClearedEvent(context: Context, event: ReceiptClearedEvent)

    protected abstract fun handleReceiptClosedEvent(context: Context, event: ReceiptClosedEvent)

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        when (action) {
            actionReceiptOpened -> handleReceiptOpenedEvent(context, ReceiptOpenedEvent.from(bundle)
                    ?: return)
            actionPositionAdded -> handlePositionAddedEvent(context, PositionAddedEvent.from(bundle)
                    ?: return)
            actionPositionEdited -> handlePositionEditedEvent(context, PositionEditedEvent.from(bundle)
                    ?: return)
            actionPositionRemoved -> handlePositionRemovedEvent(context, PositionRemovedEvent.from(bundle)
                    ?: return)
            actionReceiptCleared -> handleReceiptClearedEvent(context, ReceiptClearedEvent.from(bundle)
                    ?: return)
            actionReceiptClosed -> handleReceiptClosedEvent(context, ReceiptClosedEvent.from(bundle)
                    ?: return)
        }
    }

}