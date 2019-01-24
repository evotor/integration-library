package ru.evotor.framework.inventory.product.extension

import ru.evotor.framework.inventory.product.AmountOfLiters
import java.math.BigDecimal

interface AlcoholProduct : ProductExtension {
    val fsrarProductKindCode: Long
    val tareVolume: AmountOfLiters
    val alcoholPercentage: Float?
}