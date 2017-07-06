package ru.evotor.framework.payment

import android.net.Uri

object PaymentSystemTable {
    @JvmField val URI = Uri.withAppendedPath(PaymentSystemApi.BASE_URI, "paymentSystems")
    const val COLUMN_PAYMENT_SYSTEM_ID = "PAYMENT_SYSTEM_ID"
    const val COLUMN_PAYMENT_SYSTEM_USER_DESCRIPTION = "PAYMENT_SYSTEM_USER_DESCRIPTION"
    const val COLUMN_PAYMENT_TYPE = "PAYMENT_TYPE"
    const val COLUMN_ACCOUNT_ID = "ACCOUNT_ID"
    const val COLUMN_ACCOUNT_USER_DESCRIPTION = "ACCOUNT_USER_DESCRIPTION"
}
