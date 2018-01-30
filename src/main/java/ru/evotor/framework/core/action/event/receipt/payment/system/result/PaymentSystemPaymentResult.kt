package ru.evotor.framework.core.action.event.receipt.payment.system.result

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.Utils

abstract class PaymentSystemPaymentResult(
        val resultType: ResultType
) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RESULT_TYPE, resultType.name)
        return result
    }

    public enum class ResultType {
        UNKNOWN, OK, ERROR
    }

    companion object {
        private val KEY_RESULT_TYPE = "resultType"

        fun create(bundle: Bundle?): PaymentSystemPaymentResult? {
            if (bundle == null) {
                return null
            }
            val resultType = Utils.safeValueOf(ResultType::class.java, bundle.getString(KEY_RESULT_TYPE, null), ResultType.UNKNOWN)
            return when (resultType) {
                ResultType.OK -> PaymentSystemPaymentOkResult.create(bundle)
                ResultType.ERROR -> PaymentSystemPaymentErrorResult.create(bundle)
                else -> null
            }
        }
    }
}