package ru.evotor.framework.receipt.slip

import android.content.Context
import android.net.Uri
import ru.evotor.framework.features.FeaturesApi
import ru.evotor.framework.settings.SettingsProviderContracts.SLIPS_AMOUNT_PROVIDER

@Deprecated("Используйте SettingsApi", replaceWith = ReplaceWith("SettingsApi"))
object SlipsAmountApi {
    fun getSlipsAmount(context: Context): Int? =
        if (FeaturesApi.isSlipAmountActive(context)) {
            context.contentResolver.query(
                Uri.withAppendedPath(BASE_URI, SLIPS_AMOUNT_PROVIDER.path),
                null,
                null,
                null,
                null
            )?.use { c ->
                c.moveToFirst()

                val ind = c.getColumnIndex(SLIPS_AMOUNT_PROVIDER.columnName)
                if (ind > -1) {
                    c.getInt(ind)
                } else {
                    null
                }
            }
        } else {
            null
        }

    @Suppress("MemberVisibilityCanBePrivate")
    const val DEPRECATED_AUTHORITY = "ru.evotor.settings.SlipsAmountInfo"
    private val BASE_URI = Uri.parse("content://$DEPRECATED_AUTHORITY")
}
