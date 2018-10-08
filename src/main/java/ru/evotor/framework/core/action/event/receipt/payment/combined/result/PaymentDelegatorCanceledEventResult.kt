package ru.evotor.framework.core.action.event.receipt.payment.combined.result

import android.os.Bundle
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra

class PaymentDelegatorCanceledEventResult(
        val paymentUuid: String,
        extra: SetExtra?
) : PaymentDelegatorEventResult(ResultType.CANCEL, extra) {
    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putString(KEY_PAYMENT_UUID, paymentUuid)
        return result
    }

    companion object {
        private const val KEY_PAYMENT_UUID = "paymentUuid"

        fun create(bundle: Bundle?): PaymentDelegatorCanceledEventResult? {
            if (bundle == null) {
                return null
            }
            return bundle.getString(KEY_PAYMENT_UUID)
                    ?.let {
                        PaymentDelegatorCanceledEventResult(
                                it,
                                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)))
                    }
        }
    }
}