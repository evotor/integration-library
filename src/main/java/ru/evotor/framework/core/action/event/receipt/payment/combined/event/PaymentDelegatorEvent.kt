package ru.evotor.framework.core.action.event.receipt.payment.combined.event

import android.os.Bundle

class PaymentDelegatorEvent() {
    companion object {
        // TODO refactor action and permission
        const val NAME_ACTION = "evo.v2.receipt.sell.payment.COMBINED"
        const val NAME_PERMISSION = "ru.evotor.permission.COMBINED"
        fun create(bundle: Bundle?): PaymentDelegatorEvent? {
            if (bundle == null) {
                return null
            }
            return PaymentDelegatorEvent()
        }
    }
}