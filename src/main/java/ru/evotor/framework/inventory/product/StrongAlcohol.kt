package ru.evotor.framework.inventory.product

import ru.evotor.framework.inventory.product.extension.AlcoholProduct
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
import ru.evotor.framework.inventory.product.extension.FixedPriceProduct
import java.math.BigDecimal
import java.util.*

data class StrongAlcohol internal constructor(
        override val uuid: UUID,
        override val groupUuid: UUID?,
        override val name: String,
        override val code: String?,
        override val fsrarProductKindCode: Long,
        override val vendorCode: String?,
        override val barcodes: List<String>?,
        override val mark: String,
        override val purchasePrice: BigDecimal?,
        override val sellingPrice: BigDecimal,
        override val vatRate: VatRate,
        override val quantity: BigDecimal,
        override val unitOfMeasurement: UnitOfMeasurement,
        override val tareVolume: BigDecimal,
        override val alcoholPercentage: BigDecimal,
        override val description: String?,
        override val allowedToSell: Boolean
) : Product(), FixedPriceProduct,  AlcoholProduct, ExcisableProduct