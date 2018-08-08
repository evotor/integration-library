package ru.evotor.framework.core.action.event.receipt.payment.combined.result

import android.os.Bundle

class PaymentDelegatorCanceledAllEventResult(
) : PaymentDelegatorEventResult(ResultType.CANCEL_ALL) {
    companion object {

        fun create(bundle: Bundle?): PaymentDelegatorCanceledAllEventResult? {
            if (bundle == null) {
                return null
            }
            return PaymentDelegatorCanceledAllEventResult()
        }
    }
}