package ru.evotor.framework.features

import android.content.Context
import android.net.Uri
import ru.evotor.framework.features.provider.FeaturesContract

/**
 * Интерфейс для проверки статуса функций, которые активируются после приобретения (продления подписки) и установки приложения "Пакет обновлений".
 *
 * Установка и оплата приложения "Пакет обновлений" активирует:
 *
 * возможность работать по ставке НДС 20%;
 * поддержку ФФД 1.05;
 * маркировку табака;
 * возможность добавлять в чек реквизиты покупателя.
 */
object FeaturesApi {

    /**
     * Проверяет, активна ли на смарт-терминале функция "НДС 20%".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isVat20Active(context: Context) = isFeatureActive(context, FeaturesContract.PATH_VAT20)

    /**
     * Проверяет, активна ли на смарт-терминале функция "Переход на ФФД 1.05".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isFfd105Active(context: Context) = isFeatureActive(context, FeaturesContract.PATH_FFD105)

    /**
     * Проверяет, активна ли на смарт-терминале функция "Маркировка табака".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isTobaccomarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_TOBACCOMARK)

    /**
     * Проверяет, активна ли на смарт-терминале функция "Реквизиты покупателя".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isPurchaserActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_PURCHASER)

    /**
     * Проверяет, активна ли функция "Развозная торговля" на данном терминале.
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isDeliveryActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_DELIVERY)

    /**
     * Проверяет, активна ли функция "Компактный чек" на данном терминале
     * Требуется версия прошивки ФР >= 5086
     *
     * @return true если функция активна, false соответственно если функция не активна
     */
    fun isShortCheckActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_SHORT_CHECK)

    /**
     * Проверяет, активна ли функция "Маркировка алкоголя" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isAlcoMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_ALCO_MARK)

    /**
     * Проверяет, активна ли функция "Внешний УТМ" на данном терминале
     *
     * @return `true` если функция активна или данных о фиче нет; `false` если функция не активна.
     */
    fun isExternalUtmActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_EXTERNAL_UTM, true)

    /**
     * Проверяет, активна ли функция "Маркировка лекарств" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isMedicineMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_MEDICINE_MARK)

    /**
     * Проверяет, активна ли функция "Маркировка обуви" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isShoesMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_SHOES_MARK)

    /**
     * Проверяет, активна ли функция "Управленческие отчёты" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isManagementReportsActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_MANAGEMENT_REPORTS)

    /**
     * Проверяет, активна ли функция "Удаление документов" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isDocumentCleanActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_DOCUMENT_CLEAN)

    /**
     * Проверяет, активна ли функция "Тег 1162 для обычного товара" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isClassificationCodeActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_CLASSIFICATION_CODE)

    /**
     * Проверяет, активна ли функция "Маркировка шин" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isTyresMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_TYRES_MARK)

    /**
     * Проверяет, активна ли функция "Маркировка парфюмерии" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isPerfumeMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_PERFUMES_MARK)

    /**
     * Проверяет, активна ли функция "Маркировка фототоваров" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isPhotosMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_PHOTOS_MARK)

    /**
     * Проверяет, активна ли функция "Маркировка легкой промышленности" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isLightIndustryMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_LIGHT_INDUSTRY_MARK)

    /**
     * Проверяет, активна ли функция "Маркировка альтернативного табака" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isTobaccoProductsMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_TOBACCO_PRODUCTS_MARK)

    /**
     * Проверяет, активна ли функция "Маркировка молочной продукции" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isDairyMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_DAIRY_MARK)

    /**
     * Проверяет, активна ли функция "Маркировка воды" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isWaterMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_WATER_MARK)

    /**
     * Проверяет, активна ли функция "Маркировка велотоваров" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isBikeMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_BIKE_MARK)

    /**
     * Проверяет, активна ли функция "Маркировка ювелирных изделий" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isJewelryMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_JEWELRY_MARK)

    /**
     * Проверяет, активна ли функция "Маркировка меховых изделий" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isFurMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_FUR_MARK)

    /**
     * Проверяет, активна ли функция "Настройка количества печати слип-чеков" на данном терминале
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isSlipAmountActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_SLIP_AMOUNT)

    /**
     * Проверяет, активна ли на смарт-терминале функция "Переход на ФФД 1.2".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isFfd12Active(context: Context) = isFeatureActive(context, FeaturesContract.PATH_FFD12)

    /**
     * Проверяет, активна ли на смарт-терминале функция "Управление обновлениями".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isUpdateManagementActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_UPDATE_MANAGEMENT)

    /**
     * Проверяет, активна ли на смарт-терминале функция "Блокировка кассового ПО".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isFiscBlockActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_FISC_BLOCK)

    /**
     * Проверяет, активна ли на смарт-терминале функция "Полный кассовый функционал".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isFiscPaidActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_FISC_PAID)

    /**
     * Проверяет, активна ли на смарт-терминале функция "Прием платежей через СБП".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isSbpActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_SBP_ACTIVATION)

    private fun isFeatureActive(context: Context, path: String, defaultValue: Boolean = false): Boolean =
            context.contentResolver.query(
                    Uri.withAppendedPath(FeaturesContract.BASE_URI, path),
                    null,
                    null,
                    null,
                    null
            )?.use {
                it.moveToFirst()
                it.getInt(it.getColumnIndex(FeaturesContract.COLUMN_IS_ACTIVE)) == 1
            } ?: defaultValue
}
