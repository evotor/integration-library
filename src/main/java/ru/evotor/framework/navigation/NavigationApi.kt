package ru.evotor.framework.navigation

import android.content.Intent

object NavigationApi {
    private const val ACTION_EDIT_SELL = "evotor.intent.action.edit.SELL"
    private const val ACTION_EDIT_PAYBACK = "evotor.intent.action.edit.PAYBACK"
    private const val ACTION_EDIT_BUY = "evotor.intent.action.edit.BUY"
    private const val ACTION_EDIT_BUYBACK = "evotor.intent.action.edit.BUYBACK"
    private const val ACTION_PAYMENT_SELL = "evotor.intent.action.payment.SELL"
    private const val ACTION_PAYMENT_PAYBACK = "evotor.intent.action.payment.PAYBACK"
    private const val ACTION_PAYMENT_BUY = "evotor.intent.action.payment.BUY"
    private const val ACTION_PAYMENT_BUYBACK = "evotor.intent.action.payment.BUYBACK"
    private const val ACTION_SETTINGS_CASH_RECEIPT = "evotor.intent.action.settings.CASH_RECEIPT"
    private const val ACTION_REPORT_CASH_REGISTER = "evotor.intent.action.report.CASH_REGISTER"
    private const val ACTION_EDIT_PRODUCT = "evotor.intent.action.edit.PRODUCT"
    private const val ACTION_CHANGE_USER = "evotor.intent.action.user.CHANGE"

    // extras for new/edit commodity intent
    const val EXTRA_BARCODE = "barcode"
    const val EXTRA_PRODUCT_UUID = "productUuid"

    /**
     * ключ для получения uuid продукта при успешном добавлении
     */
    const val EXTRA_ADDED_PRODUCT_UUID = "addedProductUuid"

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
     * форма наполнения чека покупки
     */
    @JvmStatic
    fun createIntentForBuyReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_BUY)
    }

    /**
     * форма наполнения чека возврата покупки
     */
    @JvmStatic
    fun createIntentForBuybackReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_BUYBACK)
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
     * форма оплаты чека покупки
     */
    @JvmStatic
    fun createIntentForBuyReceiptPayment(): Intent {
        return Intent(ACTION_PAYMENT_BUY)
    }

    /**
     * форма оплаты чека возврата покупки
     */
    @JvmStatic
    fun createIntentForBuybackReceiptPayment(): Intent {
        return Intent(ACTION_PAYMENT_BUYBACK)
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
     * форма редактирования товара
     */
    @JvmStatic
    fun createIntentForEditProduct(productBuilder: EditProductIntentBuilder): Intent {
        return productBuilder.build()
    }

    /**
     * полуение uuid продукта при успешном добавлении
     */
    @JvmStatic
    fun getProductUuid(intent: Intent): String? {
        return intent.getStringExtra(EXTRA_ADDED_PRODUCT_UUID)
    }

    class NewProductIntentBuilder {
        private var barcode: String? = null

        fun setBarcode(barcode: String?): NewProductIntentBuilder {
            this.barcode = barcode
            return this
        }

        @JvmSynthetic
        internal fun build() = Intent(ACTION_EDIT_PRODUCT).apply {
            barcode?.let {
                putExtra(EXTRA_BARCODE, it)
            }
        }
    }

    class EditProductIntentBuilder {
        private lateinit var uuid: String

        fun setUuid(uuid: String): EditProductIntentBuilder {
            this.uuid = uuid
            return this
        }

        @JvmSynthetic
        internal fun build() = Intent(ACTION_EDIT_PRODUCT).apply {
            putExtra(EXTRA_PRODUCT_UUID, uuid)
        }
    }


}