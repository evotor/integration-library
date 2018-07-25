package ru.evotor.framework.core.action.broadcast

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.InventoryEventMapper

/**
 * Класс, получающий широковещательные сообщения о товароучётных событиях.
 */
open class InventoryBroadcastReceiver : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий открытия карточки товара.
     */
    protected open fun handleProductCardOpenedEvent(productUuid: String) = Unit

    final override fun onEvent(action: String, bundle: Bundle) {
        val mapper = InventoryEventMapper(bundle)
        val productUuid = mapper.getProductUuid() ?: return
        when (action) {
            ACTION_PRODUCT_CARD_OPENED -> handleProductCardOpenedEvent(productUuid)
        }
    }

    companion object {

        const val ACTION_PRODUCT_CARD_OPENED = "evotor.intent.action.inventory.CARD_OPEN"

    }

}