package ru.evotor.framework.receipt.formation.event.handler.service

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.receipt.formation.event.ReturnPositionsForBarcodeRequestedEvent

abstract class ReceiptFormationIntegrationService(
        private val actionBarcodeReceived: String
) : IntegrationServiceV2() {

    final override fun onEvent(action: String, bundle: Bundle): IBundlable? {
        when (action) {
            actionBarcodeReceived -> {
                return handleEvent(ReturnPositionsForBarcodeRequestedEvent.from(bundle)
                        ?: return null)
            }
        }
        return null
    }

    open fun handleEvent(event: ReturnPositionsForBarcodeRequestedEvent): ReturnPositionsForBarcodeRequestedEvent.Result? = null

}