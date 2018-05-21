package ru.evotor.framework.system

import android.content.Context
import android.database.Cursor
import android.net.Uri

object SystemStateApi {

    @JvmField
    val BASE_URI = Uri.parse("content://ru.evotor.evotorpos.system_state")

    @JvmStatic
    fun getLastSessionNumber(context: Context): Long? {
        val cursor = context.contentResolver.query(SystemStateTable.LAST_SESSION_NUMBER_URI, null,
                null, null, null)

        return cursor?.use {
            if (it.moveToFirst()) {
                return@use getLastSessionNumberFromCursor(it)
            } else {
                return@use null
            }
        }
    }

    @JvmStatic
    fun isSessionOpened(context: Context): Boolean? {
        val cursor = context.contentResolver.query(SystemStateTable.IS_SESSION_OPENED_URI, null,
                null, null, null)

        return cursor?.use {
            if (it.moveToFirst()) {
                return@use getIsSessionOpenedFromCursor(it)
            } else {
                return@use null
            }
        }
    }

    private fun getLastSessionNumberFromCursor(cursor: Cursor): Long? {
        val valueStr = cursor.getString(cursor.getColumnIndex(SystemStateTable.LAST_SESSION_NUMBER_COLUMN_NAME))
        if (valueStr.isNullOrEmpty()) {
            return null
        }

        return try {
            valueStr.toLong()
        } catch (error: NumberFormatException) {
            return null
        }
    }

    private fun getIsSessionOpenedFromCursor(cursor: Cursor): Boolean? {
        val valueStr = cursor.getString(cursor.getColumnIndex(SystemStateTable.IS_SESSION_OPENED_COLUMN_NAME))
        if (valueStr.isNullOrEmpty()) {
            return null
        }

        return valueStr.toBoolean()
    }
}