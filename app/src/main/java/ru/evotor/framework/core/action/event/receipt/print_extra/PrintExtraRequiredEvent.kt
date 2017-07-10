package ru.evotor.framework.core.action.event.receipt.print_extra

import android.os.Bundle
import ru.evotor.IBundlable

class PrintExtraRequiredEvent() : IBundlable {

    override fun toBundle(): Bundle {
        return Bundle()
    }

    companion object {
        @JvmField
        val NAME_PERMISSION = "ru.evotor.permission.receipt.printExtra.SET"
        @JvmField
        val NAME_SELL_RECEIPT = "evo.v2.receipt.sell.printExtra.REQUIRED"
        @JvmField
        val NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.printExtra.REQUIRED"

        fun create(bundle: Bundle?): PrintExtraRequiredEvent? {
            if (bundle == null) {
                return null
            }

            return PrintExtraRequiredEvent()
        }
    }
}