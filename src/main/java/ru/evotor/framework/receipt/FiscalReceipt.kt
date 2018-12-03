package ru.evotor.framework.receipt

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.FiscalDocument
import ru.evotor.framework.FiscalRequisite
import ru.evotor.framework.FutureFeature
import ru.evotor.framework.provider.FiscalDocumentContract
import ru.evotor.framework.receipt.mapper.FiscalReceiptMapper
import ru.evotor.framework.receipt.provider.FiscalReceiptContract
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.util.*

data class FiscalReceipt internal constructor(
        /**
         * Номер фискального документа
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_DOCUMENT_NUMBER, isPrinted = true, isSentToOfd = true)
        override val documentNumber: Long,

        /**
         * Дата и время создания фискального документа
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_CREATION_DATE, isPrinted = true, isSentToOfd = true)
        override val creationDate: Date,

        /**
         * Признак (тип) расчёта
         */
        @FiscalRequisite(tag = TAG_SETTLEMENT_TYPE, isPrinted = true, isSentToOfd = true)
        val settlementType: SettlementType,

        /**
         * Регистрационный номер ККТ
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_KKT_REGISTRATION_NUMBER, isPrinted = true, isSentToOfd = true)
        override val kktRegistrationNumber: Long,

        /**
         * Номер аппаратной смены
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_SESSION_NUMBER, isPrinted = true, isSentToOfd = true)
        override val sessionNumber: Long,

        /**
         * Номер фискального накопителя
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_FISCAL_STORAGE_NUMBER, isPrinted = true, isSentToOfd = true)
        override val fiscalStorageNumber: Long,

        /**
         * Фискальный признак (фискальный идентификатор) документа
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_FISCAL_IDENTIFIER, isPrinted = true, isSentToOfd = true)
        override val fiscalIdentifier: Long,

        /**
         * Был ли напечатан фискальный чек
         */
        val wasPrinted: Boolean
) : FiscalDocument(), IBundlable {
    companion object {
        /**
         * Фискальный тег "Признак расчёта"
         */
        const val TAG_SETTLEMENT_TYPE = 1054

        fun from(bundle: Bundle?): FiscalReceipt? = FiscalReceiptMapper.read(bundle)
    }

    override fun toBundle(): Bundle = FiscalReceiptMapper.write(this)

    @FutureFeature("Query на получение фискальных чеков")
    private class Query : FilterBuilder<Query, Query.SortOrder, FiscalReceipt>(FiscalReceiptContract.URI) {
        val documentNumber = addFieldFilter<Long>(FiscalDocumentContract.COLUMN_DOCUMENT_NUMBER)
        val creationDate = addFieldFilter<Date>(FiscalDocumentContract.COLUMN_CREATION_DATE)
        val settlementType = addFieldFilter<SettlementType>(FiscalReceiptContract.COLUMN_SETTLEMENT_TYPE)
        val kktRegistrationNumber = addFieldFilter<Long>(FiscalDocumentContract.COLUMN_KKT_REGISTRATION_NUMBER)
        val sessionNumber = addFieldFilter<Long>(FiscalDocumentContract.COLUMN_SESSION_NUMBER)
        val fiscalStorageNumber = addFieldFilter<Long>(FiscalDocumentContract.COLUMN_FISCAL_STORAGE_NUMBER)
        val fiscalIdentifier = addFieldFilter<Long>(FiscalDocumentContract.COLUMN_FISCAL_IDENTIFIER)

        class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
            val documentNumber = addFieldSorter(FiscalDocumentContract.COLUMN_DOCUMENT_NUMBER)
            val creationDate = addFieldSorter(FiscalDocumentContract.COLUMN_CREATION_DATE)
            val settlementType = addFieldSorter(FiscalReceiptContract.COLUMN_SETTLEMENT_TYPE)
            val kktRegistrationNumber = addFieldSorter(FiscalDocumentContract.COLUMN_KKT_REGISTRATION_NUMBER)
            val sessionNumber = addFieldSorter(FiscalDocumentContract.COLUMN_SESSION_NUMBER)
            val fiscalStorageNumber = addFieldSorter(FiscalDocumentContract.COLUMN_FISCAL_STORAGE_NUMBER)
            val fiscalIdentifier = addFieldSorter(FiscalDocumentContract.COLUMN_FISCAL_IDENTIFIER)

            override val currentSortOrder: SortOrder
                get() = this
        }

        override val currentQuery: Query
            get() = this

        override fun getValue(cursor: Cursor<FiscalReceipt>): FiscalReceipt = FiscalReceiptMapper.read(cursor)
    }
}