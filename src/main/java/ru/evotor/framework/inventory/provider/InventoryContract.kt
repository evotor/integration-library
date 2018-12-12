package ru.evotor.framework.inventory.provider

import android.net.Uri

internal object InventoryContract {
    private const val AUTHORITY = "ru.evotor.framework.inventory"

    val URI: Uri = Uri.parse("content://$AUTHORITY")

    const val PATH_PRODUCTS_BY_BARCODE = "products"
    const val PATH_POSITIONS_BY_BARCODE = "positions"

    fun getBarcodeUri(barcode: String): Uri = Uri.withAppendedPath(URI, barcode)
}