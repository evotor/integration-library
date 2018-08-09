package ru.evotor.framework.core.action.event.receipt.payment.combined.result

import android.os.Bundle
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra

class PaymentDelegatorCanceledAllEventResult(extra: SetExtra?) : PaymentDelegatorEventResult(ResultType.CANCEL_ALL, extra) {
    companion object {

        fun create(bundle: Bundle?): PaymentDelegatorCanceledAllEventResult? {
            if (bundle == null) {
                return null
            }
            return PaymentDelegatorCanceledAllEventResult(SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)))
        }
    }
}