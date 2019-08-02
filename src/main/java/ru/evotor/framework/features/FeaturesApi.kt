package ru.evotor.framework.features

import android.content.Context
import android.net.Uri

/**
 * Интерфейс для проверки статуса функций, которые активируются после приобретения (продления подписки) и установки приложения "Пакет обновлений".
 * Установка и оплата приложения "Пакет обновлений" активирует:
 *
 * возможность работать по ставке НДС 20%;
 * поддержку ФФД 1.05;
 * маркировку табака;
 * возможность добавлять в чек реквизиты покупателя.
 */
object FeaturesApi {
    private const val BASE_PATH = "content://ru.evotor.paidupdates.FeaturesContentProvider"

    private const val PATH_VAT20 = "vat20"
    private const val PATH_FFD105 = "ffd105"
    private const val PATH_TOBACCOMARK = "tobaccomark"
    private const val PATH_PURCHASER = "purchaser"

    private const val COLUMN_IS_ACTIVE = "is_active"

    /**
     * Проверяет активна ли на смарт-терминале функция "НДС 20%".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isVat20Active(context: Context) = isFeatureActive(context, PATH_VAT20)

    /**
     * Проверяет активна ли на смарт-терминале функция "Переход на ФФД 1.05".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isFfd105Active(context: Context) = isFeatureActive(context, PATH_FFD105)

    /**
     * Проверяет активна ли на смарт-терминале функция "Маркировка табака".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isTobaccomarkActive(context: Context) = isFeatureActive(context, PATH_TOBACCOMARK)

    /**
     * Проверяет активна ли на смарт-терминале функция "Реквизиты покупателя".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isPurchaserActive(context: Context) = isFeatureActive(context, PATH_PURCHASER)

    private fun isFeatureActive(context: Context, path: String): Boolean {
        val cursor = context.contentResolver.query(
                Uri.parse("$BASE_PATH/$path"),
                null,
                null,
                null,
                null
        )

        return cursor?.use {
            it.moveToFirst()
            cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ACTIVE)) == 1
        } ?: false
    }
}