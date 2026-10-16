package ru.evotor.framework.receipt.status.api.resume_receipt_processing

interface ResumeReceiptProcessingCallback {
    fun onSuccess()

    fun onError(error: ResumeReceiptProcessingException)
}
