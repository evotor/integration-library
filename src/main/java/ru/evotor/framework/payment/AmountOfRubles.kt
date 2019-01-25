package ru.evotor.framework.payment

import ru.evotor.framework.Quantity
import ru.evotor.framework.UnitOfMeasurement
import java.math.BigDecimal
import java.math.RoundingMode

private const val SCALE = 2

class AmountOfRubles(value: BigDecimal) : Quantity(value.setScale(SCALE), UnitOfMeasurement.Ruble()) {
    constructor(value: String) : this(BigDecimal(value))

    constructor(value: Int) : this(BigDecimal(value))

    constructor(value: Long) : this(BigDecimal(value))

    constructor(value: Double) : this(BigDecimal(value))

    override fun add(augend: BigDecimal) = AmountOfRubles(super.add(augend))

    override fun subtract(subtrahend: BigDecimal) = AmountOfRubles(super.subtract(subtrahend))

    override fun multiply(multiplicand: BigDecimal) = AmountOfRubles(super.multiply(multiplicand))

    override fun divide(divisor: BigDecimal) = AmountOfRubles(super.divide(divisor))

    override fun divide(divisor: BigDecimal, roundingMode: Int) = AmountOfRubles(super.divide(divisor, roundingMode))

    override fun divide(divisor: BigDecimal, roundingMode: RoundingMode?) = AmountOfRubles(super.divide(divisor, roundingMode))

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: Int) = AmountOfRubles(super.divide(divisor, scale, roundingMode))

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: RoundingMode?) = AmountOfRubles(super.divide(divisor, scale, roundingMode))

    override fun abs() = AmountOfRubles(super.abs())

    override fun plus() = AmountOfRubles(super.plus())

    override fun negate() = AmountOfRubles(super.negate())
}