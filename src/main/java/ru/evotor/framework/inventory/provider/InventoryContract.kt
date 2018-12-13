package ru.evotor.framework.inventory.provider

import android.net.Uri

internal object InventoryContract {
    private const val AUTHORITY = "ru.evotor.framework.inventory"

    val BASE_URI: Uri = Uri.parse("content://$AUTHORITY")

    const val PATH_PRODUCTS = "products"
    const val PATH_POSITIONS = "positions"

    fun getBarcodeUri(barcode: String): Uri = Uri.withAppendedPath(BASE_URI, barcode)
}