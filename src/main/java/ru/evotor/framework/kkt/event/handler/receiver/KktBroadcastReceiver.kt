package ru.evotor.framework.kkt.event.handler.receiver

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.core.BroadcastEventReceiver
import ru.evotor.framework.kkt.event.CashInsertedEvent
import ru.evotor.framework.kkt.event.CashWithdrawnEvent

/**
 * Широковещательный приёмник событий кассы.
 * @see <a href="https://developer.evotor.ru/docs/beta/doc_java_broadcastreceiver.html">Использование широковещательного приёмника</a>
 */
open class KktBroadcastReceiver : BroadcastEventReceiver() {

    /**
     * Обработчик событий внесения наличности в кассу.
     */
    @RequiresIntentAction(ACTION_CASH_INSERTED)
    protected open fun handleCashInsertedEvent(context: Context, event: CashInsertedEvent) = Unit

    /**
     * Обработчик событий изъятия наличности из кассы.
     */
    @RequiresIntentAction(ACTION_CASH_WITHDRAWN)
    protected open fun handleCashWithdrawnEvent(context: Context, event: CashWithdrawnEvent) = Unit

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        when (action) {
            ACTION_CASH_INSERTED -> handleCashInsertedEvent(context, CashInsertedEvent.from(bundle) ?: return)
            ACTION_CASH_WITHDRAWN -> handleCashWithdrawnEvent(context, CashWithdrawnEvent.from(bundle) ?: return)
        }
    }

    companion object {

        const val ACTION_CASH_INSERTED = "evotor.intent.action.cashOperation.CASH_IN"

        const val ACTION_CASH_WITHDRAWN = "evotor.intent.action.cashOperation.CASH_OUT"

    }

}