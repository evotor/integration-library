package ru.evotor.framework.core.action.broadcast

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.action.event.cash_operations.CashInEvent
import ru.evotor.framework.core.action.event.cash_operations.CashOutEvent

/**
 * Широковещательный приёмник событий денежных операциях.
 * @see <a href="https://developer.evotor.ru/docs/beta/doc_java_broadcastreceiver.html">Использование широковещательного приёмника</a>
 */
open class CashOperationBroadcastReceiver : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий внесения наличности.
     */
    @RequiresIntentAction(ACTION_CASH_IN)
    protected open fun handleCashInEvent(context: Context, cashInEvent: CashInEvent) = Unit

    /**
     * Обработчик событий изъятия наличности.
     */
    @RequiresIntentAction(ACTION_CASH_OUT)
    protected open fun handleCashOutEvent(context: Context, cashOutEvent: CashOutEvent) = Unit

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        when (action) {
            ACTION_CASH_IN -> handleCashInEvent(context, CashInEvent.create(bundle) ?: return)
            ACTION_CASH_OUT -> handleCashOutEvent(context, CashOutEvent.create(bundle) ?: return)
        }
    }

    companion object {

        const val ACTION_CASH_IN = "evotor.intent.action.cashOperation.CASH_IN"

        const val ACTION_CASH_OUT = "evotor.intent.action.cashOperation.CASH_OUT"

    }

}