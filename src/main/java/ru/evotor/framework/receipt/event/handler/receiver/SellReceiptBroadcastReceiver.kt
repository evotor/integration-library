package ru.evotor.framework.receipt.event.handler.receiver

import android.content.Context
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.event.ReceiptDeletedEvent
import ru.evotor.framework.receipt.event.ReceiptCompletedEvent
import ru.evotor.framework.receipt.event.ReceiptCreatedEvent
import ru.evotor.framework.receipt.position.event.PositionAddedEvent
import ru.evotor.framework.receipt.position.event.PositionUpdatedEvent
import ru.evotor.framework.receipt.position.event.PositionRemovedEvent

/**
 * Широковещательный приёмник событий приложения "Продажа".
 * @see <a href="https://developer.evotor.ru/docs/beta/doc_java_broadcastreceiver.html">Использование широковещательного приёмника</a>
 */
open class SellReceiptBroadcastReceiver : ReceiptBroadcastReceiver(
        ACTION_RECEIPT_CREATED,
        ACTION_POSITION_ADDED,
        ACTION_POSITION_UPDATED,
        ACTION_POSITION_REMOVED,
        ACTION_RECEIPT_DELETED,
        ACTION_RECEIPT_COMPLETED
) {

    /**
     * Обработчик событий создания чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_CREATED)
    override fun handleReceiptCreatedEvent(context: Context, event: ReceiptCreatedEvent) = Unit

    /**
     * Обработчик событий добавления позиции в чек.
     */
    @RequiresIntentAction(ACTION_POSITION_ADDED)
    override fun handlePositionAddedEvent(context: Context, event: PositionAddedEvent) = Unit

    /**
     * Обработчик событий обновления позиции чека.
     */
    @RequiresIntentAction(ACTION_POSITION_UPDATED)
    override fun handlePositionUpdatedEvent(context: Context, event: PositionUpdatedEvent) = Unit

    /**
     * Обработчик событий удаления позиции из чека.
     */
    @RequiresIntentAction(ACTION_POSITION_REMOVED)
    override fun handlePositionRemovedEvent(context: Context, event: PositionRemovedEvent) = Unit

    /**
     * Обработчик событий удаления чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_DELETED)
    override fun handleReceiptDeletedEvent(context: Context, event: ReceiptDeletedEvent) = Unit

    /**
     * Обработчик событий завершения чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_COMPLETED)
    override fun handleReceiptCompletedEvent(context: Context, event: ReceiptCompletedEvent) = Unit

    companion object {

        const val ACTION_RECEIPT_CREATED = "evotor.intent.action.receipt.sell.OPENED"

        const val ACTION_POSITION_ADDED = "evotor.intent.action.receipt.sell.POSITION_ADDED"

        const val ACTION_POSITION_UPDATED = "evotor.intent.action.receipt.sell.POSITION_EDITED"

        const val ACTION_POSITION_REMOVED = "evotor.intent.action.receipt.sell.POSITION_REMOVED"

        const val ACTION_RECEIPT_DELETED = "evotor.intent.action.receipt.sell.CLEARED"

        const val ACTION_RECEIPT_COMPLETED = "evotor.intent.action.receipt.sell.CLOSED"

    }

}