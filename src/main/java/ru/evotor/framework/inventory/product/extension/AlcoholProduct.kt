package ru.evotor.framework.inventory.product.extension

import java.math.BigDecimal

interface AlcoholProduct : ProductExtension {
    val fsrarProductKindCode: Long
    val tareVolume: BigDecimal
    val alcoholPercentage: BigDecimal?
}