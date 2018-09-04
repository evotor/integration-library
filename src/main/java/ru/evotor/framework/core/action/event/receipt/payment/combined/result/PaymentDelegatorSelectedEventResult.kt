package ru.evotor.framework.core.action.event.receipt.payment.combined.result

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.PaymentPurposeMapper
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra
import ru.evotor.framework.payment.PaymentPurpose

class PaymentDelegatorSelectedEventResult(
        val paymentPurpose: PaymentPurpose,
        extra: SetExtra?
) : PaymentDelegatorEventResult(ResultType.SELECTED, extra) {
    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putBundle(KEY_PAYMENT_PURPOSE, PaymentPurposeMapper.toBundle(paymentPurpose))
        return result
    }

    companion object {
        private const val KEY_PAYMENT_PURPOSE = "paymentPurpose"

        fun create(bundle: Bundle?): PaymentDelegatorSelectedEventResult? {
            if (bundle == null) {
                return null
            }
            val paymentPurpose = bundle.getBundle(KEY_PAYMENT_PURPOSE)
                    ?.let { PaymentPurposeMapper.from(it) }
            if (paymentPurpose != null) {
                return PaymentDelegatorSelectedEventResult(
                        paymentPurpose,
                        SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA))
                )
            }
            return null
        }
    }
}