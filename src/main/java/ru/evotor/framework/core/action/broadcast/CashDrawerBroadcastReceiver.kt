package ru.evotor.framework.core.action.broadcast

import android.content.Context
import android.os.Bundle

/**
 * Широковещательный приёмник событий денежного ящика.
 * @see <a href="https://developer.evotor.ru/docs/tobi_pizda">Использование широковещательного приёмника</a>
 */
abstract class CashDrawerBroadcastReceiver : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий открытия денежного ящика.
     */
    @RequiresIntentAction(ACTION_CASH_DRAWER_OPENED)
    protected abstract fun handleCashDrawerOpenedEvent(context: Context, cashDrawerId: Int)

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        handleCashDrawerOpenedEvent(context, bundle.getInt(KEY_CASH_DRAWER_ID))
    }

    companion object {

        const val ACTION_CASH_DRAWER_OPENED = "evotor.intent.action.cashDrawer.OPEN"

        const val KEY_CASH_DRAWER_ID = "cashDrawerId"

    }

}