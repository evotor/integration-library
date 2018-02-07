package ru.evotor.framework.system

import android.database.Cursor
import android.net.Uri

class LastSessionNumber private constructor(val sessionNumber: Long) {
    companion object {
        val COLUMN_NAME = "LAST_SESSION_NUMBER"
        val LAST_SESSION_NUMBER_PATH = "LastSessionNumber"
        val URI = Uri.withAppendedPath(SystemStateApi.BASE_URI, LAST_SESSION_NUMBER_PATH)

        fun fromCursor(cursor: Cursor): LastSessionNumber? {
            val valueStr = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            if (valueStr.isNullOrEmpty()) {
                return null
            }

            val value = try {
                valueStr.toLong()
            } catch (error: NumberFormatException) {
                return null
            }

            return LastSessionNumber(value)
        }
    }
}