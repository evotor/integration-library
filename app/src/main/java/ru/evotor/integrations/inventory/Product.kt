package ru.evotor.integrations.inventory

import java.math.BigDecimal

/**
 * Created by a.kuznetsov on 26/03/2017.
 */
data class Product(
        val uuid: String,
        val productName: String,
        val price: BigDecimal,
        val quantity: BigDecimal,
        val description: String?
)