package ru.evotor.framework

import android.os.Bundle
import ru.evotor.framework.mapper.FiscalDocumentMapper
import java.util.*

abstract class FiscalDocument internal constructor() : Document() {

    abstract val documentNumber: Long
    abstract val creationDate: Date
    abstract val kktRegistrationNumber: Long
    abstract val sessionNumber: Long
    abstract val fiscalStorageNumber: Long
    abstract val fiscalIdentifier: Long

    companion object {
        /**
         * Фискальный тег "Номер ФД"
         */
        const val TAG_DOCUMENT_NUMBER = 1040

        /**
         * Фискальный тег "Дата, время"
         */
        const val TAG_CREATION_DATE = 1012

        /**
         * Фискальный тег "Регистрационный номер ККТ"
         */
        const val TAG_KKT_REGISTRATION_NUMBER = 1037

        /**
         * Фискальный тег "Номер смены"
         */
        const val TAG_SESSION_NUMBER = 1038

        /**
         * Фискальный тег "Номер ФН"
         */
        const val TAG_FISCAL_STORAGE_NUMBER = 1041

        /**
         * Фискальный тег "ФПД"
         */
        const val TAG_FISCAL_IDENTIFIER = 1077
    }

    override fun toBundle(): Bundle = FiscalDocumentMapper.write(this)
}