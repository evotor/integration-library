package ru.evotor.framework.device.cash_drawer.event

import android.os.Bundle

/**
 * Событие открытия денежного ящика.
 *
 * Происходит при открытии денежного ящика, подключённого к смарт-терминалу.
 *
 * Обрабатывать это событие можно с помощью широковещательного приёмника событий денежного ящика:
 * [ru.evotor.framework.device.cash_drawer.event.handler.receiver.CashDrawerBroadcastReceiver]
 *
 * @param cashDrawerId идентификатор денежного ящика (по умолчанию -1)
 */
class CashDrawerOpenedEvent(cashDrawerId: Int) : CashDrawerEvent(cashDrawerId) {
    companion object {
        fun from(bundle: Bundle?): CashDrawerOpenedEvent? = bundle?.let {
            CashDrawerOpenedEvent(CashDrawerEvent.getCashDrawerId(it))
        }
    }
}