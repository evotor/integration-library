package ru.evotor.framework.inventory.product.extension

import java.math.BigDecimal

interface FixedPriceProduct: ProductExtension {
    val sellingPrice: BigDecimal
}