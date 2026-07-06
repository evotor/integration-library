package ru.evotor.framework.core.action.event.receipt.payment.system.result

import android.os.Bundle
import ru.evotor.framework.core.IntegrationLibraryParsingException
import ru.evotor.framework.payment.PaymentType

class PaymentIntentRequestedOkResult(
        val paymentType: PaymentType = PaymentType.ELECTRON
) : PaymentIntentRequestedResult(ResultType.OK) {

    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putString(KEY_PAYMENT_TYPE, paymentType.name)
        return result
    }

    companion object {
        private const val KEY_PAYMENT_TYPE = "paymentType"

        fun create(bundle: Bundle?): PaymentIntentRequestedOkResult? = bundle?.let {
            val paymentType = PaymentType.valueOf(it.getString(KEY_PAYMENT_TYPE)
                ?: throw IntegrationLibraryParsingException(PaymentIntentRequestedOkResult::class.java))
            PaymentIntentRequestedOkResult(paymentType)
        }
    }
}
