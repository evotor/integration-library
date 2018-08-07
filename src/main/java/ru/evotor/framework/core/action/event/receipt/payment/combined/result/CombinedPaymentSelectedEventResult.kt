package ru.evotor.framework.core.action.event.receipt.payment.combined.result

import android.os.Bundle
import ru.evotor.framework.component.PaymentIntegrationComponent
import ru.evotor.framework.core.action.datamapper.BundleUtils
import ru.evotor.framework.core.action.datamapper.PaymentIntegrationComponentMapper
import java.math.BigDecimal

class CombinedPaymentSelectedEventResult(
        val paymentIntegrationComponent: PaymentIntegrationComponent,
        val sum: BigDecimal
) : CombinedPaymentEventResult(ResultType.SELECTED) {
    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putBundle(KEY_PAYMENT_APP, PaymentIntegrationComponentMapper.toBundle(paymentIntegrationComponent))
        result.putString(KEY_SUM, sum.toPlainString())
        return result
    }

    companion object {
        private const val KEY_PAYMENT_APP = "paymentApp"
        private const val KEY_SUM = "sum"

        fun create(bundle: Bundle?): CombinedPaymentSelectedEventResult? {
            if (bundle == null) {
                return null
            }
            val paymentApp = bundle.getBundle(KEY_PAYMENT_APP)
                    ?.let { PaymentIntegrationComponentMapper.fromBundle(it) }
            val sum = BundleUtils.getMoney(bundle, KEY_SUM)
            if (paymentApp != null && sum != null) {
                return CombinedPaymentSelectedEventResult(paymentApp, sum)
            }
            return null
        }
    }
}