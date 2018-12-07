package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.receipt.position.SettlementMethod

object PaymentFeatureMapper {

    private const val KEY_PAYMENT_FEATURE = "paymentFeature"

    @JvmStatic
    fun toBundle(settlementMethod: SettlementMethod): Bundle =
            Bundle().apply {
                putParcelable(KEY_PAYMENT_FEATURE, settlementMethod)
            }

    @JvmStatic
    fun fromBundle(bundle: Bundle?): SettlementMethod {
        val defaultPaymentFeature = SettlementMethod.FullSettlement()

        val paymentFeature = bundle?.let {
            it.classLoader = SettlementMethod::class.java.classLoader
            it.getParcelable<SettlementMethod>(KEY_PAYMENT_FEATURE)
        }

        return paymentFeature ?: defaultPaymentFeature
    }
}
