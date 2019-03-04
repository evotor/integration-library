package ru.evotor.framework

import ru.evotor.framework.mapper.QuantityMapper
import ru.evotor.framework.mapper.VolumeMapper
import ru.evotor.query.FilterBuilder
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.UnsupportedOperationException

class Volume(
        value: BigDecimal,
        volumeUnit: UnitOfMeasurement = UnitOfMeasurement.Liter()
) : Quantity(
        value,
        VolumeMapper.checkUnitOfMeasurement(volumeUnit)
) {
    constructor(value: String, volumeUnit: UnitOfMeasurement = UnitOfMeasurement.Liter()) : this(BigDecimal(value), volumeUnit)

    constructor(value: Int, volumeUnit: UnitOfMeasurement = UnitOfMeasurement.Liter()) : this(BigDecimal(value), volumeUnit)

    constructor(value: Long, volumeUnit: UnitOfMeasurement = UnitOfMeasurement.Liter()) : this(BigDecimal(value), volumeUnit)

    constructor(value: Double, volumeUnit: UnitOfMeasurement = UnitOfMeasurement.Liter()) : this(BigDecimal(value), volumeUnit)

    @Throws(UnsupportedOperationException::class)
    override fun add(augend: BigDecimal) = super.add(augend).let { Volume(it, it.unitOfMeasurement) }

    @Throws(UnsupportedOperationException::class)
    override fun subtract(subtrahend: BigDecimal) = super.subtract(subtrahend).let { Volume(it, it.unitOfMeasurement) }

    @Throws(UnsupportedOperationException::class)
    override fun multiply(multiplicand: BigDecimal) = super.multiply(multiplicand).let { Volume(it, it.unitOfMeasurement) }

    @Throws(UnsupportedOperationException::class)
    override fun divide(divisor: BigDecimal) = super.divide(divisor).let { Volume(it, it.unitOfMeasurement) }

    @Throws(UnsupportedOperationException::class)
    override fun divide(divisor: BigDecimal, roundingMode: Int) = super.divide(divisor, roundingMode).let { Volume(it, it.unitOfMeasurement) }

    @Throws(UnsupportedOperationException::class)
    override fun divide(divisor: BigDecimal, roundingMode: RoundingMode?) = super.divide(divisor, roundingMode).let { Volume(it, it.unitOfMeasurement) }

    @Throws(UnsupportedOperationException::class)
    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: Int) = super.divide(divisor, scale, roundingMode).let { Volume(it, it.unitOfMeasurement) }

    @Throws(UnsupportedOperationException::class)
    override fun divide(divisor: BigDecimal, scale: Int, roundingMode: RoundingMode?) = super.divide(divisor, scale, roundingMode).let { Volume(it, it.unitOfMeasurement) }

    override fun setScale(newScale: Int, roundingMode: RoundingMode?) = super.setScale(newScale, roundingMode).let { Volume(it, it.unitOfMeasurement) }

    override fun setScale(newScale: Int, roundingMode: Int) = super.setScale(newScale, roundingMode).let { Volume(it, it.unitOfMeasurement) }

    override fun setScale(newScale: Int) = super.setScale(newScale).let { Volume(it, it.unitOfMeasurement) }

    override fun abs() = super.abs().let { Volume(it, it.unitOfMeasurement) }

    override fun plus() = super.plus().let { Volume(it, it.unitOfMeasurement) }

    override fun negate() = super.negate().let { Volume(it, it.unitOfMeasurement) }

    @Throws(UnsupportedOperationException::class)
    fun toMilliliters(): Volume = VolumeMapper.toMilliliters(this)

    @Throws(UnsupportedOperationException::class)
    fun toLiters(): Volume = VolumeMapper.toLiters(this)

    @Throws(UnsupportedOperationException::class)
    fun toDecalitres(): Volume = VolumeMapper.toDecalitres(this)

    @Throws(UnsupportedOperationException::class)
    fun toCubicMeters(): Volume = VolumeMapper.toCubicMeters(this)

    class Filter<Q, S : FilterBuilder.SortOrder<S>, R> internal constructor(
            columnExactValue: String,
            columnUnitOfMeasurementName: String
    ) : FilterBuilder.Inner<Q, S, R>() {
        val exactValue = addFieldFilter<BigDecimal, Long>(columnExactValue, QuantityMapper.exactValueConverter)
        val unitOfMeasurement = addInnerFilterBuilder(UnitOfMeasurement.NameOnlyFilter<Q, S, R>(
                columnUnitOfMeasurementName
        ))

        class SortOrder<S : FilterBuilder.SortOrder<S>> internal constructor(
                columnExactValue: String,
                columnUnitOfMeasurementName: String
        ) : FilterBuilder.Inner.SortOrder<S>() {
            val exactValue = addFieldSorter(columnExactValue)
            val unitOfMeasurement = addInnerSortOrder(UnitOfMeasurement.NameOnlyFilter.SortOrder<S>(
                    columnUnitOfMeasurementName
            ))
        }
    }
}