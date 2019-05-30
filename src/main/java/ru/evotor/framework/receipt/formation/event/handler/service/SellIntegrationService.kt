package ru.evotor.framework.receipt.formation.event.handler.service

import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.formation.event.ReturnPositionsForBarcodeRequestedEvent

abstract class SellIntegrationService : ReceiptFormationIntegrationService(
        ACTION_BARCODE_RECEIVED
) {

    @RequiresIntentAction(ACTION_BARCODE_RECEIVED)
    override fun handleEvent(event: ReturnPositionsForBarcodeRequestedEvent): ReturnPositionsForBarcodeRequestedEvent.Result? = null

    companion object {
        const val ACTION_BARCODE_RECEIVED = "ru.evotor.event.sell.BARCODE_RECEIVED"

        const val PERMISSION = "ru.evotor.permission.SELL_INTEGRATION_SERVICE"
    }
}