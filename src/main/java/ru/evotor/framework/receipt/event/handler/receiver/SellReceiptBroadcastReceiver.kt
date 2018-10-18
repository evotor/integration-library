package ru.evotor.framework.receipt.event.handler.receiver

import android.content.Context
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.event.ReceiptClearedEvent
import ru.evotor.framework.receipt.event.ReceiptDeletedEvent
import ru.evotor.framework.receipt.event.ReceiptCreatedEvent
import ru.evotor.framework.receipt.position.event.PositionAddedEvent
import ru.evotor.framework.receipt.position.event.PositionChangedEvent
import ru.evotor.framework.receipt.position.event.PositionRemovedEvent

/**
 * Широковещательный приёмник событий чека продажи.
 * @see <a href="https://developer.evotor.ru/docs/beta/doc_java_broadcastreceiver.html">Использование широковещательного приёмника</a>
 */
open class SellReceiptBroadcastReceiver : ReceiptBroadcastReceiver(
        ACTION_RECEIPT_CREATED,
        ACTION_POSITION_ADDED,
        ACTION_POSITION_CHANGED,
        ACTION_POSITION_REMOVED,
        ACTION_RECEIPT_CLEARED,
        ACTION_RECEIPT_DELETED
) {

    /**
     * Обработчик событий открытия чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_CREATED)
    override fun handleReceiptCreatedEvent(context: Context, event: ReceiptCreatedEvent) = Unit

    /**
     * Обработчик событий добавления позиции в чек.
     */
    @RequiresIntentAction(ACTION_POSITION_ADDED)
    override fun handlePositionAddedEvent(context: Context, event: PositionAddedEvent) = Unit

    /**
     * Обработчик событий изменения позиции чека.
     */
    @RequiresIntentAction(ACTION_POSITION_CHANGED)
    override fun handlePositionChangedEvent(context: Context, event: PositionChangedEvent) = Unit

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
    @RequiresIntentAction(ACTION_RECEIPT_DELETED)
    override fun handleReceiptDeletedEvent(context: Context, event: ReceiptDeletedEvent) = Unit

    companion object {

        const val ACTION_RECEIPT_CREATED = "evotor.intent.action.receipt.sell.OPENED"

        const val ACTION_POSITION_ADDED = "evotor.intent.action.receipt.sell.POSITION_ADDED"

        const val ACTION_POSITION_CHANGED = "evotor.intent.action.receipt.sell.POSITION_EDITED"

        const val ACTION_POSITION_REMOVED = "evotor.intent.action.receipt.sell.POSITION_REMOVED"

        const val ACTION_RECEIPT_CLEARED = "evotor.intent.action.receipt.sell.CLEARED"

        const val ACTION_RECEIPT_DELETED = "evotor.intent.action.receipt.sell.CLOSED"

    }

}