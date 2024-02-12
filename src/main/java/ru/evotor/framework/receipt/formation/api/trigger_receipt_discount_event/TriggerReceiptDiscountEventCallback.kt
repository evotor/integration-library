package ru.evotor.framework.receipt.formation.api.trigger_receipt_discount_event

interface TriggerReceiptDiscountEventCallback {

    fun onSuccess()

    fun onError(error: TriggerReceiptDiscountEventException)
}
