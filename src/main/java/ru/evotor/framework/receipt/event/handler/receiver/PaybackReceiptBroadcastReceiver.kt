package ru.evotor.framework.receipt.event.handler.receiver

import android.content.Context
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.event.ApplyDiscountToReceiptEvent
import ru.evotor.framework.receipt.event.ReceiptCompletedEvent
import ru.evotor.framework.receipt.event.ReceiptCreatedEvent
import ru.evotor.framework.receipt.event.ReceiptDeletedEvent
import ru.evotor.framework.receipt.position.event.PositionAddedEvent
import ru.evotor.framework.receipt.position.event.PositionRemovedEvent
import ru.evotor.framework.receipt.position.event.PositionUpdatedEvent

/**
 * Широковещательный приёмник событий чека возврата.
 * @see <a href="https://developer.evotor.ru/docs/doc_java_broadcastreceiver.html">Использование широковещательного приёмника</a>
 */
open class PaybackReceiptBroadcastReceiver : ReceiptBroadcastReceiver(
        ACTION_RECEIPT_CREATED,
        ACTION_POSITION_ADDED,
        ACTION_POSITION_UPDATED,
        ACTION_POSITION_REMOVED,
        ACTION_APPLY_DISCOUNT_TO_RECEIPT,
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
     * Обработчик событий применения скидки к чеку.
     */
    @RequiresIntentAction(ACTION_APPLY_DISCOUNT_TO_RECEIPT)
    override fun handleApplyDiscountToReceiptEvent(context: Context, eventApplyDiscountTo: ApplyDiscountToReceiptEvent) = Unit

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

        const val ACTION_RECEIPT_CREATED = "evotor.intent.action.receipt.payback.OPENED"

        const val ACTION_POSITION_ADDED = "evotor.intent.action.receipt.payback.POSITION_ADDED"

        const val ACTION_POSITION_UPDATED = "evotor.intent.action.receipt.payback.POSITION_EDITED"

        const val ACTION_POSITION_REMOVED = "evotor.intent.action.receipt.payback.POSITION_REMOVED"

        const val ACTION_APPLY_DISCOUNT_TO_RECEIPT = "evotor.intent.action.receipt.payback.APPLY_DISCOUNT_TO_RECEIPT"

        const val ACTION_RECEIPT_DELETED = "evotor.intent.action.receipt.payback.CLEARED"

        const val ACTION_RECEIPT_COMPLETED = "evotor.intent.action.receipt.payback.RECEIPT_CLOSED"

    }

}