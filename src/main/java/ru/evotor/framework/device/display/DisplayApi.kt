package ru.evotor.framework.device.display

import android.app.ActivityOptions
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle

object DisplayApi {

    /**
     * Возвращает информацию о количестве дисплеев
     * и логическом делении дисплеев
     * на дисплей кассира и дисплей покупателя.
     */
    fun getDisplaysInfo(context: Context): DisplaysInfo? {
        val uri = Uri.parse("${DisplayContract.BASE_URI}/${DisplayContract.DISPLAY_QUERY_PATH}")
        val cursor = context.contentResolver.query(
            uri,
            arrayOf(
                DisplayContract.COLUMN_DISPLAYS_COUNT,
                DisplayContract.COLUMN_CASHIER_DISPLAY_ID,
                DisplayContract.COLUMN_CUSTOMER_DISPLAY_ID
            ),
            null,
            null,
            null
        )

        return cursor?.use {
            if (it.moveToFirst()) {
                DisplaysInfo(
                    it.getInt(it.getColumnIndex(DisplayContract.COLUMN_DISPLAYS_COUNT)),
                    it.getInt(it.getColumnIndex(DisplayContract.COLUMN_CASHIER_DISPLAY_ID)),
                    it.getInt(it.getColumnIndex(DisplayContract.COLUMN_CUSTOMER_DISPLAY_ID)),
                )
            } else null
        }
    }

    /**
     *  Выбор дисплея доступен только с 26 версии Android API,
     *  поэтому для меньшей версии Android API вернётся null.
     */
    fun makeOptionsBundleForStartOnDisplay(displayId: Int): Bundle? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ActivityOptions.makeBasic().setLaunchDisplayId(displayId).toBundle()
        } else {
            null
        }
    }

}
