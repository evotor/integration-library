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
    const val PATH_DOCUMENT_CLEAN = "documentClean"
    const val PATH_MANAGEMENT_REPORTS = "managementReports"
    const val PATH_DELIVERY = "delivery"
    const val PATH_SHORT_CHECK = "short_check"
    const val PATH_ALCO_MARK = "alco_mark"
    const val PATH_EXTERNAL_UTM = "external_utm"
    const val PATH_MEDICINE_MARK = "medicine_mark"
    const val PATH_SHOES_MARK = "shoes_mark"
    const val PATH_CLASSIFICATION_CODE = "classification_code"
    const val PATH_TYRES_MARK = "tyres_mark"
    const val PATH_PERFUMES_MARK = "perfumes_mark"
    const val PATH_PHOTOS_MARK = "photos_mark"
    const val PATH_LIGHT_INDUSTRY_MARK = "light_industry_mark"
    const val PATH_TOBACCO_PRODUCTS_MARK = "tobacco_products_mark"
    const val PATH_SLIP_AMOUNT = "slip_amount"
    const val PATH_DAIRY_MARK = "dairy_mark"
    const val PATH_WATER_MARK = "water_mark"
    const val PATH_FFD12 = "ffd12"
    const val PATH_UPDATE_MANAGEMENT = "update_management"
    const val PATH_BIKE_MARK = "bike_mark"
    const val PATH_FISC_BLOCK = "fisc_block"
    const val PATH_FISC_PAID = "fisc_paid"
    const val PATH_JEWELRY_MARK = "jewelry_mark"
    const val PATH_FUR_MARK = "fur_mark"
    const val PATH_SBP_ACTIVATION = "sbp_activation"

    const val COLUMN_IS_ACTIVE = "is_active"
}
