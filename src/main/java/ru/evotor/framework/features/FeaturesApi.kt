package ru.evotor.framework.features

import android.content.Context
import android.net.Uri
import ru.evotor.framework.features.provider.FeaturesContract

/**
 * Интерфейс для проверки состояния функций, которые активируются после приобретения (продления подписки) и установки приложения "Смарт-терминал Плюс" (ранее "Пакет обновлноений").
 *
 * Установка и оплата приложения "Смарт-терминал Плюс" активирует:
 *
 * возможность работать по ставке НДС 20%;
 * поддержку ФФД 1.05;
 * маркировку табака;
 * возможность добавлять в чек реквизиты покупателя.
 */
object FeaturesApi {

    /**
     * Проверяет состояние функции "НДС 20%".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isVat20Active(context: Context) = isFeatureActive(context, FeaturesContract.PATH_VAT20)

    /**
     * Проверяет состояние функции "Переход на ФФД 1.05".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isFfd105Active(context: Context) = isFeatureActive(context, FeaturesContract.PATH_FFD105)

    /**
     * Проверяет состояние функции "Маркировка табака".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isTobaccomarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_TOBACCOMARK)

    /**
     * Проверяет состояние функции "Реквизиты покупателя".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isPurchaserActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_PURCHASER)

    /**
     * Проверяет состояние функции "Развозная торговля".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isDeliveryActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_DELIVERY)

    /**
     * Проверяет состояние функции "Компактный чек".
     * Требуется версия прошивки ФР >= 5086
     *
     * @return `true` если функция активна, `false` соответственно если функция не активна
     */
    fun isShortCheckActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_SHORT_CHECK)

    /**
     * Проверяет состояние функции "Маркировка алкоголя".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isAlcoMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_ALCO_MARK)

    /**
     * Проверяет состояние функции "Внешний УТМ".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isExternalUtmActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_EXTERNAL_UTM)

    /**
     * Проверяет состояние функции "Маркировка лекарств".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isMedicineMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_MEDICINE_MARK)

    /**
     * Проверяет состояние функции "Маркировка обуви".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isShoesMarkActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_SHOES_MARK)

    /**
     * Проверяет состояние функции "Управленческие отчёты".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isManagementReportsActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_MANAGEMENT_REPORTS)

    /**
     * Проверяет состояние функции "Удаление документов".
     *
     * @return `true` если функция активна; `false` если функция не активна.
     */
    fun isDocumentCleanActive(context: Context) = isFeatureActive(context, FeaturesContract.PATH_DOCUMENT_CLEAN)


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