package ru.evotor.framework.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.*
import ru.evotor.framework.provider.FiscalDocumentContract
import java.text.SimpleDateFormat
import java.util.*

internal object FiscalDocumentMapper {
    private const val KEY_DOCUMENT_NUMBER = "DOCUMENT_NUMBER"
    private const val KEY_CREATION_DATE = "CREATION_DATE"
    private const val KEY_KKT_REGISTRATION_NUMBER = "KKT_REGISTRATION_NUMBER"
    private const val KEY_SESSION_NUMBER = "SESSION_NUMBER"
    private const val KEY_FISCAL_STORAGE_NUMBER = "FISCAL_STORAGE_NUMBER"
    private const val KEY_FISCAL_IDENTIFIER = "FISCAL_IDENTIFIER"

    private const val FISCAL_DATE_PATTERN = "ddMMyyyyHHmm"

    fun readDocumentNumber(bundle: Bundle?): Long? = bundle?.safeGetLong(KEY_DOCUMENT_NUMBER)

    fun readDocumentNumber(cursor: Cursor): Long? = cursor.optLong(FiscalDocumentContract.COLUMN_DOCUMENT_NUMBER)

    fun readCreationDate(bundle: Bundle?): Date? = bundle?.safeGetSerializable(KEY_CREATION_DATE)

    fun readCreationDate(cursor: Cursor): Date? = cursor.optString(FiscalDocumentContract.COLUMN_CREATION_DATE)
            ?.let { dateString ->
                SimpleDateFormat(FISCAL_DATE_PATTERN, Locale.getDefault()).parse(dateString)
            }

    fun readKktRegistrationNumber(bundle: Bundle?): Long? = bundle?.safeGetLong(KEY_KKT_REGISTRATION_NUMBER)

    fun readKktRegistrationNumber(cursor: Cursor): Long? = cursor.optLong(FiscalDocumentContract.COLUMN_KKT_REGISTRATION_NUMBER)

    fun readSessionNumber(bundle: Bundle?): Long? = bundle?.safeGetLong(KEY_SESSION_NUMBER)

    fun readSessionNumber(cursor: Cursor): Long? = cursor.optLong(FiscalDocumentContract.COLUMN_SESSION_NUMBER)

    fun readFiscalStorageNumber(bundle: Bundle?): Long? = bundle?.safeGetLong(KEY_FISCAL_STORAGE_NUMBER)

    fun readFiscalStorageNumber(cursor: Cursor): Long? = cursor.optLong(FiscalDocumentContract.COLUMN_FISCAL_STORAGE_NUMBER)

    fun readFiscalIdentifier(bundle: Bundle?): Long? = bundle?.safeGetLong(KEY_FISCAL_IDENTIFIER)

    fun readFiscalIdentifier(cursor: Cursor): Long? = cursor.optLong(FiscalDocumentContract.COLUMN_FISCAL_IDENTIFIER)

    fun write(fiscalDocument: FiscalDocument) = DocumentMapper.write(fiscalDocument).apply {
        this.putLong(KEY_DOCUMENT_NUMBER, fiscalDocument.documentNumber)
        this.putSerializable(KEY_CREATION_DATE, fiscalDocument.creationDate)
        this.putLong(KEY_KKT_REGISTRATION_NUMBER, fiscalDocument.kktRegistrationNumber)
        this.putLong(KEY_SESSION_NUMBER, fiscalDocument.sessionNumber)
        this.putLong(KEY_FISCAL_STORAGE_NUMBER, fiscalDocument.fiscalStorageNumber)
        this.putLong(KEY_FISCAL_IDENTIFIER, fiscalDocument.fiscalIdentifier)
    }
}