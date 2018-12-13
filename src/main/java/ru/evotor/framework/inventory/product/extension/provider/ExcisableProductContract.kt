package ru.evotor.framework.inventory.product.extension.provider

import android.net.Uri
import ru.evotor.framework.inventory.provider.InventoryContract

internal object ExcisableProductContract {
    private const val PATH = "excisable_products"

    val URI: Uri = Uri.withAppendedPath(InventoryContract.BASE_URI, PATH)

    const val COLUMN_MARK = "MARK"
}