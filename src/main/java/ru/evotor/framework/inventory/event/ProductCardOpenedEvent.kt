package ru.evotor.framework.inventory.event

import android.os.Bundle

/**
 * Событие открытия карточки товара.
 *
 * Происходит при открытии карточки товара в интерфейсе приложения "Товары".
 *
 * Обрабатывать это событие можно с помощью широковещательного приёмника товароучётных событий:
 * [ru.evotor.framework.inventory.event.handler.receiver.InventoryBroadcastReceiver]
 *
 * @param productUuid uuid товара
 */
class ProductCardOpenedEvent(productUuid: String) : ProductEvent(productUuid) {
    companion object {
        fun from(bundle: Bundle?): ProductCardOpenedEvent? = bundle?.let {
            ProductCardOpenedEvent(ProductEvent.getProductUuid(it) ?: return null)
        }
    }
}