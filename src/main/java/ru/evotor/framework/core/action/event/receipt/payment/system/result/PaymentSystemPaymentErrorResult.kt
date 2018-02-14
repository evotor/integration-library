package ru.evotor.framework.core.action.event.receipt.payment.system.result

import android.os.Bundle

class PaymentSystemPaymentErrorResult(
        val errorDescription: String?
) : PaymentSystemPaymentResult(ResultType.ERROR) {

    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putString(KEY_ERROR_DESCRIPTION, errorDescription)
        return result
    }

    companion object {
        private val KEY_ERROR_DESCRIPTION = "errorDescription"

        fun create(bundle: Bundle?): PaymentSystemPaymentErrorResult? {
            if (bundle == null) {
                return null
            }
            val errorDescription = bundle.getString(KEY_ERROR_DESCRIPTION, null)
            return PaymentSystemPaymentErrorResult(errorDescription)
        }
    }
}