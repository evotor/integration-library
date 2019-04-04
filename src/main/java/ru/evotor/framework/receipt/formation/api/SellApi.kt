package ru.evotor.framework.receipt.formation.api

import android.content.Context
import ru.evotor.framework.component.PaymentDelegator
import ru.evotor.framework.component.PaymentPerformer
import ru.evotor.framework.core.IntegrationManagerCallback
import ru.evotor.framework.core.startIntegrationService
import ru.evotor.framework.receipt.event.handler.service.ReceiptBacksideIntegrationService
import ru.evotor.framework.receipt.formation.event.MoveCurrentReceiptDraftToPaymentStageEvent

object SellApi {
    /**
     * Переходит к процессу оплаты с последующей печатью чека
     *
     * @param context Контекст приложения
     * @param paymentPerformer Интеграционный компонент, осуществляющий оплату
     * @param callback
     */
    @JvmStatic
    fun moveCurrentSellReceiptDraftToPaymentStage(context: Context, paymentPerformer: PaymentPerformer, callback: ReceiptFormationCallback) {
        context.startIntegrationService(
                ReceiptBacksideIntegrationService.ACTION_MOVE_CURRENT_SELL_RECEIPT_DRAFT_TO_PAYMENT_STAGE,
                MoveCurrentReceiptDraftToPaymentStageEvent(null, paymentPerformer),
                IntegrationManagerCallback {
                    it?.result?.error?.let { error -> callback.onError(ReceiptFormationException(error.code, error.message)) }
                            ?: run { callback.onSuccess() }
                }
        )
    }

    /**
     * Переходит к процессу оплаты с последующей печатью чека)
     *
     * @param context Контекст приложения
     * @param paymentDelegator Интеграционный компонент, осуществляющий комбооплату
     * @param callback
     */
    @JvmStatic
    fun moveCurrentSellReceiptDraftToPaymentStage(context: Context, paymentDelegator: PaymentDelegator, callback: ReceiptFormationCallback) {
        context.startIntegrationService(
                ReceiptBacksideIntegrationService.ACTION_MOVE_CURRENT_SELL_RECEIPT_DRAFT_TO_PAYMENT_STAGE,
                MoveCurrentReceiptDraftToPaymentStageEvent(paymentDelegator, null),
                IntegrationManagerCallback {
                    it?.result?.error?.let { error -> callback.onError(ReceiptFormationException(error.code, error.message)) }
                            ?: run { callback.onSuccess() }
                }
        )
    }
}
