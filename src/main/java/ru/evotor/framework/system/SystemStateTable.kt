package ru.evotor.framework.system

import android.net.Uri

object SystemStateTable {
    const val IS_SESSION_OPENED_COLUMN_NAME = "IS_SESSION_OPENED"
    const val LAST_SESSION_NUMBER_COLUMN_NAME = "LAST_SESSION_NUMBER"

    const val IS_SESSION_OPENED_PATH = "IsSessionOpened"
    const val LAST_SESSION_NUMBER_PATH = "LastSessionNumber"

    @JvmField
    val IS_SESSION_OPENED_URI = Uri.withAppendedPath(SystemStateApi.BASE_URI, IS_SESSION_OPENED_PATH)
    @JvmField
    val LAST_SESSION_NUMBER_URI = Uri.withAppendedPath(SystemStateApi.BASE_URI, LAST_SESSION_NUMBER_PATH)
}