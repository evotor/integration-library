package ru.evotor.framework.core.action.broadcast

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.action.event.cash_drawer.CashDrawerOpenEvent

/**
 * Широковещательный приёмник событий денежного ящика.
 * @see <a href="https://developer.evotor.ru/docs/">Использование широковещательного приёмника</a>
 */
abstract class CashDrawerBroadcastReceiver : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий открытия денежного ящика.
     */
    @RequiresIntentAction(ACTION_CASH_DRAWER_OPENED)
    protected abstract fun handleCashDrawerOpenedEvent(context: Context, cashDrawerOpenedEvent: CashDrawerOpenEvent)

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        handleCashDrawerOpenedEvent(context, CashDrawerOpenEvent.create(bundle) ?: return)
    }

    companion object {
        const val ACTION_CASH_DRAWER_OPENED = "evotor.intent.action.cashDrawer.OPEN"
    }

}