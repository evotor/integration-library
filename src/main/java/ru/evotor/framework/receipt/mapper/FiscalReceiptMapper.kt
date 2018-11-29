package ru.evotor.framework.receipt.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.mapper.DocumentMapper
import ru.evotor.framework.mapper.FiscalDocumentMapper
import ru.evotor.framework.optEnum
import ru.evotor.framework.receipt.FiscalReceipt
import ru.evotor.framework.receipt.SettlementType
import ru.evotor.framework.receipt.provider.FiscalReceiptContract
import ru.evotor.framework.safeGetEnum

internal object FiscalReceiptMapper {
    private const val KEY_SETTLEMENT_TYPE = "SETTLEMENT_TYPE"

    fun read(bundle: Bundle?): FiscalReceipt? = bundle?.let {
        FiscalReceipt(
          //      uuid = DocumentMapper.readUuid(it) ?: return null,
                documentNumber = FiscalDocumentMapper.readDocumentNumber(it) ?: return null,
                creationDate = FiscalDocumentMapper.readCreationDate(it) ?: return null,
                settlementType = it.safeGetEnum(KEY_SETTLEMENT_TYPE, SettlementType.values())
                        ?: return null,
                kktRegistrationNumber = FiscalDocumentMapper.readKktRegistrationNumber(it)
                        ?: return null,
                sessionNumber = FiscalDocumentMapper.readSessionNumber(it) ?: return null,
                fiscalStorageNumber = FiscalDocumentMapper.readFiscalStorageNumber(it)
                        ?: return null,
                fiscalIdentifier = FiscalDocumentMapper.readFiscalIdentifier(it) ?: return null
        )
    }

    fun read(cursor: Cursor): FiscalReceipt? {
        return FiscalReceipt(
              //  uuid = DocumentMapper.readUuid(cursor) ?: return null,
                documentNumber = FiscalDocumentMapper.readDocumentNumber(cursor) ?: return null,
                creationDate = FiscalDocumentMapper.readCreationDate(cursor) ?: return null,
                settlementType = cursor.optEnum(FiscalReceiptContract.COLUMN_SETTLEMENT_TYPE, SettlementType.values())
                        ?: return null,
                kktRegistrationNumber = FiscalDocumentMapper.readKktRegistrationNumber(cursor)
                        ?: return null,
                sessionNumber = FiscalDocumentMapper.readSessionNumber(cursor) ?: return null,
                fiscalStorageNumber = FiscalDocumentMapper.readFiscalStorageNumber(cursor)
                        ?: return null,
                fiscalIdentifier = FiscalDocumentMapper.readFiscalIdentifier(cursor) ?: return null
        )
    }

    fun write(fiscalReceipt: FiscalReceipt) = FiscalDocumentMapper.write(fiscalReceipt).apply {
        this.putInt(KEY_SETTLEMENT_TYPE, fiscalReceipt.settlementType.ordinal)
    }
}