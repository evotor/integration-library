package ru.evotor.framework.provider

interface FiscalDocumentColumns {
    val DOCUMENT_NUMBER get() = "DOCUMENT_NUMBER"
    val CREATION_DATE get() = "CREATION_DATE"
    val KKT_REGISTRATION_NUMBER get() = "KKT_REGISTRATION_NUMBER"
    val SESSION_NUMBER get() = "SESSION_NUMBER"
    val FISCAL_STORAGE_NUMBER get() = "FISCAL_STORAGE_NUMBER"
    val FISCAL_IDENTIFIER get() = "FISCAL_IDENTIFIER"
}