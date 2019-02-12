package ru.evotor.framework

import ru.evotor.framework.mapper.QuantityMapper
import ru.evotor.query.FilterBuilder
import java.math.BigDecimal
import java.math.RoundingMode

open class Quantity(
        value: BigDecimal,
        val unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()
) : BigDecimal(QuantityMapper.getInitialValue(value)) {
    constructor(value: String, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), unitOfMeasurement)

    constructor(value: Int, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), unitOfMeasurement)

    constructor(value: Long, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), unitOfMeasurement)

    constructor(value: Double, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), unitOfMeasurement)

    override fun add(augend: BigDecimal) = QuantityMapper.calculate(this, augend) { Quantity(super.add(it), this.unitOfMeasurement) }

    override fun subtract(subtrahend: BigDecimal) = QuantityMapper.calculate(this, subtrahend) { Quantity(super.subtract(it), this.unitOfMeasurement) }

    override fun multiply(multiplicand: BigDecimal) = QuantityMapper.calculate(this, multiplicand) { Quantity(super.multiply(it), this.unitOfMeasurement) }

    override fun divide(divisor: BigDecimal) = QuantityMapper.calculate(this, divisor) { Quantity(super.divide(it), this.unitOfMeasurement) }

    override fun divide(divisor: BigDecimal, roundingMode: Int) = QuantityMapper.calculate(this, divisor) { Quantity(super.divide(it, roundingMode), this.unitOfMeasurement) }

    override fun divide(divisor: BigDecimal, roundingMode: RoundingMode?) = QuantityMapper.calculate(this, divisor) { Quantity(super.divide(it, roundingMode), this.unitOfMeasurement) }

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: Int) = QuantityMapper.calculate(this, divisor) { Quantity(super.divide(it, QuantityMapper.checkScale(scale), roundingMode), this.unitOfMeasurement) }

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: RoundingMode?) = QuantityMapper.calculate(this, divisor) { Quantity(super.divide(it, QuantityMapper.checkScale(scale), roundingMode), this.unitOfMeasurement) }

    override fun setScale(newScale: Int, roundingMode: RoundingMode?) = Quantity(super.setScale(QuantityMapper.checkScale(newScale), roundingMode), this.unitOfMeasurement)

    override fun setScale(newScale: Int, roundingMode: Int) = Quantity(super.setScale(QuantityMapper.checkScale(newScale), roundingMode), this.unitOfMeasurement)

    override fun setScale(newScale: Int) = Quantity(super.setScale(QuantityMapper.checkScale(newScale)), this.unitOfMeasurement)

    override fun abs() = Quantity(super.abs(), this.unitOfMeasurement)

    override fun plus() = Quantity(super.plus(), this.unitOfMeasurement)

    override fun negate() = Quantity(super.negate(), this.unitOfMeasurement)

    override fun toByte() = byteValueExact()

    override fun toChar() = BigDecimal(this.toString()).toChar()

    override fun toShort() = shortValueExact()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Quantity) return false
        if (!super.equals(other)) return false

        if (unitOfMeasurement != other.unitOfMeasurement) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + unitOfMeasurement.hashCode()
        return result
    }

    fun toStringWithUnitName() = "${super.toString()} ${unitOfMeasurement.name}"

    class Filter<Q, S : FilterBuilder.SortOrder<S>, R> internal constructor(
            columnExactValue: String,
            columnUnitOfMeasurementName: String,
            columnUnitOfMeasurementType: String
    ) : FilterBuilder.Inner<Q, S, R>() {
        val exactValue = addFieldFilter<BigDecimal, Long>(columnExactValue, QuantityMapper.exactValueConverter)
        val unitOfMeasurement = addInnerFilterBuilder(UnitOfMeasurement.FullFilter<Q, S, R>(
                columnUnitOfMeasurementName,
                columnUnitOfMeasurementType
        ))

        class SortOrder<S : FilterBuilder.SortOrder<S>> internal constructor(
                columnExactValue: String,
                columnUnitOfMeasurementName: String,
                columnUnitOfMeasurementType: String
        ) : FilterBuilder.Inner.SortOrder<S>() {
            val exactValue = addFieldSorter(columnExactValue)
            val unitOfMeasurement = addInnerSortOrder(UnitOfMeasurement.FullFilter.SortOrder<S>(
                    columnUnitOfMeasurementName,
                    columnUnitOfMeasurementType
            ))
        }
    }

    companion object {
        const val MIN_SCALE = 0
        const val MAX_SCALE = 3
    }
}