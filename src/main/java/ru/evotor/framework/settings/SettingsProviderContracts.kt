package ru.evotor.framework.settings

import android.net.Uri

/**
 * Контракты контент-провайдера настроек EvotorPos.
 */
enum class SettingsProviderContracts(
    val path: String,
    val columnName: String
) {

    /**
     * Количество печати слип-чеков.
     */
    SLIPS_AMOUNT_PROVIDER(
        "SLIPS_AMOUNT_PATH",
        "SLIPS_AMOUNT_COLUMN"
    ),

    /**
     * Опция "Разрешить отрицательные остатки".
     */
    NEGATIVE_BALANCE_PROVIDER(
        "NEGATIVE_BALANCE_PATH",
        "NEGATIVE_BALANCE_COLUMN"
    );

    @Suppress("MemberVisibilityCanBePrivate")
    companion object {
        const val AUTHORITY = "ru.evotor.evotorpos.settings"
        const val DEPRECATED_AUTHORITY = "ru.evotor.settings.SlipsAmountInfo"

        internal val BASE_URI = Uri.parse("content://$AUTHORITY")
        internal val DEPRECATED_BASE_URI = Uri.parse("content://$DEPRECATED_AUTHORITY")
    }
}
