package ru.evotor.framework.payment

import android.net.Uri

object PaymentTable {
    @JvmField val URI = Uri.withAppendedPath(PaymentSystemApi.BASE_URI, "paymentSystems")
    const val PAYMENT_SYSTEM_ID = "PAYMENT_SYSTEM_ID"
    const val ROW_PAYMENT_SYSTEM_USER_DESCRIPTION = "PAYMENT_SYSTEM_USER_DESCRIPTION"
    const val ROW_PAYMENT_TYPE = "PAYMENT_TYPE"
    const val ROW_ACCOUNT_ID = "ACCOUNT_ID"
    const val ROW_ACCOUNT_USER_DESCRIPTION = "ACCOUNT_USER_DESCRIPTION"
}
