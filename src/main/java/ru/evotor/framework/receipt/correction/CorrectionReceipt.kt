package ru.evotor.framework.receipt.correction

import ru.evotor.framework.FiscalDocument
import ru.evotor.framework.kkt.FiscalRequisite
import ru.evotor.framework.kkt.FiscalTags
import java.util.*

data class CorrectionReceipt(
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
        override val fiscalIdentifier: Long
) : FiscalDocument()