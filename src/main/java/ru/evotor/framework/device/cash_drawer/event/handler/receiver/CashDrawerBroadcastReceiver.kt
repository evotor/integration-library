package ru.evotor.framework.device.cash_drawer.event.handler.receiver

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.core.BroadcastEventReceiver
import ru.evotor.framework.device.cash_drawer.event.CashDrawerOpenedEvent

/**
 * Широковещательный приёмник событий денежного ящика.
 * @see <a href="https://developer.evotor.ru/docs/beta/doc_java_broadcastreceiver.html">Использование широковещательного приёмника</a>
 */
abstract class CashDrawerBroadcastReceiver : BroadcastEventReceiver() {

    /**
     * Обработчик событий открытия денежного ящика.
     */
    @RequiresIntentAction(ACTION_CASH_DRAWER_OPENED)
    protected abstract fun handleCashDrawerOpenedEvent(context: Context, event: CashDrawerOpenedEvent)

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        when (action) {
            ACTION_CASH_DRAWER_OPENED -> handleCashDrawerOpenedEvent(context, CashDrawerOpenedEvent.from(bundle)
                    ?: return)
        }
    }

    companion object {
        const val ACTION_CASH_DRAWER_OPENED = "evotor.intent.action.cashDrawer.OPEN"
    }

}