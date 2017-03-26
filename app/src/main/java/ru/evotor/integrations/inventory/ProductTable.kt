package ru.evotor.integrations.inventory

import android.net.Uri

/**
 * Created by a.kuznetsov on 26/03/2017.
 */
object ProductTable {
    @JvmField val URI = Uri.withAppendedPath(InventoryApi.BASE_URI, "Commodity")

    @JvmField val ROW_UUID = "UUID"
    @JvmField val ROW_NAME = "NAME"
    @JvmField val ROW_DESCRIPTION = "DESCRIPTION"
    @JvmField val ROW_PRICE_OUT = "PRICE_OUT"
    @JvmField val ROW_QUANTITY = "QUANTITY"
}
