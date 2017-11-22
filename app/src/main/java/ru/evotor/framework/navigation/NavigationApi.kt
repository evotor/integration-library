package ru.evotor.framework.navigation

import android.content.Intent

object NavigationApi {
    const val BROADCAST_ACTION_EDIT_SELL = "evotor.intent.action.edit.SELL"
    const val BROADCAST_ACTION_EDIT_PAYBACK = "evotor.intent.action.edit.PAYBACK"
    const val BROADCAST_ACTION_PAYMENT_SELL = "evotor.intent.action.payment.SELL"
    const val BROADCAST_ACTION_PAYMENT_PAYBACK = "evotor.intent.action.payment.PAYBACK"
    const val BROADCAST_ACTION_SETTINGS_CASH_RECEIPT = "evotor.intent.action.settings.CASH_RECEIPT"
    const val BROADCAST_ACTION_REPORT_CASH_REGISTER = "evotor.intent.action.report.CASH_REGISTER"
    const val BROADCAST_ACTION_EDIT_COMMODITY = "evotor.intent.action.edit.COMMODITY"

    const val EXTRA_IN_COMMODITY_UUID = "inCommodityUuid"
    const val EXTRA_OUT_COMMODITY_UUID = "outCommodityUuid"

    /**
     * форма наполнения чека продажи
     */
    @JvmStatic
    fun createIntentForSellReceiptEdit(): Intent {
        return Intent(BROADCAST_ACTION_EDIT_SELL)
    }

    /**
     * форма наполнения чека возврата
     */
    @JvmStatic
    fun createIntentForPaybackReceiptEdit(): Intent {
        return Intent(BROADCAST_ACTION_EDIT_PAYBACK)
    }

    /**
     * форма оплаты чека продажи
     */
    @JvmStatic
    fun createIntentForSellReceiptPayment(): Intent {
        return Intent(BROADCAST_ACTION_PAYMENT_SELL)
    }

    /**
     * форма оплаты чека возврата
     */
    @JvmStatic
    fun createIntentForPaybackReceiptPayment(): Intent {
        return Intent(BROADCAST_ACTION_PAYMENT_PAYBACK)
    }

    /**
     * форма настроек кассового чека
     */
    @JvmStatic
    fun createIntentForCashReceiptSettings(): Intent {
        return Intent(BROADCAST_ACTION_SETTINGS_CASH_RECEIPT)
    }

    /**
     * форма кассового отчёта
     */
    @JvmStatic
    fun createIntentForCashRegisterReport(): Intent {
        return Intent(BROADCAST_ACTION_REPORT_CASH_REGISTER)
    }

    /**
     * форма редактора товара
     */
    @JvmStatic
    fun createIntentForEditCommodity(uuid: String?): Intent {
        return Intent(BROADCAST_ACTION_EDIT_COMMODITY).apply { uuid?.let { putExtra(EXTRA_IN_COMMODITY_UUID, uuid) } }
    }

}