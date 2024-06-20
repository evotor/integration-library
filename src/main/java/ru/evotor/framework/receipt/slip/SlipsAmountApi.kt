package ru.evotor.framework.receipt.slip

import android.content.Context
import android.net.Uri
import ru.evotor.framework.settings.SettingsApi

@Deprecated("Используйте SettingsApi", replaceWith = ReplaceWith("SettingsApi"))
object SlipsAmountApi {
    fun getSlipsAmount(context: Context): Int? = SettingsApi.getSlipsAmount(context)

    @Suppress("MemberVisibilityCanBePrivate")
    const val DEPRECATED_AUTHORITY = "ru.evotor.settings.SlipsAmountInfo"
    internal val DEPRECATED_BASE_URI = Uri.parse("content://$DEPRECATED_AUTHORITY")
}
