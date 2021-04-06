package ru.evotor.framework

import java.math.BigDecimal

internal fun Long.formatQuantity(): BigDecimal {
    return BigDecimal(this).divide(BigDecimal(1000))
}

internal fun Long.formatPrice(): BigDecimal {
    return BigDecimal(this).divide(BigDecimal(100))
}