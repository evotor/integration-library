package ru.evotor.framework.system

import android.database.Cursor
import android.net.Uri

/**
 * Created by d.fabrichnyi on 06.02.2018.
 */
class IsSessionOpened private constructor(val isOpened: Boolean) {
    companion object {
        val COLUMN_NAME = "IS_SESSION_OPENED"
        val IS_SESSION_OPENED_PATH = "IsSessionOpened"
        val URI = Uri.withAppendedPath(SystemStateApi.BASE_URI, IS_SESSION_OPENED_PATH)

        fun fromCursor(cursor: Cursor): IsSessionOpened? {
            val valueStr = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            if (valueStr.isNullOrEmpty()) {
                return null
            }

            val value = try {
                valueStr.toBoolean()
            } catch (error: NumberFormatException) {
                return null
            }

            return IsSessionOpened(value)
        }
    }
}