package ru.evotor.framework

import ru.evotor.framework.kkt.FfdVersion
import java.util.*

abstract class FiscalDocument internal constructor() : Document() {
    abstract val name: String
    abstract val formCode: String
    abstract val number: Long
    abstract val creationDate: Date
    abstract val userName: String
    abstract val userInn: String
    abstract val settlementsAddress: String
    abstract val settlementsPlace: String
    abstract val kktRegistrationNumber: Long
    abstract val sessionNumber: Long
    abstract val registeredFfdVersion: FfdVersion
    abstract val fiscalStorageNumber: Long
    abstract val fpd: Long
    abstract val fps: Long
}