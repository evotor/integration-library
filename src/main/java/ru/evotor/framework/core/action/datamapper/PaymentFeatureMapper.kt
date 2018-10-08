package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.payment.PaymentFeature

object PaymentFeatureMapper {

    private const val KEY_PAYMENT_FEATURE = "paymentFeature"

    @JvmStatic
    fun toBundle(paymentFeature: PaymentFeature): Bundle =
            Bundle().apply {
                putParcelable(KEY_PAYMENT_FEATURE, paymentFeature)
            }

    @JvmStatic
    fun fromBundle(bundle: Bundle?): PaymentFeature {
        val defaultPaymentFeature = PaymentFeature.CheckoutFull()

        val paymentFeature = bundle?.let {
            it.getParcelable<PaymentFeature>(KEY_PAYMENT_FEATURE)
        }

        return paymentFeature ?: defaultPaymentFeature
    }
}