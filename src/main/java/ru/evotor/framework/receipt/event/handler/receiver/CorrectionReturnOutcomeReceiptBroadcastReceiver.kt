package ru.evotor.framework.receipt.event.handler.receiver

import android.content.Context
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.event.ApplyDiscountToReceiptEvent
import ru.evotor.framework.receipt.event.ReceiptEditScreenOpenedEvent
import ru.evotor.framework.receipt.event.ReceiptCompletedEvent
import ru.evotor.framework.receipt.event.ReceiptCreatedEvent
import ru.evotor.framework.receipt.event.ReceiptDeletedEvent
import ru.evotor.framework.receipt.event.ReceiptPaidEvent
import ru.evotor.framework.receipt.event.ReceiptPaymentCanceledEvent
import ru.evotor.framework.receipt.event.ReceiptPaymentPaidEvent
import ru.evotor.framework.receipt.event.ReceiptPaymentScreenOpenedEvent
import ru.evotor.framework.receipt.event.ReceiptPaymentsCanceledEvent
import ru.evotor.framework.receipt.position.event.PositionAddedEvent
import ru.evotor.framework.receipt.position.event.PositionRemovedEvent
import ru.evotor.framework.receipt.position.event.PositionUpdatedEvent

/**
 * Широковещательный приёмник событий приложения "Коррекция возврата расхода".
 * @see <a href="https://developer.evotor.ru/docs/doc_java_broadcastreceiver.html">Использование широковещательного приёмника</a>
 */
open class CorrectionReturnOutcomeReceiptBroadcastReceiver : ReceiptBroadcastReceiver(
    actionReceiptCreated = ACTION_RECEIPT_CREATED,
    actionPositionAdded = ACTION_POSITION_ADDED,
    actionPositionUpdated = ACTION_POSITION_UPDATED,
    actionPositionRemoved = ACTION_POSITION_REMOVED,
    actionApplyDiscountToReceipt = ACTION_APPLY_DISCOUNT_TO_RECEIPT,
    actionReceiptDeleted = ACTION_RECEIPT_DELETED,
    actionReceiptCompleted = ACTION_RECEIPT_COMPLETED,
    actionReceiptEditScreenOpened = ACTION_RECEIPT_EDIT_SCREEN_OPENED,
    actionReceiptPaymentScreenOpened = ACTION_RECEIPT_PAYMENT_SCREEN_OPENED,
    actionReceiptPaymentCanceled = ACTION_RECEIPT_PAYMENT_CANCELED,
    actionReceiptPaymentsCanceled = ACTION_RECEIPT_PAYMENTS_CANCELED,
    actionReceiptPaymentPaid = ACTION_RECEIPT_PAYMENT_PERFORMED,
    actionReceiptPaid = ACTION_RECEIPT_PAID
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
     * Обработчик событий применения скидки к чеку
     */
    @RequiresIntentAction(ACTION_APPLY_DISCOUNT_TO_RECEIPT)
    override fun handleApplyDiscountToReceiptEvent(
        context: Context,
        eventApplyDiscountTo: ApplyDiscountToReceiptEvent
    ) = Unit

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

    /**
     * Обработчик событий перехода в список позиций чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_EDIT_SCREEN_OPENED)
    override fun handleReceiptEditScreenOpenedEvent(context: Context, event: ReceiptEditScreenOpenedEvent) = Unit

    /**
     * Обработчик событий перехода к оплате чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_PAYMENT_SCREEN_OPENED)
    override fun handleReceiptPaymentScreenOpenedEvent(context: Context, event: ReceiptPaymentScreenOpenedEvent) = Unit

    /**
     * Обработчик событий отмены конкретной оплаты чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_PAYMENT_CANCELED)
    override fun handleReceiptPaymentCanceledEvent(context: Context, event: ReceiptPaymentCanceledEvent) = Unit

    /**
     * Обработчик событий отмены всех оплат чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_PAYMENTS_CANCELED)
    override fun handleReceiptPaymentsCanceledEvent(context: Context, event: ReceiptPaymentsCanceledEvent) = Unit

    /**
     * Обработчик событий завершённой конкретной оплаты чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_PAYMENT_PERFORMED)
    override fun handleReceiptPaymentPaidEvent(context: Context, event: ReceiptPaymentPaidEvent) = Unit

    /**
     * Обработчик событий полностью завершённой оплаты чека.
     */
    @RequiresIntentAction(ACTION_RECEIPT_PAID)
    override fun handleReceiptPaidEvent(context: Context, event: ReceiptPaidEvent) = Unit

    companion object {
        const val ACTION_RECEIPT_CREATED =
            "evotor.intent.action.receipt.correction.return.outcome.OPENED"
        const val ACTION_POSITION_ADDED =
            "evotor.intent.action.receipt.correction.return.outcome.POSITION_ADDED"
        const val ACTION_POSITION_UPDATED =
            "evotor.intent.action.receipt.correction.return.outcome.POSITION_EDITED"
        const val ACTION_POSITION_REMOVED =
            "evotor.intent.action.receipt.correction.return.outcome.POSITION_REMOVED"
        const val ACTION_APPLY_DISCOUNT_TO_RECEIPT =
            "evotor.intent.action.receipt.correction.return.outcome.APPLY_DISCOUNT_TO_RECEIPT"
        const val ACTION_RECEIPT_DELETED =
            "evotor.intent.action.receipt.correction.return.outcome.CLEARED"
        const val ACTION_RECEIPT_COMPLETED =
            "evotor.intent.action.receipt.correction.return.outcome.RECEIPT_CLOSED"
        const val ACTION_RECEIPT_EDIT_SCREEN_OPENED =
            "evotor.intent.action.receipt.correction.return.outcome.EDIT_SCREEN_OPENED"
        const val ACTION_RECEIPT_PAYMENT_SCREEN_OPENED =
            "evotor.intent.action.receipt.correction.return.outcome.PAYMENT_SCREEN_OPENED"

        const val ACTION_RECEIPT_PAYMENT_CANCELED =
            "evotor.intent.action.receipt.correction.return.outcome.PAYMENT_CANCELED"

        const val ACTION_RECEIPT_PAYMENTS_CANCELED =
            "evotor.intent.action.receipt.correction.return.outcome.PAYMENTS_CANCELED"

        const val ACTION_RECEIPT_PAYMENT_PERFORMED =
            "evotor.intent.action.receipt.correction.return.outcome.PAYMENT_PERFORMED"

        const val ACTION_RECEIPT_PAID =
            "evotor.intent.action.receipt.correction.return.outcome.PAID"
    }
}
