package ru.evotor.framework.inventory

import java.util.*

abstract class Product internal constructor() {
    abstract val uuid: UUID
    abstract val name: String
}