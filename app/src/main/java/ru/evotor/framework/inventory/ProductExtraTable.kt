package ru.evotor.framework.inventory

import android.net.Uri

/**
 * Created by a.kuznetsov on 26/03/2017.
 */

object ProductExtraTable {
    @JvmField val URI = Uri.withAppendedPath(InventoryApi.BASE_URI, "CommodityExtra")

    const val ROW_UUID = "UUID"
    const val ROW_NAME = "NAME"
    const val ROW_PRODUCT_UUID = "COMMODITY_UUID"
    const val ROW_FIELD_UUID = "FIELD_UUID"
    const val ROW_FIELD_VALUE = "FIELD_VALUE"
    const val ROW_DATA = "DATA"
}