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
            )?.use { c ->
                if (c.moveToNext()) {
                    c.getString(c.getColumnIndex(DeviceModeContract.COLUMN_MODE))
                } else {
                    null
                }
            }
        } catch (t: Throwable) {
            null
        }
    }

}
