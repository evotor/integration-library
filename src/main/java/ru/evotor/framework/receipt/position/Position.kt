package ru.evotor.framework.receipt.position

import ru.evotor.framework.inventory.Product
import java.math.BigDecimal
import java.util.*

data class Position internal constructor(
        val uuid: UUID = UUID.randomUUID(),
        val productUuid: UUID? = null,
        val type: Type,
        val name: String,
        val productCode: String? = null,
        val price: BigDecimal,
        val quantity: BigDecimal = BigDecimal(1),
        val unitOfMeasurement: String? = null,
        val discount: BigDecimal = BigDecimal(0),
        val settlementMethod: SettlementMethod = SettlementMethod.FullSettlement(),
        val agentRequisites: AgentRequisites? = null
) {
    val priceWithDiscountsAndSurcharges get() = price - discount

    val costWithDiscountsAndSurcharges get() = priceWithDiscountsAndSurcharges * quantity

    constructor(product: Product) : this(UUID.randomUUID(), product.uuid, product.name)

    constructor(uuid: UUID, name: String) : this(uuid, null, name)

    enum class Type {
        ORDINARY_PRODUCT,
        EXCISABLE_PRODUCT,
        JOB,
        SERVICE
    }
}