package ru.evotor.framework.kkt.api

import java.util.*

interface DocumentRegistrationCallback {
    fun onSuccess(documentUuid: UUID?)
    fun onError(error: DocumentRegistrationException)
}