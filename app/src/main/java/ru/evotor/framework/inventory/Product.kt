package ru.evotor.framework.inventory

import java.math.BigDecimal

/**
 * Created by a.kuznetsov on 26/03/2017.
 */
data class Product(
        val uuid: String,
        val code: String?,
        val type: ProductType,
        val productName: String,
        val price: BigDecimal,
        val quantity: BigDecimal,
        val description: String?,
        val measureName: String,
        val measurePrecision: String,
        val alcoholByVolume: BigDecimal?,
        val alcoholProductKindCode: Long?,
        val tareVolume: BigDecimal?
)