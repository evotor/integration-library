package ru.evotor.framework.core.action.event.receipt.payment.combined.result

import android.os.Bundle
import ru.evotor.framework.component.PaymentDelegator
import ru.evotor.framework.core.action.datamapper.PaymentDelegatorMapper
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetExtra

class PaymentDelegatorForwardedEventResult(
    val paymentDelegator: PaymentDelegator,
    extra: SetExtra?
) : PaymentDelegatorEventResult(ResultType.FORWARDED, extra) {
    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putBundle(KEY_PAYMENT_DELEGATOR, PaymentDelegatorMapper.toBundle(paymentDelegator))
        return result
    }

    companion object {
        private const val KEY_PAYMENT_DELEGATOR = "paymentDelegator"

        fun create(bundle: Bundle?): PaymentDelegatorForwardedEventResult? {
            if (bundle == null) {
                return null
            }
            val paymentDelegator = bundle.getBundle(KEY_PAYMENT_DELEGATOR)
                ?.let { PaymentDelegator.from(it) }
            if (paymentDelegator != null) {
                return PaymentDelegatorForwardedEventResult(
                    paymentDelegator,
                    SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA))
                )
            }
            return null
        }
    }
}
