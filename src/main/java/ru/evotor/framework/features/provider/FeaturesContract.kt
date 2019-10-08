package ru.evotor.framework.features.provider

import android.net.Uri

object FeaturesContract {

    const val AUTHORITY = "ru.evotor.paidupdates.FeaturesContentProvider"
    const val BASE_PATH = "content://$AUTHORITY"
    val BASE_URI: Uri = Uri.parse(BASE_PATH)

    const val PATH_VAT20 = "vat20"
    const val PATH_FFD105 = "ffd105"
    const val PATH_TOBACCOMARK = "tobaccomark"
    const val PATH_PURCHASER = "purchaser"
    const val PATH_DELIVERY = "delivery"

    const val COLUMN_IS_ACTIVE = "is_active"

}