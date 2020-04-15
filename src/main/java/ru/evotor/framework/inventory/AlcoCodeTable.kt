package ru.evotor.framework.inventory

import android.net.Uri

internal object AlcoCodeTable {
    @JvmField val URI = Uri.withAppendedPath(ru.evotor.framework.inventory.InventoryApi.BASE_URI, "AlcoCode")
    const val COLUMN_ALCO_CODE = "ALCO_CODE"
    const val COLUMN_COMMODITY_UUID = "COMMODITY_UUID"
}