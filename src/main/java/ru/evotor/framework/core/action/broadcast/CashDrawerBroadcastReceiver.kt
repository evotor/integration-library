package ru.evotor.framework.core.action.broadcast

import android.os.Bundle

/**
 * Класс, получающий широковещательные сообщения о событиях денежного ящика.
 */
abstract class CashDrawerBroadcastReceiver : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий открытия денежного ящика.
     */
    protected abstract fun handleCashDrawerOpenedEvent(cashDrawerId: Int)

    final override fun onEvent(action: String, bundle: Bundle) {
        handleCashDrawerOpenedEvent(bundle.getInt(KEY_CASH_DRAWER_ID))
    }

    companion object {

        const val ACTION_CASH_DRAWER_OPENED = "evotor.intent.action.cashDrawer.OPEN"

        private const val KEY_CASH_DRAWER_ID = "cashDrawerId"

    }

}