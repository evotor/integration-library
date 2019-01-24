package ru.evotor.framework.inventory.product

import ru.evotor.framework.Quantity
import ru.evotor.framework.UnitOfMeasurement
import java.math.BigDecimal
import java.math.RoundingMode

class AmountOfLiters(value: BigDecimal) : Quantity(value, value.scale(), UnitOfMeasurement.Liter()) {
    constructor(value: String) : this(BigDecimal(value))

    constructor(value: Int) : this(BigDecimal(value))

    constructor(value: Long) : this(BigDecimal(value))

    constructor(value: Double) : this(BigDecimal(value))

    override fun add(augend: BigDecimal) = AmountOfLiters(BigDecimal(this.toString()).add(augend))

    override fun subtract(subtrahend: BigDecimal) = AmountOfLiters(BigDecimal(this.toString()).subtract(subtrahend))

    override fun multiply(multiplicand: BigDecimal) = AmountOfLiters(BigDecimal(this.toString()).multiply(multiplicand))

    override fun divide(divisor: BigDecimal) = AmountOfLiters(BigDecimal(this.toString()).divide(divisor))

    override fun divide(divisor: BigDecimal, roundingMode: Int) = AmountOfLiters(BigDecimal(this.toString()).divide(divisor, roundingMode))

    override fun divide(divisor: BigDecimal, roundingMode: RoundingMode?) = AmountOfLiters(BigDecimal(this.toString()).divide(divisor, roundingMode))

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: Int) = AmountOfLiters(BigDecimal(this.toString()).divide(divisor, scale, roundingMode))

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: RoundingMode?) = AmountOfLiters(BigDecimal(this.toString()).divide(divisor, scale, roundingMode))

    override fun abs() = AmountOfLiters(BigDecimal(this.toString()).abs())

    override fun plus() = AmountOfLiters(BigDecimal(this.toString()).plus())

    override fun negate() = AmountOfLiters(BigDecimal(this.toString()).negate())
}