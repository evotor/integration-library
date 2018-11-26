package ru.evotor.framework

import java.util.*

abstract class Document internal constructor() {
    abstract val uuid: UUID

    enum class Form {
        PRINT,
        ELECTRONIC
    }
}