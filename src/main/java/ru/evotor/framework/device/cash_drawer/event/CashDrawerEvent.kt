package ru.evotor.framework.device.cash_drawer.event

import android.os.Bundle

import ru.evotor.IBundlable

abstract class CashDrawerEvent internal constructor(val cashDrawerId: Int) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putInt(KEY_CASH_DRAWER_ID, cashDrawerId)
        return result
    }

    companion object {

        private const val KEY_CASH_DRAWER_ID = "cashDrawerId"

        internal fun getCashDrawerId(bundle: Bundle) = bundle.getInt(KEY_CASH_DRAWER_ID, -1)

    }
}
