package ru.evotor.framework.inventory.product.extension

import ru.evotor.framework.Volume

interface AlcoholProduct : ProductExtension {
    val fsrarProductKindCode: Long
    val tareVolume: Volume
    val alcoholPercentage: Float?
}