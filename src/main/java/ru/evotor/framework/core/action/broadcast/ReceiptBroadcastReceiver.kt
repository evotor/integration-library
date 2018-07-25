package ru.evotor.framework.core.action.broadcast

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
    protected open fun handleReceiptOpenedEvent(receiptUuid: String) = Unit

    /**
     * Обработчик событий добавления позиции в чек.
     */
    protected open fun handlePositionAddedEvent(receiptUuid: String, positionUuid: String) = Unit

    /**
     * Обработчик событий изменения позиции чека.
     */
    protected open fun handlePositionEditedEvent(receiptUuid: String, positionUuid: String) = Unit

    /**
     * Обработчик событий удаления позиции чека.
     */
    protected open fun handlePositionRemovedEvent(receiptUuid: String, positionUuid: String) = Unit

    /**
     * Обработчик событий очистки чека.
     */
    protected open fun handleReceiptClearedEvent(receiptUuid: String) = Unit

    /**
     * Обработчик событий закрытия чека.
     */
    protected open fun handleReceiptClosedEvent(receiptUuid: String) = Unit

    final override fun onEvent(action: String, bundle: Bundle) {
        val mapper = ReceiptEventMapper(bundle)
        val receiptUuid = mapper.getReceiptUuid() ?: return
        when (action) {
            actionReceiptOpened -> handleReceiptOpenedEvent(receiptUuid)
            actionPositionAdded, actionPositionEdited, actionPositionRemoved -> {
                val positionUuid = mapper.getPosition()?.uuid ?: return
                when (action) {
                    actionPositionAdded -> handlePositionAddedEvent(receiptUuid, positionUuid)
                    actionPositionEdited -> handlePositionEditedEvent(receiptUuid, positionUuid)
                    actionPositionRemoved -> handlePositionRemovedEvent(receiptUuid, positionUuid)
                }
            }
            actionReceiptCleared -> handleReceiptClearedEvent(receiptUuid)
            actionReceiptClosed -> handleReceiptClosedEvent(receiptUuid)
        }
    }

}