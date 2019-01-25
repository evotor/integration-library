package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.UnitOfMeasurement
import ru.evotor.framework.core.safeGetString
import ru.evotor.framework.core.safeGetEnum
import ru.evotor.framework.core.safeGetInt
import ru.evotor.framework.provider.UnitOfMeasurementContract

internal object UnitOfMeasurementMapper {
    fun read(
            cursor: Cursor,
            columnVariationId: String,
            columnName: String,
            columnType: String
    ): UnitOfMeasurement? = cursor.safeGetEnum(columnType, UnitOfMeasurement.Type.values())?.let { type ->
        UnitOfMeasurementMapper.read(
                cursor,
                columnVariationId,
                columnName,
                type
        )
    }

    fun read(
            cursor: Cursor,
            columnVariationId: String,
            columnName: String,
            type: UnitOfMeasurement.Type
    ): UnitOfMeasurement? {
        return when (cursor.safeGetInt(columnVariationId)) {
            UnitOfMeasurementContract.VARIATION_ID_CUSTOM -> UnitOfMeasurement.Custom(
                    name = cursor.safeGetString(columnName) ?: return null,
                    type = type
            )
            UnitOfMeasurementContract.VARIATION_ID_CONVENTIONAL_UNIT -> UnitOfMeasurement.ConventionalUnit()
            UnitOfMeasurementContract.VARIATION_ID_PIECE -> UnitOfMeasurement.Piece()
            UnitOfMeasurementContract.VARIATION_ID_PACKAGING -> UnitOfMeasurement.Packaging()
            UnitOfMeasurementContract.VARIATION_ID_KIT -> UnitOfMeasurement.Kit()
            UnitOfMeasurementContract.VARIATION_ID_KILOGRAM -> UnitOfMeasurement.Kilogram()
            UnitOfMeasurementContract.VARIATION_ID_METER -> UnitOfMeasurement.Meter()
            UnitOfMeasurementContract.VARIATION_ID_SQUARE_METER -> UnitOfMeasurement.SquareMeter()
            UnitOfMeasurementContract.VARIATION_ID_CUBIC_METER -> UnitOfMeasurement.CubicMeter()
            UnitOfMeasurementContract.VARIATION_ID_LITER -> UnitOfMeasurement.Liter()
            else -> null
        }
    }
}