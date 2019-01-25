package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.UnitOfMeasurement
import ru.evotor.framework.Volume
import ru.evotor.framework.core.safeGetBigDecimal
import ru.evotor.framework.core.safeGetInt
import java.math.BigDecimal

internal object VolumeMapper {
    fun read(
            cursor: Cursor,
            columnUnscaledValue: String,
            columnScale: String,
            columnUnitOfMeasurementVariationId: String,
            columnUnitOfMeasurementName: String
    ): Volume? {
        return Volume(
                value = cursor.safeGetBigDecimal(columnUnscaledValue)
                        ?.divide(BigDecimal(1000))
                        ?.setScale(cursor.safeGetInt(columnScale) ?: 0)
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