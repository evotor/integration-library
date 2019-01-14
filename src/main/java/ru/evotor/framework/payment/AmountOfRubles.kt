package ru.evotor.framework.payment

import ru.evotor.framework.Quantity
import ru.evotor.framework.UnitOfMeasurement
import java.math.BigDecimal
import java.math.RoundingMode

private const val SCALE = 2

class AmountOfRubles(value: BigDecimal) : Quantity(value, SCALE, UnitOfMeasurement.Ruble()) {
    constructor(value: String) : this(BigDecimal(value))

    constructor(value: Int) : this(BigDecimal(value))

    constructor(value: Long) : this(BigDecimal(value))

    constructor(value: Double) : this(BigDecimal(value))

    override fun add(augend: BigDecimal) = AmountOfRubles(BigDecimal(this.toString()).add(augend))

    override fun subtract(subtrahend: BigDecimal) = AmountOfRubles(BigDecimal(this.toString()).subtract(subtrahend))

    override fun multiply(multiplicand: BigDecimal) = AmountOfRubles(BigDecimal(this.toString()).multiply(multiplicand))

    override fun divide(divisor: BigDecimal) = AmountOfRubles(BigDecimal(this.toString()).divide(divisor))

    override fun divide(divisor: BigDecimal, roundingMode: Int) = AmountOfRubles(BigDecimal(this.toString()).divide(divisor, roundingMode))

    override fun divide(divisor: BigDecimal, roundingMode: RoundingMode?) = AmountOfRubles(BigDecimal(this.toString()).divide(divisor, roundingMode))

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: Int) = AmountOfRubles(BigDecimal(this.toString()).divide(divisor, scale, roundingMode))

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: RoundingMode?) = AmountOfRubles(BigDecimal(this.toString()).divide(divisor, scale, roundingMode))

    override fun abs() = AmountOfRubles(BigDecimal(this.toString()).abs())

    override fun plus() = AmountOfRubles(BigDecimal(this.toString()).plus())

    override fun negate() = AmountOfRubles(BigDecimal(this.toString()).negate())
}