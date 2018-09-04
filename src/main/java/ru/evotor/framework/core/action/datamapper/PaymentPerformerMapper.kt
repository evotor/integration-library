package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.component.PaymentPerformer

object PaymentPerformerMapper {
    private const val KEY_PAYMENT_SYSTEM = "paymentSystem"
    private const val KEY_COMPONENT_NAME = "componentName"
    private const val KEY_APP_UUID = "appUuid"
    private const val KEY_APP_NAME = "appName"
    private const val KEY_PACKAGE_NAME = "packageName"

    fun toBundle(paymentPerformer: PaymentPerformer?): Bundle? =
            paymentPerformer?.let {
                Bundle().apply {
                    putString(KEY_PACKAGE_NAME, paymentPerformer.packageName)
                    putString(KEY_COMPONENT_NAME, paymentPerformer.componentName)
                    putString(KEY_APP_UUID, paymentPerformer.appUuid)
                    putString(KEY_APP_NAME, paymentPerformer.appName)
                    putBundle(KEY_PAYMENT_SYSTEM, PaymentSystemMapper.toBundle(paymentPerformer?.paymentSystem))
                }
            }

    fun fromBundle(bundle: Bundle?): PaymentPerformer? =
            bundle?.let {
                PaymentPerformer(
                        PaymentSystemMapper.from(bundle.getBundle(KEY_PAYMENT_SYSTEM)),
                        bundle.getString(KEY_PACKAGE_NAME),
                        bundle.getString(KEY_COMPONENT_NAME),
                        bundle.getString(KEY_APP_UUID),
                        bundle.getString(KEY_APP_NAME)
                )
            }
}