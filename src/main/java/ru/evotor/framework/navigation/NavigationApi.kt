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
    private const val ACTION_EDIT_CORRECTION_INCOME = "evotor.intent.action.edit.correction.INCOME"
    private const val ACTION_EDIT_CORRECTION_OUTCOME =
        "evotor.intent.action.edit.correction.OUTCOME"
    private const val ACTION_EDIT_CORRECTION_RETURN_INCOME =
        "evotor.intent.action.edit.correction.RETURN_INCOME"
    private const val ACTION_EDIT_CORRECTION_RETURN_OUTCOME =
        "evotor.intent.action.edit.correction.RETURN_OUTCOME"
    private const val ACTION_PAYMENT_SELL = "evotor.intent.action.payment.SELL"
    private const val ACTION_PAYMENT_PAYBACK = "evotor.intent.action.payment.PAYBACK"
    private const val ACTION_PAYMENT_BUY = "evotor.intent.action.payment.BUY"
    private const val ACTION_PAYMENT_BUYBACK = "evotor.intent.action.payment.BUYBACK"
    private const val ACTION_PAYMENT_CORRECTION_INCOME =
        "evotor.intent.action.payment.correction.INCOME"
    private const val ACTION_PAYMENT_CORRECTION_OUTCOME =
        "evotor.intent.action.payment.correction.OUTCOME"
    private const val ACTION_PAYMENT_CORRECTION_RETURN_INCOME =
        "evotor.intent.action.payment.correction.RETURN_INCOME"
    private const val ACTION_PAYMENT_CORRECTION_RETURN_OUTCOME =
        "evotor.intent.action.payment.correction.RETURN_OUTCOME"
    private const val ACTION_SETTINGS_CASH_RECEIPT = "evotor.intent.action.settings.CASH_RECEIPT"
    private const val ACTION_REPORT_CASH_REGISTER = "evotor.intent.action.report.CASH_REGISTER"
    private const val ACTION_EDIT_PRODUCT = "evotor.intent.action.edit.PRODUCT"
    private const val ACTION_CHANGE_USER = "evotor.intent.action.user.CHANGE"
    private const val ACTION_PRODUCT_LIST = "evotor.intent.action.commodity.SELECT"

    const val EXTRA_SHOULD_LOCK_SCREEN = "shouldLockScreen"

    // extras for edit sell intent
    const val EXTRA_CLOSE_AFTER_OPERATION = "closeAfterOperation"

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
     * @param closeAfterOperation нужно ли закрыть окно редактирования чека продажи после того,
     * как чек был успешно зарегистрирован
     * @return intent
     */
    @JvmStatic
    fun createIntentForSellReceiptEdit(
        closeAfterOperation: Boolean = false
    ): Intent {
        return Intent(ACTION_EDIT_SELL).apply {
            putExtra(
                EXTRA_CLOSE_AFTER_OPERATION,
                closeAfterOperation
            )
        }
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
     * Создаёт `intent`, который открывает окно редактирования чека коррекции прихода.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForCorrectionIncomeReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_CORRECTION_INCOME)
    }

    /**
     * Создаёт `intent`, который открывает окно редактирования чека коррекции расхода.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForCorrectionOutcomeReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_CORRECTION_OUTCOME)
    }

    /**
     * Создаёт `intent`, который открывает окно редактирования чека коррекции возврата прихода.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForCorrectionReturnIncomeReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_CORRECTION_RETURN_INCOME)
    }

    /**
     * Создаёт `intent`, который открывает окно редактирования чека коррекции возврата расхода.
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForCorrectionReturnOutcomeReceiptEdit(): Intent {
        return Intent(ACTION_EDIT_CORRECTION_RETURN_OUTCOME)
    }

    /**
     * Создаёт `intent`, который открывает окно оплаты чека продажи.
     *
     * @param shouldLockScreen параметр запуска экрана оплаты, если 'true' то приложение вызвавшее этот Intent, будет запущено в lock-task
     * и нижняя навигационная панель будет недоступна (кроме кнопки Назад). Если передан 'false' навигационная нижняя панель будет доступна и lock-task не будет.
     * По-умолчанию 'false'
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForSellReceiptPayment(
        shouldLockScreen: Boolean = false
    ): Intent {
        return Intent(ACTION_PAYMENT_SELL).apply {
            putExtra(EXTRA_SHOULD_LOCK_SCREEN, shouldLockScreen)
        }
    }

    /**
     * Создаёт `intent`, который открывает окно оплаты чека возврата.
     *
     * @param shouldLockScreen параметр запуска экрана оплаты, если 'true' то приложение вызвавшее этот Intent, будет запущено в lock-task
     * и нижняя навигационная панель будет недоступна (кроме кнопки Назад). Если передан 'false' навигационная нижняя панель будет доступна и lock-task не будет.
     * По-умолчанию 'false'
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForPaybackReceiptPayment(
        shouldLockScreen: Boolean = false
    ): Intent {
        return Intent(ACTION_PAYMENT_PAYBACK).apply {
            putExtra(EXTRA_SHOULD_LOCK_SCREEN, shouldLockScreen)
        }
    }

    /**
     * Создаёт `intent`, который открывает окно оплаты чека покупки.
     *
     * @param shouldLockScreen параметр запуска экрана оплаты, если 'true' то приложение вызвавшее этот Intent, будет запущено в lock-task
     * и нижняя навигационная панель будет недоступна (кроме кнопки Назад). Если передан 'false' навигационная нижняя панель будет доступна и lock-task не будет.
     * По-умолчанию 'false'
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForBuyReceiptPayment(
        shouldLockScreen: Boolean = false
    ): Intent {
        return Intent(ACTION_PAYMENT_BUY).apply {
            putExtra(EXTRA_SHOULD_LOCK_SCREEN, shouldLockScreen)
        }
    }

    /**
     * Создаёт `intent`, который открывает окно оплаты чека возврата покупки.
     *
     * @param shouldLockScreen параметр запуска экрана оплаты, если 'true' то приложение вызвавшее этот Intent, будет запущено в lock-task
     * и нижняя навигационная панель будет недоступна (кроме кнопки Назад). Если передан 'false' навигационная нижняя панель будет доступна и lock-task не будет.
     * По-умолчанию 'false'
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForBuybackReceiptPayment(
        shouldLockScreen: Boolean = false
    ): Intent {
        return Intent(ACTION_PAYMENT_BUYBACK).apply {
            putExtra(EXTRA_SHOULD_LOCK_SCREEN, shouldLockScreen)
        }
    }


    /**
     * Создаёт `intent`, который открывает окно оплаты чека коррекции прихода.
     *
     * @param shouldLockScreen параметр запуска экрана оплаты, если 'true' то приложение вызвавшее этот Intent, будет запущено в lock-task
     * и нижняя навигационная панель будет недоступна (кроме кнопки Назад). Если передан 'false' навигационная нижняя панель будет доступна и lock-task не будет.
     * По-умолчанию 'false'
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForCorrectionIncomeReceiptPayment(
        shouldLockScreen: Boolean = false
    ): Intent {
        return Intent(ACTION_PAYMENT_CORRECTION_INCOME).apply {
            putExtra(EXTRA_SHOULD_LOCK_SCREEN, shouldLockScreen)
        }
    }


    /**
     * Создаёт `intent`, который открывает окно оплаты чека коррекции расхода.
     *
     * @param shouldLockScreen параметр запуска экрана оплаты, если 'true' то приложение вызвавшее этот Intent, будет запущено в lock-task
     * и нижняя навигационная панель будет недоступна (кроме кнопки Назад). Если передан 'false' навигационная нижняя панель будет доступна и lock-task не будет.
     * По-умолчанию 'false'
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForCorrectionOutcomeReceiptPayment(
        shouldLockScreen: Boolean = false
    ): Intent {
        return Intent(ACTION_PAYMENT_CORRECTION_OUTCOME).apply {
            putExtra(EXTRA_SHOULD_LOCK_SCREEN, shouldLockScreen)
        }
    }

    /**
     * Создаёт `intent`, который открывает окно оплаты чека коррекции возврата прихода.
     *
     * @param shouldLockScreen параметр запуска экрана оплаты, если 'true' то приложение вызвавшее этот Intent, будет запущено в lock-task
     * и нижняя навигационная панель будет недоступна (кроме кнопки Назад). Если передан 'false' навигационная нижняя панель будет доступна и lock-task не будет.
     * По-умолчанию 'false'
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForCorrectionReturnIncomeReceiptPayment(
        shouldLockScreen: Boolean = false
    ): Intent {
        return Intent(ACTION_PAYMENT_CORRECTION_RETURN_INCOME).apply {
            putExtra(EXTRA_SHOULD_LOCK_SCREEN, shouldLockScreen)
        }
    }


    /**
     * Создаёт `intent`, который открывает окно оплаты чека коррекции возврата расхода.
     *
     * @param shouldLockScreen параметр запуска экрана оплаты, если 'true' то приложение вызвавшее этот Intent, будет запущено в lock-task
     * и нижняя навигационная панель будет недоступна (кроме кнопки Назад). Если передан 'false' навигационная нижняя панель будет доступна и lock-task не будет.
     * По-умолчанию 'false'
     *
     * @return intent
     */
    @JvmStatic
    fun createIntentForCorrectionReturnOutcomeReceiptPayment(
        shouldLockScreen: Boolean = false
    ): Intent {
        return Intent(ACTION_PAYMENT_CORRECTION_RETURN_OUTCOME).apply {
            putExtra(EXTRA_SHOULD_LOCK_SCREEN, shouldLockScreen)
        }
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
