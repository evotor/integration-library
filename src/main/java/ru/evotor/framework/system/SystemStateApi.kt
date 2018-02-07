package ru.evotor.framework.system

import android.content.Context
import android.net.Uri

object SystemStateApi {

    @JvmField
    val BASE_URI = Uri.parse("content://ru.evotor.evotorpos.system_state")

    @JvmStatic
    fun getLastSessionNumber(context: Context): LastSessionNumber? {
        val cursor = context.contentResolver.query(LastSessionNumber.URI, null,
                null, null, null)

        if (cursor == null) {
            return null
        }

        return cursor.use {
            if (it.moveToFirst()) {
                return@use LastSessionNumber.fromCursor(it)
            } else {
                return@use null
            }
        }
    }

    @JvmStatic
    fun isSessionOpened(context: Context): IsSessionOpened? {
        val cursor = context.contentResolver.query(IsSessionOpened.URI, null,
                null, null, null)

        if (cursor == null) {
            return null
        }

        return cursor.use {
            if (it.moveToFirst()) {
                return@use IsSessionOpened.fromCursor(it)
            } else {
                return@use null
            }
        }
    }
}