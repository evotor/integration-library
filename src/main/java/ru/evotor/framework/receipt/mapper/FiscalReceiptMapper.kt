package ru.evotor.framework.receipt.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.mapper.FiscalDocumentMapper
import ru.evotor.framework.safeGetEnum
import ru.evotor.framework.receipt.FiscalReceipt
import ru.evotor.framework.receipt.SettlementType
import ru.evotor.framework.receipt.provider.ReceiptContract
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
                    ?: throw IntegrationLibraryMappingException(FiscalReceipt::class.java, FiscalReceipt::documentNumber),
            creationDate = FiscalDocumentMapper.readCreationDate(cursor)
                    ?: throw IntegrationLibraryMappingException(FiscalReceipt::class.java, FiscalReceipt::creationDate),
            settlementType = cursor.safeGetEnum(ReceiptContract.FiscalReceiptColumns.SETTLEMENT_TYPE, SettlementType.values())
                    ?: throw IntegrationLibraryMappingException(FiscalReceipt::class.java, FiscalReceipt::settlementType),
            kktRegistrationNumber = FiscalDocumentMapper.readKktRegistrationNumber(cursor)
                    ?: throw IntegrationLibraryMappingException(FiscalReceipt::class.java, FiscalReceipt::kktRegistrationNumber),
            sessionNumber = FiscalDocumentMapper.readSessionNumber(cursor)
                    ?: throw IntegrationLibraryMappingException(FiscalReceipt::class.java, FiscalReceipt::sessionNumber),
            fiscalStorageNumber = FiscalDocumentMapper.readFiscalStorageNumber(cursor)
                    ?: throw IntegrationLibraryMappingException(FiscalReceipt::class.java, FiscalReceipt::fiscalStorageNumber),
            fiscalIdentifier = FiscalDocumentMapper.readFiscalIdentifier(cursor)
                    ?: throw IntegrationLibraryMappingException(FiscalReceipt::class.java, FiscalReceipt::fiscalIdentifier),
            wasPrinted = cursor.safeGetBoolean(KEY_WAS_PRINTED)
                    ?: throw IntegrationLibraryMappingException(FiscalReceipt::class.java, FiscalReceipt::wasPrinted)
    )

    fun write(fiscalReceipt: FiscalReceipt) = FiscalDocumentMapper.write(fiscalReceipt).apply {
        this.putInt(KEY_SETTLEMENT_TYPE, fiscalReceipt.settlementType.ordinal)
    }
}