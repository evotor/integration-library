package ru.evotor.framework.device.cash_drawer.event

import android.os.Bundle

class CashDrawerOpenedEvent(cashDrawerId: Int) : CashDrawerEvent(cashDrawerId) {
    companion object {
        fun from(bundle: Bundle?): CashDrawerOpenedEvent? = bundle?.let {
            CashDrawerOpenedEvent(CashDrawerEvent.getCashDrawerId(it))
        }
    }
}