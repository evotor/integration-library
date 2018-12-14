package ru.evotor.framework.inventory.product.category.entertainment

import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.UnitOfMeasurement
import ru.evotor.framework.inventory.product.VatRate
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
import java.math.BigDecimal
import java.util.*

data class Tobacco internal constructor(
        override val uuid: UUID,
        override val groupUuid: UUID?,
        override val name: String,
        override val code: String?,
        override val vendorCode: String?,
        override val barcodes: List<String>?,
        override val mark: String,
        override val purchasePrice: BigDecimal?,
        override val sellingPrice: BigDecimal?,
        override val vatRate: VatRate,
        override val quantity: BigDecimal,
        override val unitOfMeasurement: UnitOfMeasurement,
        override val description: String?,
        override val allowedToSell: Boolean
) : Product(), ExcisableProduct