package ru.evotor.framework.receipt.position

import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.UnitOfMeasurement
import ru.evotor.framework.receipt.position.mapper.PositionMapper
import java.math.BigDecimal
import java.util.*

data class Position internal constructor(
        val uuid: UUID = UUID.randomUUID(),
        val productUuid: UUID? = null,
        val type: Type = Type.ORDINARY_PRODUCT,
        val name: String,
        val productCode: String? = null,
        val price: BigDecimal,
        val quantity: BigDecimal = BigDecimal.ONE,
        val unitOfMeasurement: UnitOfMeasurement? = null,
        val discount: BigDecimal = BigDecimal.ZERO,
        val settlementMethod: SettlementMethod = SettlementMethod.FullSettlement(),
        val agentRequisites: AgentRequisites? = null
) {
    val priceWithDiscountsAndSurcharges get() = price - discount

    constructor(fixedPriceProduct: Product) : this(
            productUuid = fixedPriceProduct.uuid,
            type = PositionMapper.getPositionType(fixedPriceProduct),
            name = fixedPriceProduct.name,
            productCode = fixedPriceProduct.code,
            price = fixedPriceProduct.sellingPrice!!,
            unitOfMeasurement = fixedPriceProduct.unitOfMeasurement
    )

    constructor(freePriceProduct: Product, price: BigDecimal) : this(
            productUuid = freePriceProduct.uuid,
            type = PositionMapper.getPositionType(freePriceProduct),
            name = freePriceProduct.name,
            productCode = freePriceProduct.code,
            price = price,
            unitOfMeasurement = freePriceProduct.unitOfMeasurement
    )

    constructor(
            uuid: UUID = UUID.randomUUID(),
            name: String,
            type: Type = Type.ORDINARY_PRODUCT,
            productCode: String? = null,
            price: BigDecimal,
            quantity: BigDecimal = BigDecimal(1),
            unitOfMeasurement: UnitOfMeasurement? = null,
            discount: BigDecimal = BigDecimal(0),
            settlementMethod: SettlementMethod = SettlementMethod.FullSettlement(),
            agentRequisites: AgentRequisites? = null
    ) : this(
            uuid,
            null,
            type,
            name,
            productCode,
            price,
            quantity,
            unitOfMeasurement,
            discount,
            settlementMethod,
            agentRequisites
    )

    enum class Type {
        ORDINARY_PRODUCT,
        EXCISABLE_PRODUCT,
        JOB,
        PAYABLE_SERVICE
    }
}