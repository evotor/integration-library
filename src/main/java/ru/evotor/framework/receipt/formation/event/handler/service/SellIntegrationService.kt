package ru.evotor.framework.receipt.formation.event.handler.service

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.formation.event.InputPurchaserRequisitesEvent
import ru.evotor.framework.receipt.formation.event.ReturnPositionsForBarcodeRequestedEvent
import ru.evotor.framework.receipt.formation.event.ReturnPurchaserRequisitesForPrintGroupRequestedEvent

abstract class SellIntegrationService : IntegrationServiceV2() {

    final override fun onEvent(action: String, bundle: Bundle) = when (action) {
        ACTION_BARCODE_RECEIVED -> ReturnPositionsForBarcodeRequestedEvent.from(bundle)?.let { handleEvent(it) }
        ACTION_PURCHASER_REQUISITES -> ReturnPurchaserRequisitesForPrintGroupRequestedEvent.from(bundle)?.let { handleEvent(it) }
        ACTION_PURCHASER_REQUISITES_INPUT -> InputPurchaserRequisitesEvent.from(bundle)?.let { handleEvent(it) }
        else -> null
    }

    @RequiresIntentAction(ACTION_BARCODE_RECEIVED)
    open fun handleEvent(event: ReturnPositionsForBarcodeRequestedEvent): ReturnPositionsForBarcodeRequestedEvent.Result? = null

    @RequiresIntentAction(ACTION_PURCHASER_REQUISITES)
    open fun handleEvent(event: ReturnPurchaserRequisitesForPrintGroupRequestedEvent): ReturnPurchaserRequisitesForPrintGroupRequestedEvent.Result? = null

    @RequiresIntentAction(ACTION_PURCHASER_REQUISITES_INPUT)
    open fun handleEvent(event: InputPurchaserRequisitesEvent): IBundlable? = null

    companion object {
        const val ACTION_BARCODE_RECEIVED = "ru.evotor.event.sell.BARCODE_RECEIVED"
        const val ACTION_PURCHASER_REQUISITES = "ru.evotor.event.sell.PURCHASER_REQUISITES"
        const val ACTION_PURCHASER_REQUISITES_INPUT = "ru.evotor.event.sell.PURCHASER_REQUISITES_INPUT"

        const val PERMISSION = "ru.evotor.permission.SELL_INTEGRATION_SERVICE"
    }
}