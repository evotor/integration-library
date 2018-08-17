package ru.evotor.framework.core.action.broadcast

import android.content.Context
import ru.evotor.framework.core.action.event.receipt.position_edited.PositionAddedEvent
import ru.evotor.framework.core.action.event.receipt.position_edited.PositionEditedEvent
import ru.evotor.framework.core.action.event.receipt.position_edited.PositionRemovedEvent
import ru.evotor.framework.core.action.event.receipt.receipt_edited.ReceiptClearedEvent
import ru.evotor.framework.core.action.event.receipt.receipt_edited.ReceiptClosedEvent
import ru.evotor.framework.core.action.event.receipt.receipt_edited.ReceiptOpenedEvent

/**
 * Широковещательный приёмник событий чека покупки.
 * @see <a href="https://developer.evotor.ru/docs/">Использование широковещательного приёмника</a>
 */
open class BuyReceiptBroadcastReceiver : ReceiptBroadcastReceiver(
        ACTION_RECEIPT_OPENED,
        ACTION_POSITION_ADDED,
        ACTION_POSITION_EDITED,
        ACTION_POSITION_REMOVED,
        ACTION_RECEIPT_CLEARED,
        ACTION_RECEIPT_CLOSED
) {

    /**
     * Обработчик событий открытия чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_OPENED)
    override fun handleReceiptOpenedEvent(context: Context, receiptOpenedEvent: ReceiptOpenedEvent) = Unit

    /**
     * Обработчик событий добавления позиции в чек.
     */
    @RequiresIntentAction(ACTION_POSITION_ADDED)
    override fun handlePositionAddedEvent(context: Context, positionAddedEvent: PositionAddedEvent) = Unit

    /**
     * Обработчик событий изменения позиции чека.
     */
    @RequiresIntentAction(ACTION_POSITION_EDITED)
    override fun handlePositionEditedEvent(context: Context, positionEditedEvent: PositionEditedEvent) = Unit

    /**
     * Обработчик событий удаления позиции чека.
     */
    @RequiresIntentAction(ACTION_POSITION_REMOVED)
    override fun handlePositionRemovedEvent(context: Context, positionRemovedEvent: PositionRemovedEvent) = Unit

    /**
     * Обработчик событий очистки чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_CLEARED)
    override fun handleReceiptClearedEvent(context: Context, receiptClearedEvent: ReceiptClearedEvent) = Unit

    /**
     * Обработчик событий закрытия чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_CLOSED)
    override fun handleReceiptClosedEvent(context: Context, receiptClosedEvent: ReceiptClosedEvent) = Unit

    companion object {

        const val ACTION_RECEIPT_OPENED = "evotor.intent.action.receipt.buy.OPENED"

        const val ACTION_POSITION_ADDED = "evotor.intent.action.receipt.buy.POSITION_ADDED"

        const val ACTION_POSITION_EDITED = "evotor.intent.action.receipt.buy.POSITION_EDITED"

        const val ACTION_POSITION_REMOVED = "evotor.intent.action.receipt.buy.POSITION_REMOVED"

        const val ACTION_RECEIPT_CLEARED = "evotor.intent.action.receipt.buy.CLEARED"

        const val ACTION_RECEIPT_CLOSED = "evotor.intent.action.receipt.buy.CLOSED"

    }

}