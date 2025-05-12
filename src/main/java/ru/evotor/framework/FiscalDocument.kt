package ru.evotor.framework

import android.os.Bundle
import ru.evotor.framework.mapper.FiscalDocumentMapper
import java.util.*

abstract class FiscalDocument internal constructor() : Document() {
    abstract val documentNumber: Long
    abstract val creationDate: Date

    @Deprecated("Используйте kktRegNumber", replaceWith = ReplaceWith("kktRegNumber"))
    abstract val kktRegistrationNumber: Long
    abstract val kktRegNumber: String
    abstract val sessionNumber: Long
    abstract val fiscalStorageNumber: Long
    abstract val fiscalIdentifier: Long

    override fun toBundle(): Bundle = FiscalDocumentMapper.write(this)
}
