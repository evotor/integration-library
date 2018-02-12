package ru.evotor.framework.navigation

import android.content.Intent
import android.os.Bundle

object NavigationApi {
    private const val ACTION_EDIT_SELL = "evotor.intent.action.edit.SELL"
    private const val ACTION_EDIT_PAYBACK = "evotor.intent.action.edit.PAYBACK"
    private const val ACTION_PAYMENT_SELL = "evotor.intent.action.payment.SELL"
    private const val ACTION_PAYMENT_PAYBACK = "evotor.intent.action.payment.PAYBACK"
    private const val ACTION_SETTINGS_CASH_RECEIPT = "evotor.intent.action.settings.CASH_RECEIPT"
    private const val ACTION_REPORT_CASH_REGISTER = "evotor.intent.action.report.CASH_REGISTER"
    private const val ACTION_EDIT_PRODUCT = "evotor.intent.action.edit.PRODUCT"
    private const val ACTION_CHANGE_USER = "evotor.intent.action.user.CHANGE"

    // extras for new/edit commodity intent
    const val EXTRA_BARCODE = "barcode"
    const val EXTRA_PRODUCT_UUID = "productUuid"

    const val EXTRA_ADDED_PRODUCT_UUID = "added_product_uuid"

    /**
     * форма наполнения чека продажи
     */
    @JvmStatic
    fun createIntentForSellReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_SELL)
    }

    /**
     * форма наполнения чека возврата
     */
    @JvmStatic
    fun createIntentForPaybackReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_PAYBACK)
    }

    /**
     * форма оплаты чека продажи
     */
    @JvmStatic
    fun createIntentForSellReceiptPayment(): Intent {
        return Intent(ACTION_PAYMENT_SELL)
    }

    /**
     * форма оплаты чека возврата
     */
    @JvmStatic
    fun createIntentForPaybackReceiptPayment(): Intent {
        return Intent(ACTION_PAYMENT_PAYBACK)
    }

    /**
     * форма настроек кассового чека
     */
    @JvmStatic
    fun createIntentForCashReceiptSettings(): Intent {
        return Intent(ACTION_SETTINGS_CASH_RECEIPT)
    }

    /**
     * форма кассового отчёта
     */
    @JvmStatic
    fun createIntentForCashRegisterReport(): Intent {
        return Intent(ACTION_REPORT_CASH_REGISTER)
    }

    /**
     * форма смены пользователей
     */
    @JvmStatic
    fun createIntentForChangeUser(): Intent {
        return Intent(ACTION_CHANGE_USER)
    }

    /**
     * форма добавления нового товара
     */
    @JvmStatic
    fun createIntentForNewProduct(productBuilder: NewProductIntentBuilder): Intent {
        return productBuilder.build()
    }

    /**
     * форма редактирования товара товара
     */
    @JvmStatic
    fun createIntentForEditProduct(productBuilder: EditProductIntentBuilder): Intent {
        return productBuilder.build()
    }

    class NewProductIntentBuilder {
        private var barcode: String? = null

        fun setBarcode(barcode: String): NewProductIntentBuilder {
            this.barcode = barcode
            return this
        }

        @JvmSynthetic
        internal fun build() = Intent(ACTION_EDIT_PRODUCT).apply {
            val bundle = Bundle()
            barcode.let {
                bundle.putString(EXTRA_BARCODE, barcode)
            }
            putExtras(bundle)
        }
    }

    class EditProductIntentBuilder {
        private var uuid: String? = null

        fun setUuid(uuid: String): EditProductIntentBuilder {
            this.uuid = uuid
            return this
        }

        @JvmSynthetic
        internal fun build() = Intent(ACTION_EDIT_PRODUCT).apply {
            val bundle = Bundle()
            uuid.let {
                bundle.putString(EXTRA_PRODUCT_UUID, uuid)
            }
            putExtras(bundle)
        }
    }


}