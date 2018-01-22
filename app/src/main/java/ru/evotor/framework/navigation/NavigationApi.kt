package ru.evotor.framework.navigation

import android.content.Intent
import android.os.Bundle

object NavigationApi {
    const val BROADCAST_ACTION_EDIT_SELL = "evotor.intent.action.edit.SELL"
    const val BROADCAST_ACTION_EDIT_PAYBACK = "evotor.intent.action.edit.PAYBACK"
    const val BROADCAST_ACTION_PAYMENT_SELL = "evotor.intent.action.payment.SELL"
    const val BROADCAST_ACTION_PAYMENT_PAYBACK = "evotor.intent.action.payment.PAYBACK"
    const val BROADCAST_ACTION_SETTINGS_CASH_RECEIPT = "evotor.intent.action.settings.CASH_RECEIPT"
    const val BROADCAST_ACTION_REPORT_CASH_REGISTER = "evotor.intent.action.report.CASH_REGISTER"
    const val BROADCAST_ACTION_EDIT_COMMODITY = "evotor.intent.action.edit.COMMODITY"

    // extras for edit commodity intent
    const val EXTRA_COMMODITY_UUID = "commodityUuid"
    const val EXTRA_TYPE = "type"
    const val EXTRA_PARENT_UUID = "parentUuid"
    const val EXTRA_ENABLE_AUTOGENERATE_ABBREVIATION = "enableAutoGenerateAbbreviation"
    const val EXTRA_COMMODITY_ID = "commodityId"
    const val EXTRA_MULTIPLY_ADDING = "multiplyAdding"
    const val EXTRA_SCROLL_TO_SWITCH_OFF_SELL_FORBIDDEN = "scrollToSwitchOffSellForbidden"
    const val EXTRA_NAME = "name"
    const val EXTRA_COMMODITY_CODE_STRING = "commodityCodeString"
    const val EXTRA_BARCODE_DATA = "barcodeData"
    const val EXTRA_BARCODE = "barcode"
    const val EXTRA_ALCO_CODE = "alcoCode"
    const val EXTRA_PRICE = "price"
    const val EXTRA_MEASURE_ID = "measureId"
    const val EXTRA_MEASURE_NAME = "measureName"
    const val EXTRA_ALCOHOL_BY_VOLUME = "alcoholByVolume"
    const val EXTRA_ALCOHOL_PRODUCT_KIND_CODE = "alcoholProductKindCode"
    const val EXTRA_TARE_VOLUME = "tareVolume"
    const val EXTRA_REQUEST_FROM_CLOUD = "requestFromCloud";

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
    fun createIntentForEditCommodity(bundle: Bundle?): Intent {
        return Intent(BROADCAST_ACTION_EDIT_COMMODITY).apply { bundle?.let { putExtras(bundle) } }
    }

    /**
     * форма смены пользователей
     */
    @JvmStatic
    fun createIntentForChangeUser(): Intent {
        return Intent("evotor.intent.action.user.CHANGE")
    }
}