package ru.evotor.framework.core.action.event.receipt.payment.combined.result

import android.os.Bundle
import ru.evotor.framework.component.PaymentPerformer
import ru.evotor.framework.core.action.datamapper.BundleUtils
import ru.evotor.framework.core.action.datamapper.PaymentPerformerMapper
import java.math.BigDecimal

class PaymentDelegatorSelectedEventResult(
        val paymentPerformer: PaymentPerformer,
        val sum: BigDecimal
) : PaymentDelegatorEventResult(ResultType.SELECTED) {
    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putBundle(KEY_PAYMENT_APP, PaymentPerformerMapper.toBundle(paymentPerformer))
        result.putString(KEY_SUM, sum.toPlainString())
        return result
    }

    companion object {
        private const val KEY_PAYMENT_APP = "paymentApp"
        private const val KEY_SUM = "sum"

        fun create(bundle: Bundle?): PaymentDelegatorSelectedEventResult? {
            if (bundle == null) {
                return null
            }
            val paymentApp = bundle.getBundle(KEY_PAYMENT_APP)
                    ?.let { PaymentPerformerMapper.fromBundle(it) }
            val sum = BundleUtils.getMoney(bundle, KEY_SUM)
            if (paymentApp != null && sum != null) {
                return PaymentDelegatorSelectedEventResult(paymentApp, sum)
            }
            return null
        }
    }
}