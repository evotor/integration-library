package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.component.PaymentPerformer

object PaymentPerformerMapper {
    private const val KEY_PAYMENT_SYSTEM = "paymentSystem"

    fun toBundle(paymentPerformer: PaymentPerformer): Bundle =
            IntegrationComponentMapper.toBundle(paymentPerformer).apply {
                putBundle(KEY_PAYMENT_SYSTEM, PaymentSystemMapper.toBundle(paymentPerformer.paymentSystem))
            }

    fun fromBundle(bundle: Bundle?): PaymentPerformer? =
            bundle?.let {
                PaymentPerformer(
                        PaymentSystemMapper.from(bundle.getBundle(KEY_PAYMENT_SYSTEM)),
                        IntegrationComponentMapper.readPackageName(bundle),
                        IntegrationComponentMapper.readComponentName(bundle),
                        IntegrationComponentMapper.readAppUuid(bundle),
                        IntegrationComponentMapper.readAppName(bundle)
                )
            }
}