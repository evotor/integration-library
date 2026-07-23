package ru.evotor.framework.core.action.event.receipt.payment.system.result

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.Utils

abstract class PaymentIntentRequestedResult(
    val resultType: ResultType
) : IBundlable {
    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RESULT_TYPE, resultType.name)
        return result
    }

    enum class ResultType {
        UNKNOWN,
        OK,
        ERROR
    }

    companion object {
        private const val KEY_RESULT_TYPE = "resultType"

        fun create(bundle: Bundle?): PaymentIntentRequestedResult? {
            if (bundle == null) {
                return null
            }
            val resultType = Utils.safeValueOf(ResultType::class.java, bundle.getString(KEY_RESULT_TYPE, null), ResultType.UNKNOWN)
            return when (resultType) {
                ResultType.OK -> PaymentIntentRequestedOkResult.create(bundle)
                ResultType.ERROR -> PaymentIntentRequestedErrorResult.create(bundle)
                else -> null
            }
        }
    }
}
