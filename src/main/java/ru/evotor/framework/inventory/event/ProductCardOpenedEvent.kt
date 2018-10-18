package ru.evotor.framework.inventory.event

import android.os.Bundle

class ProductCardOpenedEvent(productUuid: String) : ProductEvent(productUuid) {
    companion object {
        fun from(bundle: Bundle?): ProductCardOpenedEvent? = bundle?.let {
            ProductCardOpenedEvent(ProductEvent.getProductUuid(it) ?: return null)
        }
    }
}