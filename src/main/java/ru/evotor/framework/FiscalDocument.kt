package ru.evotor.framework

import android.os.Bundle
import ru.evotor.framework.mapper.FiscalDocumentMapper
import java.util.*

abstract class FiscalDocument internal constructor() : Document() {
    abstract val documentNumber: Long
    abstract val creationDate: Date
    abstract val kktRegistrationNumber: String
    abstract val sessionNumber: Long
    abstract val fiscalStorageNumber: String
    abstract val fiscalIdentifier: String

    override fun toBundle(): Bundle = FiscalDocumentMapper.write(this)
}
