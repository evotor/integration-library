package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.Quantity
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.core.safeGetInt
import ru.evotor.framework.core.safeGetLong
import ru.evotor.framework.provider.QuantityContract.Columns

internal object QuantityMapper {
    fun read(cursor: Cursor) = Quantity(
            value = cursor.safeGetLong(Columns.QUANTITY_UNSCALED_VALUE) ?: throw IntegrationLibraryMappingException(Quantity::class.java),
            scale = cursor.safeGetInt(Columns.QUANTITY_SCALE) ?: 0,
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor)
    )
}