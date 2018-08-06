package ru.evotor.framework.core.action.event.receipt.payment.combined.result

import android.os.Bundle

class CombinedPaymentCanceledAllEventResult(
) : CombinedPaymentEventResult(ResultType.CANCEL_ALL) {
    companion object {

        fun create(bundle: Bundle?): CombinedPaymentCanceledAllEventResult? {
            if (bundle == null) {
                return null
            }
            return CombinedPaymentCanceledAllEventResult()
        }
    }
}