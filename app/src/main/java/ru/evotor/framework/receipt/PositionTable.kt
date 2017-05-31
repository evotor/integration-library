package ru.evotor.framework.receipt

import android.net.Uri

object PositionTable {
    @JvmField val URI = Uri.withAppendedPath(ReceiptApi.BASE_URI, "Position")
    const val ROW_PRODUCT_UUID = "PRODUCT_UUID"
    const val ROW_PRODUCT_CODE = "PRODUCT_CODE"
    const val ROW_PRODUCT_TYPE = "PRODUCT_TYPE"
    const val ROW_NAME = "NAME"
    const val ROW_MEASURE_NAME = "MEASURE_NAME"
    const val ROW_MEASURE_PRECISION = "MEASURE_PRECISION"
    const val ROW_PRICE = "PRICE"
    const val ROW_BARCODE = "BARCODE"
    const val ROW_QUANTITY = "QUANTITY"
    const val ROW_ALCOHOL_BY_VOLUME = "ALCOHOL_BY_VOLUME"
    const val ROW_ALCOHOL_PRODUCT_KIND_CODE = "ALCOHOL_PRODUCT_KIND_CODE"
    const val ROW_TARE_VOLUME = "TARE_VOLUME"
}
