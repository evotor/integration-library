package ru.evotor.framework.receipt

import android.content.Context
import android.net.Uri
import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.FiscalDocument
import ru.evotor.framework.FiscalRequisite
import ru.evotor.framework.FutureFeature
import ru.evotor.framework.provider.FiscalReceiptColumns
import ru.evotor.framework.receipt.mapper.FiscalReceiptMapper
import ru.evotor.framework.receipt.provider.ReceiptContract
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.util.*

data class FiscalReceipt internal constructor(
        /**
         * Номер фискального документа
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_DOCUMENT_NUMBER)
        override val documentNumber: Long,

        /**
         * Дата и время создания фискального документа
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_CREATION_DATE)
        override val creationDate: Date,

        /**
         * Тип (признак) расчёта
         */
        @FiscalRequisite(tag = TAG_SETTLEMENT_TYPE)
        val settlementType: SettlementType,

        /**
         * Регистрационный номер ККТ
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_KKT_REGISTRATION_NUMBER)
        override val kktRegistrationNumber: Long,

        /**
         * Номер аппаратной смены
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_SESSION_NUMBER)
        override val sessionNumber: Long,

        /**
         * Номер фискального накопителя
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_FISCAL_STORAGE_NUMBER)
        override val fiscalStorageNumber: Long,

        /**
         * Фискальный признак (фискальный идентификатор) документа
         */
        @FiscalRequisite(tag = FiscalDocument.TAG_FISCAL_IDENTIFIER)
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
    private class Query : FilterBuilder<Query, Query.SortOrder, FiscalReceipt>(
            Uri.withAppendedPath(ReceiptContract.BASE_URI, ReceiptContract.PATH_FISCAL_RECEIPTS)
    ) {
        override val currentQuery: Query
            get() = this
        val documentNumber = addFieldFilter<Long>(ReceiptContract.FiscalReceiptColumns.DOCUMENT_NUMBER)
        val creationDate = addFieldFilter<Date>(ReceiptContract.FiscalReceiptColumns.CREATION_DATE)
        val settlementType = addFieldFilter<SettlementType>(ReceiptContract.FiscalReceiptColumns.SETTLEMENT_TYPE)
        val kktRegistrationNumber = addFieldFilter<Long>(ReceiptContract.FiscalReceiptColumns.KKT_REGISTRATION_NUMBER)
        val sessionNumber = addFieldFilter<Long>(ReceiptContract.FiscalReceiptColumns.SESSION_NUMBER)
        val fiscalStorageNumber = addFieldFilter<Long>(ReceiptContract.FiscalReceiptColumns.FISCAL_STORAGE_NUMBER)
        val fiscalIdentifier = addFieldFilter<Long>(ReceiptContract.FiscalReceiptColumns.FISCAL_IDENTIFIER)

        class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
            override val currentSortOrder: SortOrder
                get() = this
            val documentNumber = addFieldSorter(ReceiptContract.FiscalReceiptColumns.DOCUMENT_NUMBER)
            val creationDate = addFieldSorter(ReceiptContract.FiscalReceiptColumns.CREATION_DATE)
            val settlementType = addFieldSorter(ReceiptContract.FiscalReceiptColumns.SETTLEMENT_TYPE)
            val kktRegistrationNumber = addFieldSorter(ReceiptContract.FiscalReceiptColumns.KKT_REGISTRATION_NUMBER)
            val sessionNumber = addFieldSorter(ReceiptContract.FiscalReceiptColumns.SESSION_NUMBER)
            val fiscalStorageNumber = addFieldSorter(ReceiptContract.FiscalReceiptColumns.FISCAL_STORAGE_NUMBER)
            val fiscalIdentifier = addFieldSorter(ReceiptContract.FiscalReceiptColumns.FISCAL_IDENTIFIER)
        }

        override fun getValue(context: Context, cursor: Cursor<FiscalReceipt>): FiscalReceipt = FiscalReceiptMapper.read(cursor)
    }
}