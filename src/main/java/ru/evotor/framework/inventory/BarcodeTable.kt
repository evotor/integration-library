package ru.evotor.framework.inventory

import android.net.Uri

object BarcodeTable {
    @JvmField val URI = Uri.withAppendedPath(InventoryApi.BASE_URI, "Barcode")
    const val ROW_BARCODE = "BARCODE"
}
