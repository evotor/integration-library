package ru.evotor.framework.device.cash_drawer.event

import android.os.Bundle

class CashDrawerOpenedEvent : CashDrawerEvent {

    constructor(cashDrawerId: Int) : super(cashDrawerId)

    private constructor(extras: Bundle) : super(extras)

    companion object {

        fun from(bundle: Bundle?): CashDrawerOpenedEvent? = bundle?.let {
            CashDrawerOpenedEvent(bundle)
        }
    }
}