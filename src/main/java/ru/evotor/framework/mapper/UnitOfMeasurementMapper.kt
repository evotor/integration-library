package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.UnitOfMeasurement
import ru.evotor.framework.core.safeGetString
import ru.evotor.framework.core.safeGetEnum
import ru.evotor.framework.core.safeGetInt
import ru.evotor.framework.provider.UnitOfMeasurementColumns

internal object UnitOfMeasurementMapper {
    fun read(cursor: Cursor) = when (cursor.safeGetInt(UnitOfMeasurementColumns.VARIATION_ID)) {
        UnitOfMeasurementColumns.VARIATION_ID_CUSTOM -> UnitOfMeasurement.Custom(
                readName(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::name),
                readType(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::type)

        )
        UnitOfMeasurementColumns.VARIATION_ID_CONVENTIONAL_UNIT -> UnitOfMeasurement.ConventionalUnit()
        UnitOfMeasurementColumns.VARIATION_ID_PIECE -> UnitOfMeasurement.Piece()
        UnitOfMeasurementColumns.VARIATION_ID_PACKAGING -> UnitOfMeasurement.Packaging()
        UnitOfMeasurementColumns.VARIATION_ID_KIT -> UnitOfMeasurement.Kit()
        UnitOfMeasurementColumns.VARIATION_ID_KILOGRAM -> UnitOfMeasurement.Kilogram()
        UnitOfMeasurementColumns.VARIATION_ID_METER -> UnitOfMeasurement.Meter()
        UnitOfMeasurementColumns.VARIATION_ID_SQUARE_METER -> UnitOfMeasurement.SquareMeter()
        UnitOfMeasurementColumns.VARIATION_ID_CUBIC_METER -> UnitOfMeasurement.CubicMeter()
        UnitOfMeasurementColumns.VARIATION_ID_LITER -> UnitOfMeasurement.Liter()
        else -> UnitOfMeasurement.Custom(
                readName(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::name),
                readType(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::type)
        )
    }

    private fun readType(cursor: Cursor) = cursor.safeGetEnum(UnitOfMeasurementColumns.TYPE, UnitOfMeasurement.Type.values())

    private fun readName(cursor: Cursor) = cursor.safeGetString(UnitOfMeasurementColumns.NAME)
}