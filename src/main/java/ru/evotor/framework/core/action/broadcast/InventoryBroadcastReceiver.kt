package ru.evotor.framework.core.action.broadcast

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.action.event.inventory.ProductCardOpenedEvent

/**
 * Широковещательный приёмник товароучётных событий.
 * @see <a href="https://developer.evotor.ru/docs/">Использование широковещательного приёмника</a>
 */
open class InventoryBroadcastReceiver : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий открытия карточки товара.
     */
    @RequiresIntentAction(ACTION_PRODUCT_CARD_OPENED)
    protected open fun handleProductCardOpenedEvent(context: Context, productCardOpenedEvent: ProductCardOpenedEvent) = Unit

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        when (action) {
            ACTION_PRODUCT_CARD_OPENED -> handleProductCardOpenedEvent(context, ProductCardOpenedEvent.create(bundle) ?: return)
        }
    }

    companion object {
        const val ACTION_PRODUCT_CARD_OPENED = "evotor.intent.action.inventory.CARD_OPEN"
    }

}