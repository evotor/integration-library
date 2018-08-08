package ru.evotor.framework.core.action.event.receipt.payment.combined.result

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.PaymentMapper
import ru.evotor.framework.receipt.Payment

class PaymentDelegatorCanceledEventResult(
        val payment: Payment
) : PaymentDelegatorEventResult(ResultType.CANCEL) {
    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putBundle(KEY_PAYMENT, PaymentMapper.toBundle(payment))
        return result
    }

    companion object {
        private const val KEY_PAYMENT = "payment"

        fun create(bundle: Bundle?): PaymentDelegatorCanceledEventResult? {
            if (bundle == null) {
                return null
            }
            return PaymentMapper.from(bundle.getBundle(KEY_PAYMENT))
                    ?.let { PaymentDelegatorCanceledEventResult(it) }
        }
    }
}