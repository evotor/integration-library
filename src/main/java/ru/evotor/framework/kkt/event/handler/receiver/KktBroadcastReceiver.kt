package ru.evotor.framework.kkt.event.handler.receiver

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.core.BroadcastEventReceiver
import ru.evotor.framework.core.action.event.cash_operations.CashInEvent
import ru.evotor.framework.core.action.event.cash_operations.CashOutEvent

/**
 * Широковещательный приёмник событий кассы.
 * @see <a href="https://developer.evotor.ru/docs/beta/doc_java_broadcastreceiver.html">Использование широковещательного приёмника</a>
 */
open class KktBroadcastReceiver : BroadcastEventReceiver() {

    /**
     * Обработчик событий внесения наличности в кассу.
     */
    @RequiresIntentAction(ACTION_CASH_IN)
    protected open fun handleCashInEvent(context: Context, event: CashInEvent) = Unit

    /**
     * Обработчик событий изъятия наличности из кассы.
     */
    @RequiresIntentAction(ACTION_CASH_OUT)
    protected open fun handleCashOutEvent(context: Context, event: CashOutEvent) = Unit

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