package ru.evotor.framework.receipt

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.FiscalDocument
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.FutureFeature
import ru.evotor.framework.kkt.FiscalTags
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
        @FiscalRequisite(tag = FiscalTags.DOCUMENT_NUMBER)
        override val documentNumber: Long,

        /**
         * Дата и время создания фискального документа
         */
        @FiscalRequisite(tag = FiscalTags.CREATION_DATE)
        override val creationDate: Date,

        /**
         * Признак (тип) расчёта
         */
        @FiscalRequisite(tag = FiscalTags.SETTLEMENT_TYPE)
        val settlementType: SettlementType,

        /**
         * Регистрационный номер ККТ
         */
        @FiscalRequisite(tag = FiscalTags.KKT_REGISTRATION_NUMBER)
        override val kktRegistrationNumber: Long,

        /**
         * Номер аппаратной смены
         */
        @FiscalRequisite(tag = FiscalTags.SESSION_NUMBER)
        override val sessionNumber: Long,

        /**
         * Номер фискального накопителя
         */
        @FiscalRequisite(tag = FiscalTags.FISCAL_STORAGE_NUMBER)
        override val fiscalStorageNumber: Long,

        /**
         * Фискальный признак (фискальный идентификатор) документа
         */
        @FiscalRequisite(tag = FiscalTags.FISCAL_IDENTIFIER)
        override val fiscalIdentifier: Long,

        /**
         * Был ли напечатан фискальный чек
         */
        val wasPrinted: Boolean
) : FiscalDocument(), IBundlable {
    companion object {
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