package ru.evotor.framework.core.action.event.receipt.payment.combined.result

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.Utils

abstract class PaymentDelegatorEventResult(
        val resultType: ResultType
) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RESULT_TYPE, resultType.name)
        return result
    }

    public enum class ResultType {
        UNKNOWN, SELECTED, CANCEL, CANCEL_ALL
    }

    companion object {
        private val KEY_RESULT_TYPE = "resultType"

        fun create(bundle: Bundle?): PaymentDelegatorEventResult? {
            if (bundle == null) {
                return null
            }
            val resultType = Utils.safeValueOf(ResultType::class.java, bundle.getString(KEY_RESULT_TYPE, null), ResultType.UNKNOWN)
            return when (resultType) {
                ResultType.SELECTED -> PaymentDelegatorSelectedEventResult.create(bundle)
                ResultType.CANCEL -> PaymentDelegatorCanceledEventResult.create(bundle)
                ResultType.CANCEL_ALL -> PaymentDelegatorCanceledAllEventResult.create(bundle)
                else -> null
            }
        }
    }
}