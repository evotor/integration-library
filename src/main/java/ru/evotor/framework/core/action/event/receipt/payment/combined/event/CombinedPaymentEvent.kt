package ru.evotor.framework.core.action.event.receipt.payment.combined.event

import android.os.Bundle

class CombinedPaymentEvent() {
    companion object {
        const val NAME_SELL_RECEIPT = "evo.v2.receipt.sell.payment.COMBINED"
        fun create(bundle: Bundle?): CombinedPaymentEvent? {
            if (bundle == null) {
                return null
            }
            return CombinedPaymentEvent()
        }
    }
}