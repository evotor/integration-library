package ru.evotor.framework.receipt.event.handler.receiver

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.BroadcastEventReceiver
import ru.evotor.framework.receipt.event.ApplyDiscountToReceiptEvent
import ru.evotor.framework.receipt.event.ReceiptCompletedEvent
import ru.evotor.framework.receipt.event.ReceiptCreatedEvent
import ru.evotor.framework.receipt.event.ReceiptDeletedEvent
import ru.evotor.framework.receipt.position.event.PositionAddedEvent
import ru.evotor.framework.receipt.position.event.PositionRemovedEvent
import ru.evotor.framework.receipt.position.event.PositionUpdatedEvent

abstract class ReceiptBroadcastReceiver(
        private val actionReceiptCreated: String,
        private val actionPositionAdded: String,
        private val actionPositionUpdated: String,
        private val actionPositionRemoved: String,
        private val actionApplyDiscountToReceipt: String,
        private val actionReceiptDeleted: String,
        private val actionReceiptCompleted: String
) : BroadcastEventReceiver() {

    protected abstract fun handleReceiptCreatedEvent(context: Context, event: ReceiptCreatedEvent)

    protected abstract fun handlePositionAddedEvent(context: Context, event: PositionAddedEvent)

    protected abstract fun handlePositionUpdatedEvent(context: Context, event: PositionUpdatedEvent)

    protected abstract fun handlePositionRemovedEvent(context: Context, event: PositionRemovedEvent)

    protected abstract fun handleApplyDiscountToReceiptEvent(context: Context, eventApplyDiscountTo: ApplyDiscountToReceiptEvent)

    protected abstract fun handleReceiptDeletedEvent(context: Context, event: ReceiptDeletedEvent)

    protected abstract fun handleReceiptCompletedEvent(context: Context, event: ReceiptCompletedEvent)

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        when (action) {
            actionReceiptCreated -> handleReceiptCreatedEvent(context, ReceiptCreatedEvent.from(bundle)
                    ?: return)
            actionPositionAdded -> handlePositionAddedEvent(context, PositionAddedEvent.from(bundle)
                    ?: return)
            actionPositionUpdated -> handlePositionUpdatedEvent(context, PositionUpdatedEvent.from(bundle)
                    ?: return)
            actionPositionRemoved -> handlePositionRemovedEvent(context, PositionRemovedEvent.from(bundle)
                    ?: return)
            actionApplyDiscountToReceipt -> handleApplyDiscountToReceiptEvent(context, ApplyDiscountToReceiptEvent.from(bundle)
                    ?: return)
            actionReceiptDeleted -> handleReceiptDeletedEvent(context, ReceiptDeletedEvent.from(bundle)
                    ?: return)
            actionReceiptCompleted -> handleReceiptCompletedEvent(context, ReceiptCompletedEvent.from(bundle)
                    ?: return)
        }
    }

}