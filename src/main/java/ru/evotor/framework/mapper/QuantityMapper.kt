package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.Quantity
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.core.safeGetInt
import ru.evotor.framework.core.safeGetLong
import ru.evotor.framework.provider.QuantutyColumns

internal object QuantityMapper {
    fun read(cursor: Cursor) = Quantity(
            value = cursor.safeGetLong(QuantutyColumns.UNSCALED_VALUE) ?: throw IntegrationLibraryMappingException(Quantity::class.java),
            scale = cursor.safeGetInt(QuantutyColumns.SCALE) ?: 0,
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor)
    )
}