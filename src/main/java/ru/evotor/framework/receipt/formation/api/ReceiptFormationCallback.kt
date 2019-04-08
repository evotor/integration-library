package ru.evotor.framework.receipt.formation.api

interface ReceiptFormationCallback {
    fun onSuccess()
    fun onError(error: ReceiptFormationException)
}