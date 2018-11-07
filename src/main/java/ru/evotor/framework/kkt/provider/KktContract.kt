package ru.evotor.framework.kkt.provider

import android.net.Uri

object KktContract {
    @JvmField val BASE_URI = Uri.parse("content://ru.evotor.evotorpos.kkt")
    @JvmField val URI_FFD_VERSION = Uri.withAppendedPath(BASE_URI, "FfdVersion")

    const val COLUMN_FFD_VERSION = "FFD_VERSION"
}