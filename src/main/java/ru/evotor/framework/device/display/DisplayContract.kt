package ru.evotor.framework.device.display

import android.net.Uri

object DisplayContract {

    const val AUTHORITY = "ru.evotor.launcher"
    val BASE_URI: Uri = Uri.parse("content://$AUTHORITY")

    const val DISPLAY_QUERY_PATH = ".display"

    const val COLUMN_DISPLAYS_COUNT = "DISPLAYS_COUNT"
    const val COLUMN_CUSTOMER_DISPLAY_ID = "CUSTOMER_DISPLAY_ID"
    const val COLUMN_CASHIER_DISPLAY_ID = "CASHIER_DISPLAY_ID"

}
