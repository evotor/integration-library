package ru.evotor.framework.receipt.formation.event.handler.service

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.formation.event.ReturnDeliveryRequisitesForReceiptRequestedEvent
import ru.evotor.framework.receipt.formation.event.ReturnPurchaserRequisitesForPrintGroupRequestedEvent

abstract class BuybackIntegrationService : IntegrationServiceV2() {

    final override fun onEvent(action: String, bundle: Bundle) = when (action) {
        ACTION_PURCHASER_REQUISITES -> ReturnPurchaserRequisitesForPrintGroupRequestedEvent.from(bundle)?.let { handleEvent(it) }
        ACTION_DELIVERY_REQUISITES -> ReturnDeliveryRequisitesForReceiptRequestedEvent.from(bundle)?.let { handleEvent(it) }
        else -> null
    }

    @RequiresIntentAction(ACTION_PURCHASER_REQUISITES)
    open fun handleEvent(event: ReturnPurchaserRequisitesForPrintGroupRequestedEvent): ReturnPurchaserRequisitesForPrintGroupRequestedEvent.Result? = null

    @RequiresIntentAction(ACTION_DELIVERY_REQUISITES)
    open fun handleEvent(event: ReturnDeliveryRequisitesForReceiptRequestedEvent): ReturnDeliveryRequisitesForReceiptRequestedEvent.Result? = null

    companion object {
        const val ACTION_PURCHASER_REQUISITES = "ru.evotor.event.buyback.PURCHASER_REQUISITES"
        const val ACTION_DELIVERY_REQUISITES = "ru.evotor.event.buyback.DELIVERY_REQUISITES"

        const val PERMISSION = "ru.evotor.permission.BUYBACK_INTEGRATION_SERVICE"
    }
}