package ru.evotor.framework.device.mode

import android.net.Uri

object DeviceModeContract {

    val AUTHORITY = "ru.evotor.external.integrations"
    val PATH = "mode_fr"
    val COLUMN_MODE = "mode"

    val QUERY_URI = Uri.parse("content://$AUTHORITY/$PATH")

}
