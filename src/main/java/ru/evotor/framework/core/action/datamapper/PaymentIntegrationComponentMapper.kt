package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.component.PaymentIntegrationComponent

object PaymentIntegrationComponentMapper {
    private const val KEY_PAYMENT_SYSTEM = "paymentSystem"
    private const val KEY_COMPONENT_NAME = "componentName"
    private const val KEY_APP_UUID = "appUuid"
    private const val KEY_APP_NAME = "appName"
    private const val KEY_PACKAGE_NAME = "packageName"

    fun toBundle(paymentIntegrationComponent: PaymentIntegrationComponent?): Bundle? =
            paymentIntegrationComponent?.let {
                Bundle().apply {
                    putString(KEY_PACKAGE_NAME, paymentIntegrationComponent.packageName)
                    putString(KEY_COMPONENT_NAME, paymentIntegrationComponent.componentName)
                    putString(KEY_APP_UUID, paymentIntegrationComponent.appUuid)
                    putString(KEY_APP_NAME, paymentIntegrationComponent.appName)
                    putBundle(KEY_PAYMENT_SYSTEM, PaymentSystemMapper.toBundle(paymentIntegrationComponent?.paymentSystem))
                }
            }

    fun fromBundle(bundle: Bundle?): PaymentIntegrationComponent? =
            bundle?.let {
                PaymentIntegrationComponent(
                        PaymentSystemMapper.from(bundle.getBundle(KEY_PAYMENT_SYSTEM)),
                        bundle.getString(KEY_PACKAGE_NAME),
                        bundle.getString(KEY_COMPONENT_NAME),
                        bundle.getString(KEY_APP_UUID),
                        bundle.getString(KEY_APP_NAME)
                )
            }
}