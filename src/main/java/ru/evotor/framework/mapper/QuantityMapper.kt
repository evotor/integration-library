package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.Quantity
import ru.evotor.framework.core.*
import java.math.BigDecimal

internal object QuantityMapper {
    val exactValueConverter = { exactValue: BigDecimal -> exactValue.multiply(BigDecimal(1000)).setScale(0).longValueExact() }

    fun read(
            cursor: Cursor,
            columnExactValue: String,
            columnScale: String,
            columnUnitOfMeasurementVariationId: String,
            columnUnitOfMeasurementName: String,
            columnUnitOfMeasurementType: String
    ): Quantity? {
        return Quantity(
                value = cursor.safeGetBigDecimal(columnExactValue)
                        ?.setScale(cursor.safeGetInt(columnScale) ?: 0)
                        ?.divide(BigDecimal(1000))
                        ?: return null,
                unitOfMeasurement = UnitOfMeasurementMapper.read(
                        cursor,
                        columnUnitOfMeasurementVariationId,
                        columnUnitOfMeasurementName,
                        columnUnitOfMeasurementType
                ) ?: return null
        )
    }

    fun calculate(source: Quantity, target: BigDecimal, calculationBlock: (Quantity) -> Quantity) =
            if (target is Quantity && target.unitOfMeasurement == source.unitOfMeasurement)
                calculationBlock.invoke(target)
            else
                throw UnsupportedOperationException("Расчёт не удался. Приведите оба значения к одной единице измерения.")

    fun checkScale(scale: Int) =
            if (scale in Quantity.MIN_SCALE..Quantity.MAX_SCALE)
                scale
            else
                throw IllegalArgumentException("Неправильный масштаб. Укажите масштаб от 0 до 3")
}