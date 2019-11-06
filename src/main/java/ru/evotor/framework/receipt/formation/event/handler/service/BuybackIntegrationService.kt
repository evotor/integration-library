package ru.evotor.framework.receipt.formation.event.handler.service

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.formation.event.DiscountScreenAdditionalItemsEvent
import ru.evotor.framework.receipt.formation.event.ReturnPurchaserRequisitesForPrintGroupRequestedEvent

abstract class BuybackIntegrationService : IntegrationServiceV2() {

    final override fun onEvent(action: String, bundle: Bundle) = when (action) {
        ACTION_PURCHASER_REQUISITES -> ReturnPurchaserRequisitesForPrintGroupRequestedEvent.from(bundle)?.let { handleEvent(it) }
        ACTION_DISCOUNT_SCREEN_ADDITIONAL_ITEMS -> DiscountScreenAdditionalItemsEvent.from(bundle)?.let { handleEvent(it) }
        else -> null
    }

    @RequiresIntentAction(ACTION_PURCHASER_REQUISITES)
    open fun handleEvent(event: ReturnPurchaserRequisitesForPrintGroupRequestedEvent): ReturnPurchaserRequisitesForPrintGroupRequestedEvent.Result? = null

    @RequiresIntentAction(ACTION_DISCOUNT_SCREEN_ADDITIONAL_ITEMS)
    open fun handleEvent(event: DiscountScreenAdditionalItemsEvent): DiscountScreenAdditionalItemsEvent? = null

    companion object {
        const val ACTION_PURCHASER_REQUISITES = "ru.evotor.event.buyback.PURCHASER_REQUISITES"
        const val ACTION_DISCOUNT_SCREEN_ADDITIONAL_ITEMS = "ru.evotor.event.buyback.DISCOUNT_SCREEN_ADDITIONAL_ITEMS"
        const val PERMISSION = "ru.evotor.permission.BUYBACK_INTEGRATION_SERVICE"
    }
}