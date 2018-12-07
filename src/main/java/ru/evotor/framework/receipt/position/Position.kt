package ru.evotor.framework.receipt.position

import ru.evotor.framework.inventory.Product
import java.util.*

data class Position internal constructor(
        val uuid: UUID,
        val productUuid: UUID?,
        val name: String
) {
    constructor(product: Product) : this(UUID.randomUUID(), product.uuid, product.name)

    constructor(uuid: UUID, name: String) : this(uuid, null, name)

    enum class Type {
        
    }
}