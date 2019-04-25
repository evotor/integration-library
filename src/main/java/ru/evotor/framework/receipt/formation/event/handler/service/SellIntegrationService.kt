package ru.evotor.framework.receipt.formation.event.handler.service

import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.formation.event.AddingPositionByBarcodeRequestedEvent
import ru.evotor.framework.receipt.formation.event.CreateProductByBarcodeEvent
import ru.evotor.framework.receipt.formation.event.CreatingProductIntentByBarcodeRequestedEvent

abstract class SellIntegrationService: ReceiptFormationIntegrationService(
        ACTION_BARCODE_RECEIVED,
        ACTION_CREATE_PRODUCT_BY_BARCODE,
        ACTION_SELECTED_FOR_CREATING_PRODUCT
) {

    @RequiresIntentAction(ACTION_BARCODE_RECEIVED)
    override fun handleEvent(event: AddingPositionByBarcodeRequestedEvent): AddingPositionByBarcodeRequestedEvent.Result? = null

    @RequiresIntentAction(ACTION_CREATE_PRODUCT_BY_BARCODE)
    override fun handleEvent(event: CreatingProductIntentByBarcodeRequestedEvent): CreatingProductIntentByBarcodeRequestedEvent.Result? = null

    @RequiresIntentAction(ACTION_SELECTED_FOR_CREATING_PRODUCT)
    override fun handleEvent(event: CreateProductByBarcodeEvent): Unit = Unit

    companion object {
        const val ACTION_BARCODE_RECEIVED = "ru.evotor.event.sell.BARCODE_RECEIVED"
        const val ACTION_CREATE_PRODUCT_BY_BARCODE = "ru.evotor.event.sell.CREATE_PRODUCT_BY_BARCODE"
        const val ACTION_SELECTED_FOR_CREATING_PRODUCT = "ru.evotor.event.sell.SELECTED_FOR_CREATING_PRODUCT"
    }
}