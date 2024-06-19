package ru.evotor.framework.settings

import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.evotor.framework.features.FeaturesApi
import ru.evotor.framework.settings.SettingsProviderContracts.Companion.BASE_URI
import ru.evotor.framework.settings.SettingsProviderContracts.SLIPS_AMOUNT_PROVIDER
import ru.evotor.framework.settings.SettingsProviderContracts.NEGATIVE_BALANCE_PROVIDER

/**
 * Настройки EvotorPos (раздел "Правила торговли").
 */
object SettingsApi {

    /**
     * Количество печати слип-чеков.
     */
    fun getSlipsAmount(context: Context): Int? =
        if (FeaturesApi.isSlipAmountActive(context)) {
            SLIPS_AMOUNT_PROVIDER.runCursor(context) { columnIndex ->
                getInt(columnIndex)
            }
        } else {
            null
        }

    /**
     * Включена ли опция "Разрешить отрицательные остатки".
     */
    fun isNegativeBalanceEnabled(context: Context): Boolean? =
        NEGATIVE_BALANCE_PROVIDER.runCursor(context) { columnIndex ->
            getInt(columnIndex) != 0
        }

    private fun <T> SettingsProviderContracts.runCursor(context: Context, getValue: Cursor.(Int) -> T): T? {
        context.contentResolver.query(
            Uri.withAppendedPath(BASE_URI, path),
            null,
            null,
            null,
            null
        )?.use { c ->
            c.moveToFirst()

            val ind = c.getColumnIndex(columnName)
            return if (ind > -1) {
                c.getValue(ind)
            } else {
                null
            }
        }

        return null
    }
}
