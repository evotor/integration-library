package ru.evotor.framework.kkt.provider

import android.net.Uri

object KktContract {
    const val AUTHORITY = "ru.evotor.evotorpos.kkt"
    val BASE_URI: Uri = Uri.parse("content://$AUTHORITY")

    const val COLUMN_SUPPORTED_FFD_VERSION = "SUPPORTED_FFD_VERSION"
    const val COLUMN_REGISTERED_AGENT_TYPES = "REGISTERED_AGENT_TYPES"
    const val COLUMN_REGISTERED_SUBAGENT_TYPES = "REGISTERED_SUBAGENT_TYPES"
    const val COLUMN_IS_VAT_RATE_20_AVAILABLE = "IS_VAT_RATE_20_AVAILABLE"
    const val COLUMN_IS_DELIVERY_AVAILABLE = "IS_DELIVERY_AVAILABLE"

    @Deprecated("use BASE_URI without PATH_KKT_INFO")
    const val PATH_KKT_INFO = ".Info"
    const val COLUMN_SERIAL_NUMBER = "SERIAL_NUMBER"
    const val COLUMN_REGISTER_NUMBER = "REGISTER_NUMBER"

    const val PATH_KKT_FS_INFO = ".FsInfo"

    const val PATH_KKT_COUNTERS = ".Counters"
    const val COLUMN_CURRENT_CASH_SUM = "CURRENT_CASH_SUM"
}