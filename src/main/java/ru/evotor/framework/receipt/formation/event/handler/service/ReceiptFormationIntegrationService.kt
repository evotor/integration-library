package ru.evotor.framework.receipt.formation.event.handler.service

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.receipt.formation.event.AddingPositionByBarcodeRequestedEvent
import ru.evotor.framework.receipt.formation.event.CreateProductByBarcodeEvent
import ru.evotor.framework.receipt.formation.event.CreatingProductIntentByBarcodeRequestedEvent

abstract class ReceiptFormationIntegrationService(
        private val actionBarcodeReceived: String,
        private val actionCreateProductByBarcode: String,
        private val actionSelectedForCreatingProduct: String
) : IntegrationServiceV2() {

    final override fun onEvent(action: String, bundle: Bundle): IBundlable? {
        when (action) {
            actionBarcodeReceived -> {
                return handleEvent(AddingPositionByBarcodeRequestedEvent.from(bundle)
                        ?: return null)
            }
            actionCreateProductByBarcode -> {
                return handleEvent(CreatingProductIntentByBarcodeRequestedEvent.from(bundle)
                        ?: return null)
            }
            actionSelectedForCreatingProduct -> {
                handleEvent(CreateProductByBarcodeEvent.from(bundle)
                        ?: return null)
            }
        }
        return null
    }

    open fun handleEvent(event: AddingPositionByBarcodeRequestedEvent): AddingPositionByBarcodeRequestedEvent.Result? = null
    open fun handleEvent(event: CreatingProductIntentByBarcodeRequestedEvent): CreatingProductIntentByBarcodeRequestedEvent.Result? = null
    open fun handleEvent(event: CreateProductByBarcodeEvent): Unit = Unit

}