package ru.evotor.framework.receipt

import android.net.Uri
import ru.evotor.framework.payment.PaymentApi

object PaymentTable {
    @JvmField val URI = Uri.withAppendedPath(PaymentApi.BASE_URI, "paymentSystems")
    const val ROW_PAYMENT_SYSTEM_USER_DESCRIPTION = "PAYMENT_SYSTEM_USER_DESCRIPTION"
    const val ROW_PAYMENT_TYPE = "PAYMENT_TYPE"
    const val ROW_ACCOUNT_ID = "ACCOUNT_ID"
    const val ROW_ACCOUNT_USER_DESCRIPTION = "ACCOUNT_USER_DESCRIPTION"
}
