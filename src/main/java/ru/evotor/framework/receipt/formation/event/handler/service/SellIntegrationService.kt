package ru.evotor.framework.receipt.formation.event.handler.service

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.formation.event.DiscountScreenAdditionalItemsEvent
import ru.evotor.framework.receipt.formation.event.ReturnDeliveryRequisitesForReceiptRequestedEvent
import ru.evotor.framework.receipt.formation.event.ReturnPositionsForBarcodeRequestedEvent
import ru.evotor.framework.receipt.formation.event.ReturnPurchaserRequisitesForPrintGroupRequestedEvent

abstract class SellIntegrationService : IntegrationServiceV2() {

    final override fun onEvent(action: String, bundle: Bundle) = when (action) {
        ACTION_BARCODE_RECEIVED -> ReturnPositionsForBarcodeRequestedEvent.from(bundle)?.let { handleEvent(it) }
        ACTION_PURCHASER_REQUISITES -> ReturnPurchaserRequisitesForPrintGroupRequestedEvent.from(bundle)?.let { handleEvent(it) }
        ACTION_DISCOUNT_SCREEN_ADDITIONAL_ITEMS -> DiscountScreenAdditionalItemsEvent.from(bundle)?.let { handleEvent(it) }
        ACTION_DELIVERY_REQUISITES -> ReturnDeliveryRequisitesForReceiptRequestedEvent.from(bundle)?.let { handleEvent(it) }
        else -> null
    }

    @RequiresIntentAction(ACTION_BARCODE_RECEIVED)
    open fun handleEvent(event: ReturnPositionsForBarcodeRequestedEvent): ReturnPositionsForBarcodeRequestedEvent.Result? = null

    @RequiresIntentAction(ACTION_PURCHASER_REQUISITES)
    open fun handleEvent(event: ReturnPurchaserRequisitesForPrintGroupRequestedEvent): ReturnPurchaserRequisitesForPrintGroupRequestedEvent.Result? = null

    @RequiresIntentAction(ACTION_DISCOUNT_SCREEN_ADDITIONAL_ITEMS)
    open fun handleEvent(event: DiscountScreenAdditionalItemsEvent): IBundlable? = null

    @RequiresIntentAction(ACTION_DELIVERY_REQUISITES)
    open fun handleEvent(event: ReturnDeliveryRequisitesForReceiptRequestedEvent): ReturnDeliveryRequisitesForReceiptRequestedEvent.Result? = null

    companion object {
        const val ACTION_BARCODE_RECEIVED = "ru.evotor.event.sell.BARCODE_RECEIVED"
        const val ACTION_PURCHASER_REQUISITES = "ru.evotor.event.sell.PURCHASER_REQUISITES"
        const val ACTION_DISCOUNT_SCREEN_ADDITIONAL_ITEMS = "ru.evotor.event.sell.DISCOUNT_SCREEN_ADDITIONAL_ITEMS"
        const val ACTION_DELIVERY_REQUISITES = "ru.evotor.event.sell.DELIVERY_REQUISITES"

        const val PERMISSION = "ru.evotor.permission.SELL_INTEGRATION_SERVICE"
    }
}