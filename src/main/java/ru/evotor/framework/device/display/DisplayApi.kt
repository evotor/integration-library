package ru.evotor.framework.device.display

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.hardware.display.DisplayManager
import android.os.Build
import android.os.Bundle

object DisplayApi {

    /**
     * Возвращает id дисплея покупателя
     * (или 0, если отсутствует подключенный дисплей покупателя)
     */
    @JvmStatic
    fun getCustomerDisplayId(context: Context): Int {
        return context.getSystemService(DisplayManager::class.java)
            .displays.firstOrNull { it.displayId != 0 }?.displayId ?: 0
    }

    /**
     * Получить id указанного дисплея
     */
    @JvmStatic
    fun getLaunchDisplayId(context: Context, display: Displays): Int {
        return if (display == Displays.CUSTOMER) {
            getCustomerDisplayId(context)
        } else 0
    }

    /**
     *  Выбор дисплея доступен только с 26 версии Android API,
     *  поэтому для меньшей версии Android API вернётся null.
     */
    @JvmStatic
    fun makeOptionsFor(context: Context, display: Displays): Bundle? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ActivityOptions.makeBasic().setLaunchDisplayId(
                getLaunchDisplayId(context, display)
            ).toBundle()
        } else {
            null
        }
    }

    /**
     *  Вернет true, если activity запущена на экране покупателя
     *  Если подключенный экран отсутствует, вернет false
     */
    @JvmStatic
    fun isActivityOnCustomerScreen(activity: Activity): Boolean {
        return activity.windowManager.defaultDisplay.displayId != 0
    }

}
