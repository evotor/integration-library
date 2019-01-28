package ru.evotor.framework.kkt.provider

import android.net.Uri

internal object KktContract {
    val BASE_URI: Uri = Uri.parse("content://ru.evotor.evotorpos.kkt")

    const val COLUMN_SUPPORTED_FFD_VERSION = "SUPPORTED_FFD_VERSION"
    const val COLUMN_REGISTERED_AGENT_TYPES = "REGISTERED_AGENT_TYPES"
    const val COLUMN_REGISTERED_SUBAGENT_TYPES = "REGISTERED_SUBAGENT_TYPES"
    const val COLUMN_IS_VAT_RATE_20_AVAILABLE = "IS_VAT_RATE_20_AVAILABLE"
}