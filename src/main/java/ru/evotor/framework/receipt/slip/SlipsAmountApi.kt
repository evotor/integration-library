package ru.evotor.framework.receipt.slip

import android.content.Context
import ru.evotor.framework.settings.SettingsApi

@Deprecated("Используйте SettingsApi", replaceWith = ReplaceWith("SettingsApi"))
object SlipsAmountApi {
    fun getSlipsAmount(context: Context): Int? = SettingsApi.getSlipsAmount(context)
}
