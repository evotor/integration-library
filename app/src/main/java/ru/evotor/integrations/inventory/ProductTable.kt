package ru.evotor.integrations.inventory

import android.net.Uri

/**
 * Created by a.kuznetsov on 26/03/2017.
 */
object ProductTable {
    @JvmField val URI = Uri.withAppendedPath(InventoryApi.BASE_URI, "Commodity")

    const val ROW_UUID = "UUID"
    const val ROW_NAME = "NAME"
    const val ROW_DESCRIPTION = "DESCRIPTION"
    const val ROW_PRICE_OUT = "PRICE_OUT"
    const val ROW_QUANTITY = "QUANTITY"
}
