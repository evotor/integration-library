package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.UnitOfMeasurement
import ru.evotor.framework.core.safeGetString
import ru.evotor.framework.core.safeGetEnum
import ru.evotor.framework.core.safeGetInt
import ru.evotor.framework.provider.QuantityContract

internal object UnitOfMeasurementMapper {
    fun read(cursor: Cursor): UnitOfMeasurement? {
        return when (cursor.safeGetInt(QuantityContract.Columns.UNIT_OF_MEASUREMENT_VARIATION_ID)) {
            QuantityContract.UNIT_OF_MEASUREMENT_VARIATION_ID_CUSTOM -> UnitOfMeasurement.Custom(
                    readName(cursor) ?: return null,
                    readType(cursor) ?: return null
            )
            QuantityContract.UNIT_OF_MEASUREMENT_VARIATION_ID_CONVENTIONAL_UNIT -> UnitOfMeasurement.ConventionalUnit()
            QuantityContract.UNIT_OF_MEASUREMENT_VARIATION_ID_PIECE -> UnitOfMeasurement.Piece()
            QuantityContract.UNIT_OF_MEASUREMENT_VARIATION_ID_PACKAGING -> UnitOfMeasurement.Packaging()
            QuantityContract.UNIT_OF_MEASUREMENT_VARIATION_ID_KIT -> UnitOfMeasurement.Kit()
            QuantityContract.UNIT_OF_MEASUREMENT_VARIATION_ID_KILOGRAM -> UnitOfMeasurement.Kilogram()
            QuantityContract.UNIT_OF_MEASUREMENT_VARIATION_ID_METER -> UnitOfMeasurement.Meter()
            QuantityContract.UNIT_OF_MEASUREMENT_VARIATION_ID_SQUARE_METER -> UnitOfMeasurement.SquareMeter()
            QuantityContract.UNIT_OF_MEASUREMENT_VARIATION_ID_CUBIC_METER -> UnitOfMeasurement.CubicMeter()
            QuantityContract.UNIT_OF_MEASUREMENT_VARIATION_ID_LITER -> UnitOfMeasurement.Liter()
            else -> null
        }
    }

    private fun readType(cursor: Cursor) = cursor.safeGetEnum(QuantityContract.Columns.UNIT_OF_MEASUREMENT_TYPE, UnitOfMeasurement.Type.values())

    private fun readName(cursor: Cursor) = cursor.safeGetString(QuantityContract.Columns.UNIT_OF_MEASUREMENT_NAME)
}