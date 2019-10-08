package ru.evotor.framework.features

import android.content.Context
import android.net.Uri
import ru.evotor.framework.features.provider.FeaturesContract

/**
 * API для проверки наличия доп. функций
 */
object FeaturesApi {

    /**
     * Проверяет, активна ли функция "НДС 20%" на данном терминале
     *
     * @return true если функция активна, false соответственно если функция не активна
     */
    fun isVat20Active(context: Context) = isFeatureActive(context, FeaturesContract.PATH_VAT20)

    /**
     * Проверяет, активна ли функция "Переход на ФФД 1.05" на данном терминале
     *
     * @return true если функция активна, false соответственно если функция не активна
     */
    fun isFfd105Active(context: Context) = isFeatureActive(context, FeaturesContract.PATH_FFD105)

    /**
     * Проверяет, активна ли функция "Маркировка табака" на данном терминале
     *
     * @return true если функция активна, false соответственно если функция не активна
     */
    fun isTobaccomarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_TOBACCOMARK)

    /**
     * Проверяет, активна ли функция "Реквизиты покупателя" на данном терминале
     *
     * @return true если функция активна, false соответственно если функция не активна
     */
    fun isPurchaserActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_PURCHASER)

    /**
     * Проверяет, активна ли функция "Развозная торговля" на данном терминале
     *
     * @return true если функция активна, false соответственно если функция не активна
     */
    fun isDeliveryActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_DELIVERY)

    /**
     * Проверяет, активна ли функция "Компактный чек" на данном терминале
     * Требуется версия прошивки ФР >= 5086
     *
     * @return true если функция активна, false соответственно если функция не активна
     */
    fun isShortCheckActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_SHORT_CHECK)

    private fun isFeatureActive(context: Context, path: String): Boolean =
            context.contentResolver.query(
                    Uri.withAppendedPath(FeaturesContract.BASE_URI, path),
                    null,
                    null,
                    null,
                    null
            )?.use {
                it.moveToFirst()
                it.getInt(it.getColumnIndex(FeaturesContract.COLUMN_IS_ACTIVE)) == 1
            } ?: false
}