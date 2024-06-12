package ru.evotor.framework.receipt.formation.api.move_receipt_to_payment_stage

interface MoveCurrentReceiptDraftToPaymentStageCallback {
    fun onSuccess()
    fun onError(error: MoveCurrentReceiptDraftToPaymentStageException)
}
