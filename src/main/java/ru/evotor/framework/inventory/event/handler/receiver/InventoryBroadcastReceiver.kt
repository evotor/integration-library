package ru.evotor.framework.inventory.event.handler.receiver

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.core.BroadcastEventReceiver
import ru.evotor.framework.inventory.event.ProductCardOpenedEvent

/**
 * Широковещательный приёмник товароучётных событий.
 * @see <a href="https://developer.evotor.ru/docs/beta/doc_java_broadcastreceiver.html">Использование широковещательного приёмника</a>
 */
open class InventoryBroadcastReceiver : BroadcastEventReceiver() {

    /**
     * Обработчик событий открытия карточки товара.
     */
    @RequiresIntentAction(ACTION_PRODUCT_CARD_OPENED)
    protected open fun handleProductCardOpenedEvent(context: Context, event: ProductCardOpenedEvent) = Unit

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        when (action) {
            ACTION_PRODUCT_CARD_OPENED -> handleProductCardOpenedEvent(context, ProductCardOpenedEvent.from(bundle)
                    ?: return)
        }
    }

    companion object {
        const val ACTION_PRODUCT_CARD_OPENED = "evotor.intent.action.inventory.CARD_OPEN"
    }

}