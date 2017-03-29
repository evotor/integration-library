package ru.evotor.integrations.inventory

import android.net.Uri

/**
 * Created by a.kuznetsov on 26/03/2017.
 */

object ProductExtraTable {
    @JvmField val URI = Uri.withAppendedPath(InventoryApi.BASE_URI, "CommodityExtra")

    @JvmField val ROW_UUID = "UUID"
    @JvmField val ROW_NAME = "NAME"
    @JvmField val ROW_PRODUCT_UUID = "COMMODITY_UUID"
    @JvmField val ROW_FIELD_UUID = "FIELD_UUID"
    @JvmField val ROW_FIELD_VALUE = "FIELD_VALUE"
    @JvmField val ROW_DATA = "DATA"
}