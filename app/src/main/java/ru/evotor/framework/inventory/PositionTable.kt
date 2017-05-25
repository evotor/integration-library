package ru.evotor.framework.inventory

import android.net.Uri

object PositionTable {
    @JvmField val URI = Uri.withAppendedPath(InventoryApi.BASE_URI, "Position")
    const val ROW_PRODUCT_UUID = "PRODUCT_UUID"
    const val ROW_PRICE = "PRICE"
    const val ROW_QUANTITY = "QUANTITY"
}
