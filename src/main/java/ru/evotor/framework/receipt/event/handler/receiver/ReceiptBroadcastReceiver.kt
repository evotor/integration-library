package ru.evotor.framework.receipt.event.handler.receiver

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.BroadcastEventReceiver
import ru.evotor.framework.core.action.event.receipt.position_edited.PositionAddedEvent
import ru.evotor.framework.core.action.event.receipt.position_edited.PositionEditedEvent
import ru.evotor.framework.core.action.event.receipt.position_edited.PositionRemovedEvent
import ru.evotor.framework.core.action.event.receipt.receipt_edited.ReceiptClearedEvent
import ru.evotor.framework.core.action.event.receipt.receipt_edited.ReceiptClosedEvent
import ru.evotor.framework.core.action.event.receipt.receipt_edited.ReceiptOpenedEvent

abstract class ReceiptBroadcastReceiver(
        private val actionReceiptOpened: String,
        private val actionPositionAdded: String,
        private val actionPositionEdited: String,
        private val actionPositionRemoved: String,
        private val actionReceiptCleared: String,
        private val actionReceiptClosed: String
) : BroadcastEventReceiver() {

    protected abstract fun handleReceiptOpenedEvent(context: Context, receiptOpenedEvent: ReceiptOpenedEvent)

    protected abstract fun handlePositionAddedEvent(context: Context, positionAddedEvent: PositionAddedEvent)

    protected abstract fun handlePositionEditedEvent(context: Context, positionEditedEvent: PositionEditedEvent)

    protected abstract fun handlePositionRemovedEvent(context: Context, positionRemovedEvent: PositionRemovedEvent)

    protected abstract fun handleReceiptClearedEvent(context: Context, receiptClearedEvent: ReceiptClearedEvent)

    protected abstract fun handleReceiptClosedEvent(context: Context, receiptClosedEvent: ReceiptClosedEvent)

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        when (action) {
            actionReceiptOpened -> handleReceiptOpenedEvent(context, ReceiptOpenedEvent.create(bundle) ?: return)
            actionPositionAdded -> handlePositionAddedEvent(context, PositionAddedEvent.create(bundle) ?: return)
            actionPositionEdited -> handlePositionEditedEvent(context, PositionEditedEvent.create(bundle) ?: return)
            actionPositionRemoved -> handlePositionRemovedEvent(context, PositionRemovedEvent.create(bundle) ?: return)
            actionReceiptCleared -> handleReceiptClearedEvent(context, ReceiptClearedEvent.create(bundle) ?: return)
            actionReceiptClosed -> handleReceiptClosedEvent(context, ReceiptClosedEvent.create(bundle) ?: return)
        }
    }

}