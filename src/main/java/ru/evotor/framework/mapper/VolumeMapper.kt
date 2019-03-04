package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.UnitOfMeasurement
import ru.evotor.framework.Volume
import ru.evotor.framework.core.safeGetBigDecimal
import ru.evotor.framework.core.safeGetInt
import java.lang.UnsupportedOperationException
import java.math.BigDecimal

internal object VolumeMapper {
    fun checkUnitOfMeasurement(unitOfMeasurement: UnitOfMeasurement) =
            if (unitOfMeasurement.type == UnitOfMeasurement.Type.VOLUME_UNIT)
                unitOfMeasurement
            else
                throw IllegalArgumentException("Неправильная единица измерения. Укажите единицу измерения типа \"VOLUME_UNIT\".")

    fun read(
            cursor: Cursor,
            columnExactValue: String,
            columnScale: String,
            columnUnitOfMeasurementVariationId: String,
            columnUnitOfMeasurementName: String
    ): Volume? {
        return Volume(
                value = cursor.safeGetBigDecimal(columnExactValue)
                        ?.setScale(cursor.safeGetInt(columnScale) ?: 0)
                        ?.divide(BigDecimal(1000))
                        ?: return null,
                volumeUnit = UnitOfMeasurementMapper.read(
                        cursor,
                        columnUnitOfMeasurementVariationId,
                        columnUnitOfMeasurementName,
                        UnitOfMeasurement.Type.VOLUME_UNIT
                ) ?: return null
        )
    }

    fun toMilliliters(source: Volume): Volume {
        val value = when (source.unitOfMeasurement.name) {
            UnitOfMeasurement.Milliliter.NAME -> source
            UnitOfMeasurement.Liter.NAME -> source * BigDecimal(1000)
            UnitOfMeasurement.Decalitre.NAME -> source * BigDecimal(10000)
            UnitOfMeasurement.CubicMeter.NAME -> source * BigDecimal(1000000)
            else -> throw UnsupportedOperationException("Не удалось привести \"${source.unitOfMeasurement.name}\" к миллилитру")
        }
        return Volume(value, UnitOfMeasurement.Milliliter())
    }

    fun toLiters(source: Volume): Volume {
        val value = when (source.unitOfMeasurement.name) {
            UnitOfMeasurement.Milliliter.NAME -> source.divide(BigDecimal(1000))
            UnitOfMeasurement.Liter.NAME -> source
            UnitOfMeasurement.Decalitre.NAME -> source * BigDecimal(10)
            UnitOfMeasurement.CubicMeter.NAME -> source * BigDecimal(1000)
            else -> throw UnsupportedOperationException("Не удалось привести \"${source.unitOfMeasurement.name}\" к литру")
        }
        return Volume(value, UnitOfMeasurement.Liter())
    }

    fun toDecalitres(source: Volume): Volume {
        val value = when (source.unitOfMeasurement.name) {
            UnitOfMeasurement.Milliliter.NAME -> source.divide(BigDecimal(10000))
            UnitOfMeasurement.Liter.NAME -> source.divide(BigDecimal(10))
            UnitOfMeasurement.Decalitre.NAME -> source
            UnitOfMeasurement.CubicMeter.NAME -> source * BigDecimal(100)
            else -> throw UnsupportedOperationException("Не удалось привести \"${source.unitOfMeasurement.name}\" к декалитру")
        }
        return Volume(value, UnitOfMeasurement.Decalitre())
    }

    fun toCubicMeters(source: Volume): Volume {
        val value = when (source.unitOfMeasurement.name) {
            UnitOfMeasurement.Milliliter.NAME -> source.divide(BigDecimal(1000000))
            UnitOfMeasurement.Liter.NAME -> source.divide(BigDecimal(1000))
            UnitOfMeasurement.Decalitre.NAME -> source.divide(BigDecimal(100))
            UnitOfMeasurement.CubicMeter.NAME -> source
            else -> throw UnsupportedOperationException("Не удалось привести \"${source.unitOfMeasurement.name}\" к кубическому метру")
        }
        return Volume(value, UnitOfMeasurement.CubicMeter())
    }
}