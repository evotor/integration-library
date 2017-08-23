package ru.evotor.framework.core.action.event.receipt.payment.system.result

import android.os.Bundle

class PaymentSystemPaymentOkResult(
        val rrn: String,
        val slip: List<String>
) : PaymentSystemPaymentResult(ResultType.OK) {

    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putString(KEY_RRN, rrn)
        result.putStringArrayList(KEY_SLIP, if (slip is ArrayList) slip else null)
        return result
    }

    companion object {
        private val KEY_RRN = "rrn"
        private val KEY_SLIP = "slip"

        fun create(bundle: Bundle?): PaymentSystemPaymentOkResult? {
            if (bundle == null) {
                return null
            }
            val rrn = bundle.getString(KEY_RRN, null)
            val slip = bundle.getStringArrayList(KEY_SLIP)
            return PaymentSystemPaymentOkResult(rrn, slip)
        }
    }
}