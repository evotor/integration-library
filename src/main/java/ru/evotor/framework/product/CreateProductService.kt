package ru.evotor.framework.product

import android.os.Bundle
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.core.RequiresIntentAction

/**
 * Служба для работы с созданием товара
 */
abstract class CreateProductService : IntegrationServiceV2() {

    override fun onEvent(action: String, bundle: Bundle) = when (action) {
        ACTION_CREATE_PRODUCT_CLICKED -> handleEvent(CreateProductClickedEvent())
        else -> null
    }

    @RequiresIntentAction(ACTION_CREATE_PRODUCT_CLICKED)
    open fun handleEvent(event: CreateProductClickedEvent): Nothing? = null

    companion object {

        /**
         * Чтобы подписать службу на получение запроса, в манифесте приложения, в элементе `action` intent-фильтра службы, укажите значение `ru.evotor.intent.event.product.CREATE_PRODUCT_CLICKED`.
         */
        const val ACTION_CREATE_PRODUCT_CLICKED = "ru.evotor.intent.event.product.CREATE_PRODUCT_CLICKED"

        const val PERMISSION = "ru.evotor.permission.CREATE_PRODUCT"
    }
}
