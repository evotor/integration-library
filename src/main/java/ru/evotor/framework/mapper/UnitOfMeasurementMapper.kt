package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.UnitOfMeasurement
import ru.evotor.framework.core.safeGetString
import ru.evotor.framework.core.safeGetEnum
import ru.evotor.framework.core.safeGetInt
import ru.evotor.framework.provider.QuantityContract
import ru.evotor.framework.provider.QuantityContract.UnitOfMeasurementColumns

internal object UnitOfMeasurementMapper {
    fun read(cursor: Cursor) = when (cursor.safeGetInt(UnitOfMeasurementColumns.UNIT_OF_MEASUREMENT_VARIATION_ID)) {
        QuantityContract.UNIT_OF_MEASUREMENT_VARIATION_ID_CUSTOM -> UnitOfMeasurement.Custom(
                readName(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::name),
                readType(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::type)

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
        else -> UnitOfMeasurement.Custom(
                readName(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::name),
                readType(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::type)
        )
    }

    private fun readType(cursor: Cursor) = cursor.safeGetEnum(UnitOfMeasurementColumns.UNIT_OF_MEASUREMENT_TYPE, UnitOfMeasurement.Type.values())

    private fun readName(cursor: Cursor) = cursor.safeGetString(UnitOfMeasurementColumns.UNIT_OF_MEASUREMENT_NAME)
}