package ru.evotor.framework.core.action.event.receipt.payment.system.result

import android.os.Bundle

class PaymentIntentRequestedErrorResult(
    val errorDescription: String?
) : PaymentIntentRequestedResult(ResultType.ERROR) {
    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putString(KEY_ERROR_DESCRIPTION, errorDescription)
        return result
    }

    companion object {
        private const val KEY_ERROR_DESCRIPTION = "errorDescription"

        fun create(bundle: Bundle?): PaymentIntentRequestedErrorResult? = bundle?.let {
            PaymentIntentRequestedErrorResult(it.getString(KEY_ERROR_DESCRIPTION, null))
        }
    }
}
