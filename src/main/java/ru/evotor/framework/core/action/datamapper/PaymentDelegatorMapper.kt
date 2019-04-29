package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.component.PaymentDelegator

object PaymentDelegatorMapper {
    fun fromBundle(bundle: Bundle?) =
            bundle?.let {
                PaymentDelegator(
                        IntegrationComponentMapper.readPackageName(bundle),
                        IntegrationComponentMapper.readComponentName(bundle),
                        IntegrationComponentMapper.readAppUuid(bundle),
                        IntegrationComponentMapper.readAppName(bundle)
                )
            }

    fun toBundle(paymentDelegator: PaymentDelegator) =
            IntegrationComponentMapper.toBundle(paymentDelegator)
}