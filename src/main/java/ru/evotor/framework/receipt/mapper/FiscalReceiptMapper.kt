package ru.evotor.framework.receipt.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.mapper.FiscalDocumentMapper
import ru.evotor.framework.safeGetEnum
import ru.evotor.framework.receipt.FiscalReceipt
import ru.evotor.framework.receipt.SettlementType
import ru.evotor.framework.receipt.provider.FiscalReceiptContract
import ru.evotor.framework.safeGetBoolean

internal object FiscalReceiptMapper {
    private const val KEY_SETTLEMENT_TYPE = "SETTLEMENT_TYPE"
    private const val KEY_WAS_PRINTED = "WAS_PRINTED"

    fun read(bundle: Bundle?): FiscalReceipt? = bundle?.let {
        FiscalReceipt(
                documentNumber = FiscalDocumentMapper.readDocumentNumber(it) ?: return null,
                creationDate = FiscalDocumentMapper.readCreationDate(it) ?: return null,
                settlementType = it.safeGetEnum(KEY_SETTLEMENT_TYPE, SettlementType.values())
                        ?: return null,
                kktRegistrationNumber = FiscalDocumentMapper.readKktRegistrationNumber(it)
                        ?: return null,
                sessionNumber = FiscalDocumentMapper.readSessionNumber(it) ?: return null,
                fiscalStorageNumber = FiscalDocumentMapper.readFiscalStorageNumber(it)
                        ?: return null,
                fiscalIdentifier = FiscalDocumentMapper.readFiscalIdentifier(it) ?: return null,
                wasPrinted = it.getBoolean(KEY_WAS_PRINTED)
        )
    }

    fun read(cursor: Cursor) = FiscalReceipt(
            documentNumber = FiscalDocumentMapper.readDocumentNumber(cursor)
                    ?: throwOutdatedLibraryException(),
            creationDate = FiscalDocumentMapper.readCreationDate(cursor)
                    ?: throwOutdatedLibraryException(),
            settlementType = cursor.safeGetEnum(
                    FiscalReceiptContract.COLUMN_SETTLEMENT_TYPE, SettlementType.values()
            ) ?: throwOutdatedLibraryException(),
            kktRegistrationNumber = FiscalDocumentMapper.readKktRegistrationNumber(cursor)
                    ?: throwOutdatedLibraryException(),
            sessionNumber = FiscalDocumentMapper.readSessionNumber(cursor)
                    ?: throwOutdatedLibraryException(),
            fiscalStorageNumber = FiscalDocumentMapper.readFiscalStorageNumber(cursor)
                    ?: throwOutdatedLibraryException(),
            fiscalIdentifier = FiscalDocumentMapper.readFiscalIdentifier(cursor)
                    ?: throwOutdatedLibraryException(),
            wasPrinted = cursor.safeGetBoolean(KEY_WAS_PRINTED) ?: throwOutdatedLibraryException()
    )

    private fun throwOutdatedLibraryException(): Nothing =
            throw IntegrationLibraryMappingException("${FiscalReceipt::class.java.name} field")

    fun write(fiscalReceipt: FiscalReceipt) = FiscalDocumentMapper.write(fiscalReceipt).apply {
        this.putInt(KEY_SETTLEMENT_TYPE, fiscalReceipt.settlementType.ordinal)
    }
}