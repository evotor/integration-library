package ru.evotor.framework.device.cash_drawer.event

import android.os.Bundle

import ru.evotor.IBundlable

abstract class CashDrawerEvent constructor(val cashDrawerId: Int) : IBundlable {

    constructor(extras: Bundle) : this(extras.getInt(KEY_CASH_DRAWER_ID, -1))

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putInt(KEY_CASH_DRAWER_ID, cashDrawerId)
        return result
    }

    companion object {
        private const val KEY_CASH_DRAWER_ID = "cashDrawerId"
    }
}
