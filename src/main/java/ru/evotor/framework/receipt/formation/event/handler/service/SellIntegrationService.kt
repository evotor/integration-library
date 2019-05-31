package ru.evotor.framework.receipt.formation.event.handler.service

import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.formation.event.ReturnPositionsForBarcodeRequestedEvent
import ru.evotor.framework.receipt.formation.event.ReturnPurchaserRequisitesForPrintGroupRequestedEvent

abstract class SellIntegrationService : ReceiptFormationIntegrationService(
        ACTION_BARCODE_RECEIVED,
        ACTION_PURCHASER_REQUISITES
) {

    @RequiresIntentAction(ACTION_BARCODE_RECEIVED)
    override fun handleEvent(event: ReturnPositionsForBarcodeRequestedEvent): ReturnPositionsForBarcodeRequestedEvent.Result? = null

    @RequiresIntentAction(ACTION_PURCHASER_REQUISITES)
    override fun handleEvent(event: ReturnPurchaserRequisitesForPrintGroupRequestedEvent): ReturnPurchaserRequisitesForPrintGroupRequestedEvent.Result? = null

    companion object {
        const val ACTION_BARCODE_RECEIVED = "ru.evotor.event.sell.BARCODE_RECEIVED"
        const val ACTION_PURCHASER_REQUISITES = "ru.evotor.event.sell.PURCHASER_REQUISITES"

        const val PERMISSION = "ru.evotor.permission.SELL_INTEGRATION_SERVICE"
    }
}