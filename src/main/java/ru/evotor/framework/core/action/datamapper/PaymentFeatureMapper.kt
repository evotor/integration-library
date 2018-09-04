package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.Utils
import ru.evotor.framework.payment.PaymentFeature
import ru.evotor.framework.payment.PaymentFeatureType
import java.math.BigDecimal

object PaymentFeatureMapper {

    private const val KEY_PAYMENT_FEATURE_TYPE = "paymentFeatureType"
    private const val KEY_PAYMENT_FEATURE_AMOUNT = "paymentFeatureAmount"

    @JvmStatic
    fun toBundle(paymentFeature: PaymentFeature): Bundle =
            Bundle().apply {
                putString(KEY_PAYMENT_FEATURE_TYPE, paymentFeature.paymentFeatureType.name)
                putString(KEY_PAYMENT_FEATURE_AMOUNT, paymentFeature.amount?.toPlainString())
            }

    @JvmStatic
    fun fromBundle(bundle: Bundle?): PaymentFeature {
        val defaultPaymentFeatureType = PaymentFeatureType.UNKNOWN

        val paymentFeatureType = Utils.safeValueOf(PaymentFeatureType::class.java, bundle?.getString(KEY_PAYMENT_FEATURE_TYPE), PaymentFeatureType.UNKNOWN)
        val paymentFeatureAmount: BigDecimal? = bundle?.let {
            BundleUtils.getMoney(it, KEY_PAYMENT_FEATURE_AMOUNT)
        }

        return PaymentFeature(paymentFeatureType
                ?: defaultPaymentFeatureType, paymentFeatureAmount)
    }
}