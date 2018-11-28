package ru.evotor.framework.provider

import android.net.Uri

internal object DocumentContract {
    private const val AUTHORITY = "ru.evotor.framework.document"

    val URI: Uri = Uri.parse("content://$AUTHORITY")

    const val COLUMN_UUID = "UUID"
}