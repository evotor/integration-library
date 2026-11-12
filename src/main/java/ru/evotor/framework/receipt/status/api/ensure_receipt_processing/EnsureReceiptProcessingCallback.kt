package ru.evotor.framework.receipt.status.api.ensure_receipt_processing

interface EnsureReceiptProcessingCallback {
    fun onSuccess(info: ReceiptProcessingInfo)

    fun onError(error: EnsureReceiptProcessingException)
}
