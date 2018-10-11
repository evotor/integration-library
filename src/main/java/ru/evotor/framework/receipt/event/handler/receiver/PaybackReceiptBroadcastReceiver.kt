package ru.evotor.framework.receipt.event.handler.receiver

import android.content.Context
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.event.ReceiptClearedEvent
import ru.evotor.framework.receipt.event.ReceiptClosedEvent
import ru.evotor.framework.receipt.event.ReceiptOpenedEvent
import ru.evotor.framework.receipt.position.event.PositionAddedEvent
import ru.evotor.framework.receipt.position.event.PositionEditedEvent
import ru.evotor.framework.receipt.position.event.PositionRemovedEvent

/**
 * Широковещательный приёмник событий чека возврата.
 * @see <a href="https://developer.evotor.ru/docs/beta/doc_java_broadcastreceiver.html">Использование широковещательного приёмника</a>
 */
open class PaybackReceiptBroadcastReceiver : ReceiptBroadcastReceiver(
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
    override fun handleReceiptOpenedEvent(context: Context, event: ReceiptOpenedEvent) = Unit

    /**
     * Обработчик событий добавления позиции в чек.
     */
    @RequiresIntentAction(ACTION_POSITION_ADDED)
    override fun handlePositionAddedEvent(context: Context, event: PositionAddedEvent) = Unit

    /**
     * Обработчик событий изменения позиции чека.
     */
    @RequiresIntentAction(ACTION_POSITION_EDITED)
    override fun handlePositionEditedEvent(context: Context, event: PositionEditedEvent) = Unit

    /**
     * Обработчик событий удаления позиции чека.
     */
    @RequiresIntentAction(ACTION_POSITION_REMOVED)
    override fun handlePositionRemovedEvent(context: Context, event: PositionRemovedEvent) = Unit

    /**
     * Обработчик событий очистки чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_CLEARED)
    override fun handleReceiptClearedEvent(context: Context, event: ReceiptClearedEvent) = Unit

    /**
     * Обработчик событий закрытия чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_CLOSED)
    override fun handleReceiptClosedEvent(context: Context, event: ReceiptClosedEvent) = Unit

    companion object {

        const val ACTION_RECEIPT_OPENED = "evotor.intent.action.receipt.payback.OPENED"

        const val ACTION_POSITION_ADDED = "evotor.intent.action.receipt.payback.POSITION_ADDED"

        const val ACTION_POSITION_EDITED = "evotor.intent.action.receipt.payback.POSITION_EDITED"

        const val ACTION_POSITION_REMOVED = "evotor.intent.action.receipt.payback.POSITION_REMOVED"

        const val ACTION_RECEIPT_CLEARED = "evotor.intent.action.receipt.payback.CLEARED"

        const val ACTION_RECEIPT_CLOSED = "evotor.intent.action.receipt.payback.CLOSED"

    }

}