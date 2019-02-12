package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.UnitOfMeasurement
import ru.evotor.framework.Volume
import ru.evotor.framework.core.safeGetBigDecimal
import ru.evotor.framework.core.safeGetInt
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
}