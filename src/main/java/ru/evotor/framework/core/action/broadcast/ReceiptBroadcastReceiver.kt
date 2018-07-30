package ru.evotor.framework.core.action.broadcast

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.ReceiptEventMapper

abstract class ReceiptBroadcastReceiver(
        private val actionReceiptOpened: String,
        private val actionPositionAdded: String,
        private val actionPositionEdited: String,
        private val actionPositionRemoved: String,
        private val actionReceiptCleared: String,
        private val actionReceiptClosed: String
) : AbstractBroadcastReceiver() {

    protected abstract fun handleReceiptOpenedEvent(context: Context, receiptUuid: String)

    protected abstract fun handlePositionAddedEvent(context: Context, receiptUuid: String, positionUuid: String)

    protected abstract fun handlePositionEditedEvent(context: Context, receiptUuid: String, positionUuid: String)

    protected abstract fun handlePositionRemovedEvent(context: Context, receiptUuid: String, positionUuid: String)

    protected abstract fun handleReceiptClearedEvent(context: Context, receiptUuid: String)

    protected abstract fun handleReceiptClosedEvent(context: Context, receiptUuid: String)

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        val mapper = ReceiptEventMapper(bundle)
        val receiptUuid = mapper.getReceiptUuid() ?: return
        when (action) {
            actionReceiptOpened -> handleReceiptOpenedEvent(context, receiptUuid)
            actionPositionAdded, actionPositionEdited, actionPositionRemoved -> {
                val positionUuid = mapper.getPosition()?.uuid ?: return
                when (action) {
                    actionPositionAdded -> handlePositionAddedEvent(context, receiptUuid, positionUuid)
                    actionPositionEdited -> handlePositionEditedEvent(context, receiptUuid, positionUuid)
                    actionPositionRemoved -> handlePositionRemovedEvent(context, receiptUuid, positionUuid)
                }
            }
            actionReceiptCleared -> handleReceiptClearedEvent(context, receiptUuid)
            actionReceiptClosed -> handleReceiptClosedEvent(context, receiptUuid)
        }
    }

}