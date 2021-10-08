package ru.evotor.framework.device.mode

import android.content.Context

object DeviceModeApi {

    /**
     * Возвращает текущий режим работы терминала, если применимо, иначе - null
     */
    @JvmStatic
    fun getCurrentDeviceMode(context: Context): String? {
        return try {
            context.contentResolver.query(
                DeviceModeContract.QUERY_URI,
                arrayOf(DeviceModeContract.COLUMN_MODE),
                null, null, null
            )?.use {
                it.getString(it.getColumnIndex(DeviceModeContract.COLUMN_MODE))
            }
        } catch (t: Throwable) {
            null
        }
    }

}
