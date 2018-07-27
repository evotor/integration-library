package ru.evotor.framework.core.action.broadcast

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.ReceiptEventMapper

open class ReceiptBroadcastReceiver(
        private val actionReceiptOpened: String,
        private val actionPositionAdded: String,
        private val actionPositionEdited: String,
        private val actionPositionRemoved: String,
        private val actionReceiptCleared: String,
        private val actionReceiptClosed: String
) : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий открытия чека.
     */
    protected open fun handleReceiptOpenedEvent(context: Context, receiptUuid: String) = Unit

    /**
     * Обработчик событий добавления позиции в чек.
     */
    protected open fun handlePositionAddedEvent(context: Context, receiptUuid: String, positionUuid: String) = Unit

    /**
     * Обработчик событий изменения позиции чека.
     */
    protected open fun handlePositionEditedEvent(context: Context, receiptUuid: String, positionUuid: String) = Unit

    /**
     * Обработчик событий удаления позиции чека.
     */
    protected open fun handlePositionRemovedEvent(context: Context, receiptUuid: String, positionUuid: String) = Unit

    /**
     * Обработчик событий очистки чека.
     */
    protected open fun handleReceiptClearedEvent(context: Context, receiptUuid: String) = Unit

    /**
     * Обработчик событий закрытия чека.
     */
    protected open fun handleReceiptClosedEvent(context: Context, receiptUuid: String) = Unit

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