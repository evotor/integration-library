package ru.evotor.framework.receipt.status.api.resume_locked_receipt

interface ResumeLockedReceiptCallback {
    fun onSuccess()

    fun onError(error: ResumeLockedReceiptException)
}
