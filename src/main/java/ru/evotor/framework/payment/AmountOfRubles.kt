package ru.evotor.framework.payment

import java.math.BigDecimal

class AmountOfRubles(value: BigDecimal) : BigDecimal(value.toPlainString()) {
    constructor(value: String) : this(BigDecimal(value))

    constructor(value: Int) : this(BigDecimal(value))

    constructor(value: Long) : this(BigDecimal(value))

    constructor(value: Double) : this(BigDecimal(value))

    override fun toByte() = byteValueExact()

    override fun toChar() = BigDecimal(this.toString()).toChar()

    override fun toShort() = shortValueExact()
}