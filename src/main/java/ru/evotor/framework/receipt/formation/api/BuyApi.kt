package ru.evotor.framework.receipt.formation.api

import android.content.ComponentName
import android.content.Context
import ru.evotor.framework.component.PaymentDelegator
import ru.evotor.framework.component.PaymentPerformer
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.startIntegrationService
import ru.evotor.framework.receipt.Receipt
import ru.evotor.framework.receipt.formation.api.move_receipt_to_payment_stage.MoveCurrentReceiptDraftToPaymentStageCallback
import ru.evotor.framework.receipt.formation.api.move_receipt_to_payment_stage.MoveCurrentReceiptDraftToPaymentStageException
import ru.evotor.framework.receipt.formation.api.trigger_receipt_discount_event.TriggerReceiptDiscountEventCallback
import ru.evotor.framework.receipt.formation.api.trigger_receipt_discount_event.TriggerReceiptDiscountEventException
import ru.evotor.framework.receipt.formation.event.CurrentReceiptDraftMovementToPaymentStageRequestedEvent
import ru.evotor.framework.receipt.formation.event.TriggerReceiptDiscountEventRequestedEvent
import ru.evotor.framework.receipt.formation.event.handler.service.BuyBacksideIntegrationService
import ru.evotor.framework.receipt.formation.event.handler.service.TriggerReceiptDiscountEventIntegrationService

/**
 * Класс содержит методы для проведения чеков покупки из интерфейса приложения.
 */
object BuyApi {
    /**
     * Переходит к процессу оплаты с последующей печатью чека.
     *
     * @param context Контекст приложения
     * @param paymentPerformer Интеграционный компонент, осуществляющий оплату
     * @param callback
     */
    @JvmStatic
    fun moveCurrentReceiptDraftToPaymentStage(context: Context, paymentPerformer: PaymentPerformer, callback: MoveCurrentReceiptDraftToPaymentStageCallback) {
        context.startIntegrationService(
            BuyBacksideIntegrationService.ACTION_MOVE_CURRENT_BUY_RECEIPT_DRAFT_TO_PAYMENT_STAGE,
            CurrentReceiptDraftMovementToPaymentStageRequestedEvent(null, paymentPerformer),
            IntegrationManagerCallback {
                it?.result?.error?.let { error -> callback.onError(MoveCurrentReceiptDraftToPaymentStageException(error.code, error.message)) }
                    ?: callback.onSuccess()
            }
        )
    }

    /**
     * Переходит к процессу оплаты с последующей печатью чека.
     *
     * @param context Контекст приложения
     * @param paymentDelegator Интеграционный компонент, осуществляющий делегирование платежей другим приложениям
     * @param callback
     */
    @JvmStatic
    fun moveCurrentReceiptDraftToPaymentStage(context: Context, paymentDelegator: PaymentDelegator, callback: MoveCurrentReceiptDraftToPaymentStageCallback) {
        context.startIntegrationService(
            BuyBacksideIntegrationService.ACTION_MOVE_CURRENT_BUY_RECEIPT_DRAFT_TO_PAYMENT_STAGE,
            CurrentReceiptDraftMovementToPaymentStageRequestedEvent(paymentDelegator, null),
            IntegrationManagerCallback {
                it?.result?.error?.let { error -> callback.onError(MoveCurrentReceiptDraftToPaymentStageException(error.code, error.message)) }
                    ?: callback.onSuccess()
            }
        )
    }

    /**
     * Вызывает сервис начисления скидки.
     * Требует наличие permission'а [TriggerReceiptDiscountEventRequestedEvent.NAME_PERMISSION]
     *
     * @param context Контекст приложения
     * @param componentName - ComponentName сервиса-обработчика события ReceiptDiscountEvent
     * @param callback
     */
    @JvmStatic
    fun triggerReceiptDiscountEvent(context: Context, componentName: ComponentName, callback: TriggerReceiptDiscountEventCallback) {
        context.startIntegrationService(
            TriggerReceiptDiscountEventIntegrationService.ACTION_TRIGGER_RECEIPT_DISCOUNT_EVENT,
            TriggerReceiptDiscountEventRequestedEvent(componentName, Receipt.Type.BUY)
        ) {
            it?.result?.error?.let { error -> callback.onError(TriggerReceiptDiscountEventException(error.code, error.message)) }
                ?: callback.onSuccess()
        }
    }
}
