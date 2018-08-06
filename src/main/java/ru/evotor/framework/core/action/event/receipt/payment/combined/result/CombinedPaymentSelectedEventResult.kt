package ru.evotor.framework.core.action.event.receipt.payment.combined.result

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.BundleUtils
import ru.evotor.framework.core.action.datamapper.PaymentSystemMapper
import ru.evotor.framework.payment.PaymentSystem
import java.math.BigDecimal

class CombinedPaymentSelectedEventResult(
        val paymentSystem: PaymentSystem,
        val sum: BigDecimal
): CombinedPaymentEventResult(ResultType.SELECTED) {
    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putBundle(KEY_PAYMENT_SYSTEM, PaymentSystemMapper.toBundle(paymentSystem))
        result.putString(KEY_SUM, sum.toPlainString())
        return result
    }

    companion object {
        private const val KEY_PAYMENT_SYSTEM = "paymentSystem"
        private const val KEY_SUM = "sum"

        fun create(bundle: Bundle?): CombinedPaymentSelectedEventResult? {
            if (bundle == null) {
                return null
            }
            val paymentSystem = PaymentSystemMapper.from(bundle.getBundle(KEY_PAYMENT_SYSTEM))
            val sum = BundleUtils.getMoney(bundle, KEY_SUM)
            if (paymentSystem != null && sum != null) {
                return CombinedPaymentSelectedEventResult(paymentSystem, sum)
            }
            return null
        }
    }
}