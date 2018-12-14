package ru.evotor.framework.inventory.product.extension.provider

import android.net.Uri
import ru.evotor.framework.inventory.provider.InventoryContract

object AlcoholProductContract {
    private const val PATH = "alcohol_products"

    val URI: Uri = Uri.withAppendedPath(InventoryContract.BASE_URI, PATH)

    const val COLUMN_FSAR_PRODUCT_KIND_CODE = "FSAR_PRODUCT_KIND_CODE"
    const val COLUMN_TARE_VOLUME = "TARE_VOLUME"
    const val COLUMN_ALCOHOL_PERCENTAGE = "ALCOHOL_PERCENTAGE"
}