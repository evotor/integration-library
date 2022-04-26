package ru.evotor.framework.receipt.slip

import android.net.Uri

object SlipsAmountProviderContract {
    const val AUTHORITY = "ru.evotor.settings.SlipsAmountInfo"
    const val BASE_PATH = "content://$AUTHORITY"
    val BASE_URI: Uri = Uri.parse(BASE_PATH)

    const val PATH_SLIPS_AMOUNT = "SLIPS_AMOUNT_PATH"
    const val COLUMN_SLIPS_AMOUNT = "SLIPS_AMOUNT_COLUMN"
}
