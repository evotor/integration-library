package ru.evotor.framework.inventory

import android.net.Uri

/**
 * Created by a.kuznetsov on 26/03/2017.
 */
object ProductTable {
    @JvmField val URI = Uri.withAppendedPath(InventoryApi.BASE_URI, "Commodity")

    const val ROW_UUID = "UUID"
    const val ROW_CODE = "CODE"
    const val ROW_TYPE = "TYPE"
    const val ROW_PARENT_UUID = "PARENT_UUID"
    const val ROW_IS_GROUP = "IS_GROUP"
    const val ROW_NAME = "NAME"
    const val ROW_DESCRIPTION = "DESCRIPTION"
    const val ROW_PRICE_OUT = "PRICE_OUT"
    const val ROW_QUANTITY = "QUANTITY"
    const val ROW_MEASURE_NAME = "MEASURE_NAME"
    const val ROW_MEASURE_PRECISION = "MEASURE_PRECISION"
    const val ROW_ALCOHOL_BY_VOLUME = "ALCOHOL_BY_VOLUME"
    const val ROW_ALCOHOL_PRODUCT_KIND_CODE = "ALCOHOL_PRODUCT_KIND_CODE"
    const val ROW_TARE_VOLUME = "TARE_VOLUME"
    const val ROW_TAX_NUMBER = "TAX_NUMBER"
}
