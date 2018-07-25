package ru.evotor.framework.core.action.datamapper

import android.os.Bundle

internal class InventoryEventMapper(private val bundle: Bundle) {

    fun getProductUuid(): String? = bundle.getString(KEY_PRODUCT_UUID)

    companion object {

        private const val KEY_PRODUCT_UUID = "productUuid"

    }

}