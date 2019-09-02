package ru.evotor.framework.navigation

import android.content.Intent

/**
 * Интерфейс для вызова различных окон смарт-терминала.
 *
 * @see <a href="https://developer.evotor.ru/docs/doc_java_navigation.html">Вызов окон смарт-терминала</a>
 */
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
    private const val ACTION_PRODUCT_LIST = "evotor.intent.action.commodity.SELECT"

    // extras for new/edit commodity intent
    const val EXTRA_BARCODE = "barcode"
    const val EXTRA_PRODUCT_UUID = "productUuid"

    /**
     * Ключ для получения идентификатора созданного товара.
     */
    const val EXTRA_ADDED_PRODUCT_UUID = "addedProductUuid"

    /**
     * Создаёт `intent`, который открывает окно редактирования чека продажи.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForSellReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_SELL)
    }

    /**
     * Создаёт `intent`, который открывает окно редактирования чека возврата.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForPaybackReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_PAYBACK)
    }

    /**
     * Создаёт `intent`, который открывает окно редактирования чека покупки.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForBuyReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_BUY)
    }

    /**
     * Создаёт `intent`, который открывает окно редактирования чека возврата покупки.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForBuybackReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_BUYBACK)
    }

    /**
     * Создаёт `intent`, который открывает окно оплаты чека продажи.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForSellReceiptPayment(): Intent {
        return Intent(ACTION_PAYMENT_SELL)
    }

    /**
     * Создаёт `intent`, который открывает окно оплаты чека возврата.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForPaybackReceiptPayment(): Intent {
        return Intent(ACTION_PAYMENT_PAYBACK)
    }

    /**
     * Создаёт `intent`, который открывает окно оплаты чека покупки.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForBuyReceiptPayment(): Intent {
        return Intent(ACTION_PAYMENT_BUY)
    }

    /**
     * Создаёт `intent`, который открывает окно оплаты чека возврата покупки.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForBuybackReceiptPayment(): Intent {
        return Intent(ACTION_PAYMENT_BUYBACK)
    }

    /**
     * Создаёт `intent`, который открывает окно настроек кассового чека.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForCashReceiptSettings(): Intent {
        return Intent(ACTION_SETTINGS_CASH_RECEIPT)
    }

    /**
     * Создаёт `intent`, который открывает окно кассового отчёта.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForCashRegisterReport(): Intent {
        return Intent(ACTION_REPORT_CASH_REGISTER)
    }

    /**
     * Создаёт `intent`, который открывает окно смены пользователей смарт-терминала.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForChangeUser(): Intent {
        return Intent(ACTION_CHANGE_USER)
    }

    /**
     * Создаёт `intent`, который открывает окно со списком товаров.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForProductList(): Intent {
        return Intent(ACTION_PRODUCT_LIST)
    }

    /**
     * Создаёт `intent`, который открывает окно создания нового товара.
     *
     * @param productBuilder экземпляр класса [NewProductIntentBuilder]. Позволяет задать штрихкод нового товара.
     */
    @JvmStatic
    fun createIntentForNewProduct(productBuilder: NewProductIntentBuilder): Intent {
        return productBuilder.build()
    }

    /**
     * Создаёт `intent`, который открывает окно редактирования товара.
     *
     * @param productBuilder экземпляр класса [EditProductIntentBuilder]. Позволяет указать штрихкод товара, который необходимо отредактировать.
     */
    @JvmStatic
    fun createIntentForEditProduct(productBuilder: EditProductIntentBuilder): Intent {
        return productBuilder.build()
    }

    /**
     * Получает идентификатор созданного товара.
     *
     * @param intent
     * @return идентификатор созданного товара.
     */
    @JvmStatic
    fun getProductUuid(intent: Intent): String? {
        return intent.getStringExtra(EXTRA_ADDED_PRODUCT_UUID)
    }

    /**
     * Вспомогательный класс, экземпляр которого передаётся в качестве параметра метода [createIntentForNewProduct].
     *
     * Позволяет задать штрихкод нового товара с помощью метода [setBarcode].
     */
    class NewProductIntentBuilder {
        private var barcode: String? = null

        /**
         * Задаёт штрихкод нового товара.
         *
         * Приложения могут получить штрихкод товара в событии [ru.evotor.framework.receipt.formation.event.ReturnPositionsForBarcodeRequestedEvent] или широковещательном сообщении [ru.evotor.framework.device.scanner.event.BarcodeReceivedEvent].
         *
         * @param barcode строка штрихкода. Если передать `null`, смарт-терминал предложит пользователю воспользоваться сканером штрихкодов или указать штрихкод вручную.
         */
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

    /**
     * Вспомогательный класс, экземпляр которого передаётся в качестве параметра метода [createIntentForEditProduct].
     *
     * Позволяет указать идентификатор редактируемого товара с помощью метода [setUuid].
     */
    class EditProductIntentBuilder {
        private lateinit var uuid: String

        /**
         * Указывает идентификатор товара, который необходимо отредактировать.
         *
         * @param uuid строка идентификатора товара.
         */
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