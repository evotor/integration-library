package ru.evotor.framework.receipt.event.handler.receiver

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.BroadcastEventReceiver
import ru.evotor.framework.receipt.event.ReceiptClearedEvent
import ru.evotor.framework.receipt.event.ReceiptDeletedEvent
import ru.evotor.framework.receipt.event.ReceiptCreatedEvent
import ru.evotor.framework.receipt.position.event.PositionAddedEvent
import ru.evotor.framework.receipt.position.event.PositionChangedEvent
import ru.evotor.framework.receipt.position.event.PositionRemovedEvent

abstract class ReceiptBroadcastReceiver(
        private val actionReceiptCreated: String,
        private val actionPositionAdded: String,
        private val actionPositionChanged: String,
        private val actionPositionRemoved: String,
        private val actionReceiptCleared: String,
        private val actionReceiptDeleted: String
) : BroadcastEventReceiver() {

    protected abstract fun handleReceiptCreatedEvent(context: Context, event: ReceiptCreatedEvent)

    protected abstract fun handlePositionAddedEvent(context: Context, event: PositionAddedEvent)

    protected abstract fun handlePositionChangedEvent(context: Context, event: PositionChangedEvent)

    protected abstract fun handlePositionRemovedEvent(context: Context, event: PositionRemovedEvent)

    protected abstract fun handleReceiptClearedEvent(context: Context, event: ReceiptClearedEvent)

    protected abstract fun handleReceiptDeletedEvent(context: Context, event: ReceiptDeletedEvent)

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        when (action) {
            actionReceiptCreated -> handleReceiptCreatedEvent(context, ReceiptCreatedEvent.from(bundle)
                    ?: return)
            actionPositionAdded -> handlePositionAddedEvent(context, PositionAddedEvent.from(bundle)
                    ?: return)
            actionPositionChanged -> handlePositionChangedEvent(context, PositionChangedEvent.from(bundle)
                    ?: return)
            actionPositionRemoved -> handlePositionRemovedEvent(context, PositionRemovedEvent.from(bundle)
                    ?: return)
            actionReceiptCleared -> handleReceiptClearedEvent(context, ReceiptClearedEvent.from(bundle)
                    ?: return)
            actionReceiptDeleted -> handleReceiptDeletedEvent(context, ReceiptDeletedEvent.from(bundle)
                    ?: return)
        }
    }

}