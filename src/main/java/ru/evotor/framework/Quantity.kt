package ru.evotor.framework

import ru.evotor.query.FilterBuilder
import java.math.BigDecimal
import java.math.RoundingMode

open class Quantity(
        value: BigDecimal,
        val unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()
) : BigDecimal(
        value.scale().let { sourceScale ->
            val resultScale = if (sourceScale < MIN_SCALE) MIN_SCALE else if (sourceScale > MAX_SCALE) MAX_SCALE else sourceScale
            return@let if (resultScale != sourceScale) value.setScale(resultScale, ROUND_HALF_UP) else value
        }.let { newValue ->
            newValue.toPlainString()
        }
) {
    constructor(value: String, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), unitOfMeasurement)

    constructor(value: Int, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), unitOfMeasurement)

    constructor(value: Long, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), unitOfMeasurement)

    constructor(value: Double, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), unitOfMeasurement)

    override fun add(augend: BigDecimal) = calculate(augend) { Quantity(super.add(it), this.unitOfMeasurement) }

    override fun subtract(subtrahend: BigDecimal) = calculate(subtrahend) { Quantity(super.subtract(it), this.unitOfMeasurement) }

    override fun multiply(multiplicand: BigDecimal) = calculate(multiplicand) { Quantity(super.multiply(it), this.unitOfMeasurement) }

    override fun divide(divisor: BigDecimal) = calculate(divisor) { Quantity(super.divide(it), this.unitOfMeasurement) }

    override fun divide(divisor: BigDecimal, roundingMode: Int) = calculate(divisor) { Quantity(super.divide(it, roundingMode), this.unitOfMeasurement) }

    override fun divide(divisor: BigDecimal, roundingMode: RoundingMode?) = calculate(divisor) { Quantity(super.divide(it, roundingMode), this.unitOfMeasurement) }

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: Int) = calculate(divisor) { Quantity(super.divide(it, scale, roundingMode), this.unitOfMeasurement) }

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: RoundingMode?) = calculate(divisor) { Quantity(super.divide(it, scale, roundingMode), this.unitOfMeasurement) }

    override fun setScale(newScale: Int, roundingMode: RoundingMode?) = Quantity(super.setScale(newScale, roundingMode), this.unitOfMeasurement)

    override fun setScale(newScale: Int, roundingMode: Int) = Quantity(super.setScale(newScale, roundingMode), this.unitOfMeasurement)

    override fun setScale(newScale: Int) = Quantity(super.setScale(newScale), this.unitOfMeasurement)

    override fun abs() = Quantity(super.abs(), this.unitOfMeasurement)

    override fun plus() = Quantity(super.plus(), this.unitOfMeasurement)

    override fun negate() = Quantity(super.negate(), this.unitOfMeasurement)

    override fun toByte() = byteValueExact()

    override fun toChar() = BigDecimal(this.toString()).toChar()

    override fun toShort() = shortValueExact()

    private fun calculate(target: BigDecimal, calculationBlock: (Quantity) -> Quantity) =
            if (target is Quantity && target.unitOfMeasurement == this.unitOfMeasurement)
                calculationBlock.invoke(target)
            else
                throw UnsupportedOperationException("Расчёт не удался. Приведите оба значения к одной единице измерения.")


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
            columnUnscaledValue: String,
            columnScale: String,
            columnUnitOfMeasurementName: String,
            columnUnitOfMeasurementType: String
    ) : FilterBuilder.Inner<Q, S, R>() {
        val unscaledValue = addFieldFilter<Long>(columnUnscaledValue)
        val scale = addFieldFilter<Int>(columnScale)
        val unitOfMeasurement = addInnerFilterBuilder(UnitOfMeasurement.FullFilter<Q, S, R>(
                columnUnitOfMeasurementName,
                columnUnitOfMeasurementType
        ))

        class SortOrder<S : FilterBuilder.SortOrder<S>> internal constructor(
                columnUnscaledValue: String,
                columnScale: String,
                columnUnitOfMeasurementName: String,
                columnUnitOfMeasurementType: String
        ) : FilterBuilder.Inner.SortOrder<S>() {
            val unscaledValue = addFieldSorter(columnUnscaledValue)
            val scale = addFieldSorter(columnScale)
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