package ru.evotor.framework

import ru.evotor.framework.provider.QuantityContract.Columns
import ru.evotor.query.FilterBuilder
import java.math.BigDecimal
import java.math.RoundingMode

private const val INVALID_SCALE_MESSAGE = "Invalid scale"

open class Quantity(
        value: BigDecimal,
        scale: Int = 0,
        val unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()
) : BigDecimal(
        if (scale !in MIN_SCALE..MAX_SCALE)
            throw IllegalArgumentException(INVALID_SCALE_MESSAGE)
        else
            value.setScale(scale, ROUND_HALF_UP).toPlainString()
) {
    constructor(value: String, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), BigDecimal(value).scale(), unitOfMeasurement)

    constructor(value: Int, scale: Int = 0, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), scale, unitOfMeasurement)

    constructor(value: Long, scale: Int = 0, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), scale, unitOfMeasurement)

    constructor(value: Double, scale: Int = 0, unitOfMeasurement: UnitOfMeasurement = UnitOfMeasurement.Piece()) : this(BigDecimal(value), scale, unitOfMeasurement)

    fun toStringWithUnitName() = "${super.toString()} ${unitOfMeasurement.name}"

    override fun add(augend: BigDecimal) = Quantity(super.add(augend), this.scale(), this.unitOfMeasurement)

    override fun subtract(subtrahend: BigDecimal) = Quantity(super.subtract(subtrahend), this.scale(), this.unitOfMeasurement)

    override fun multiply(multiplicand: BigDecimal) = Quantity(super.multiply(multiplicand), this.scale(), this.unitOfMeasurement)

    override fun divide(divisor: BigDecimal) = Quantity(super.divide(divisor), this.scale(), this.unitOfMeasurement)

    override fun divide(divisor: BigDecimal, roundingMode: Int) = Quantity(super.divide(divisor, roundingMode), this.scale(), this.unitOfMeasurement)

    override fun divide(divisor: BigDecimal, roundingMode: RoundingMode?) = Quantity(super.divide(divisor, roundingMode), this.scale(), this.unitOfMeasurement)

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: Int) = if (scale in MIN_SCALE..MAX_SCALE) Quantity(super.divide(divisor, scale, roundingMode), scale, this.unitOfMeasurement) else throw IllegalArgumentException(INVALID_SCALE_MESSAGE)

    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: RoundingMode?) = if (scale in MIN_SCALE..MAX_SCALE) Quantity(super.divide(divisor, scale, roundingMode), scale, this.unitOfMeasurement) else throw IllegalArgumentException(INVALID_SCALE_MESSAGE)

    override fun setScale(newScale: Int, roundingMode: RoundingMode?) = if (newScale in MIN_SCALE..MAX_SCALE) Quantity(super.setScale(newScale, roundingMode), newScale, this.unitOfMeasurement) else throw IllegalArgumentException(INVALID_SCALE_MESSAGE)

    override fun setScale(newScale: Int, roundingMode: Int) = if (newScale in MIN_SCALE..MAX_SCALE) Quantity(super.setScale(newScale, roundingMode), newScale, this.unitOfMeasurement) else throw IllegalArgumentException(INVALID_SCALE_MESSAGE)

    override fun setScale(newScale: Int) = if (newScale in MIN_SCALE..MAX_SCALE) Quantity(super.setScale(newScale), newScale, this.unitOfMeasurement) else throw IllegalArgumentException(INVALID_SCALE_MESSAGE)

    override fun abs() = Quantity(super.abs(), this.scale(), this.unitOfMeasurement)

    override fun plus() = Quantity(super.plus(), this.scale(), this.unitOfMeasurement)

    override fun negate() = Quantity(super.negate(), this.scale(), this.unitOfMeasurement)

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

    class Filter<Q, S : FilterBuilder.SortOrder<S>, R> internal constructor() : FilterBuilder.Inner<Q, S, R>() {
        val unscaledValue = addFieldFilter<Long>(Columns.QUANTITY_UNSCALED_VALUE)
        val scale = addFieldFilter<Int>(Columns.QUANTITY_SCALE)
        val unitOfMeasurement = addInnerFilterBuilder(UnitOfMeasurement.Filter<Q, S, R>())

        class SortOrder<S : FilterBuilder.SortOrder<S>> : FilterBuilder.Inner.SortOrder<S>() {
            val unscaledValue = addFieldSorter(Columns.QUANTITY_UNSCALED_VALUE)
            val scale = addFieldSorter(Columns.QUANTITY_SCALE)
            val unitOfMeasurement = addInnerSortOrder(UnitOfMeasurement.Filter.SortOrder<S>())
        }
    }

    companion object {
        const val MIN_SCALE = 0
        const val MAX_SCALE = 3
    }
}