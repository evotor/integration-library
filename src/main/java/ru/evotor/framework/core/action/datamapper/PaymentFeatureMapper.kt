package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.safeValueOf
import ru.evotor.framework.payment.PaymentFeature

object PaymentFeatureMapper {

    private const val KEY_PAYMENT_FEATURE = "paymentFeature"

    @JvmStatic
    fun toBundle(paymentFeature: PaymentFeature): Bundle =
            Bundle().apply {
                putString(KEY_PAYMENT_FEATURE, paymentFeature.name)
            }

    @JvmStatic
    fun fromBundle(bundle: Bundle?): PaymentFeature {
        val defaultPaymentFeature = PaymentFeature.CHECKOUT_FULL
        val fromBundlePaymentFeature: PaymentFeature? = safeValueOf<PaymentFeature>(bundle?.getString(KEY_PAYMENT_FEATURE))
        return fromBundlePaymentFeature ?: defaultPaymentFeature
    }
}