package ru.evotor.framework.navigation

import android.content.Intent

object NavigationApi {

    @JvmStatic
    fun createIntentForSellReceiptEdit(): Intent {
        return Intent("evotor.intent.action.edit.SELL")
    }

    @JvmStatic
    fun createIntentForPaybackReceiptEdit(): Intent {
        return Intent("evotor.intent.action.edit.PAYBACK")
    }

    @JvmStatic
    fun createIntentForSellReceiptPayment(): Intent {
        return Intent("evotor.intent.action.payment.SELL")
    }

    @JvmStatic
    fun createIntentForPaybackReceiptPayment(): Intent {
        return Intent("evotor.intent.action.payment.PAYBACK")
    }

}