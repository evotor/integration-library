package ru.evotor.framework.provider

import android.net.Uri

internal object FiscalDocumentContract {
    private const val PATH = "fiscal"

    val URI: Uri = Uri.withAppendedPath(DocumentContract.URI, PATH)

    const val COLUMN_DOCUMENT_NUMBER = "DOCUMENT_NUMBER"
    const val COLUMN_CREATION_DATE = "CREATION_DATE"
    const val COLUMN_KKT_REGISTRATION_NUMBER = "KKT_REGISTRATION_NUMBER"
    const val COLUMN_SESSION_NUMBER = "SESSION_NUMBER"
    const val COLUMN_FISCAL_STORAGE_NUMBER = "FISCAL_STORAGE_NUMBER"
    const val COLUMN_FISCAL_IDENTIFIER = "FISCAL_IDENTIFIER"
}