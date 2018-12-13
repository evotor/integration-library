package ru.evotor.framework.inventory.product.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.UnitOfMeasurement
import ru.evotor.framework.inventory.product.provider.UnitOfMeasurementContract
import ru.evotor.framework.safeGetString
import ru.evotor.framework.safeGetEnum
import ru.evotor.framework.safeGetInt

internal object UnitOfMeasurementMapper {
    fun read(cursor: Cursor) = when (cursor.safeGetInt(UnitOfMeasurementContract.COLUMN_VARIATION_ID)) {
        UnitOfMeasurementContract.VARIATION_ID_CUSTOM -> UnitOfMeasurement.Custom(
                readType(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::type),
                readName(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::name),
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        UnitOfMeasurementContract.VARIATION_ID_CONVENTIONAL_UNIT -> UnitOfMeasurement.ConventionalUnit()
        UnitOfMeasurementContract.VARIATION_ID_PIECE -> UnitOfMeasurement.Piece()
        UnitOfMeasurementContract.VARIATION_ID_PACKAGING -> UnitOfMeasurement.Packaging()
        UnitOfMeasurementContract.VARIATION_ID_KIT -> UnitOfMeasurement.Kit()
        UnitOfMeasurementContract.VARIATION_ID_KILOGRAM -> UnitOfMeasurement.Kilogram(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        UnitOfMeasurementContract.VARIATION_ID_METER -> UnitOfMeasurement.Meter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        UnitOfMeasurementContract.VARIATION_ID_SQUARE_METER -> UnitOfMeasurement.SquareMeter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        UnitOfMeasurementContract.VARIATION_ID_CUBIC_METER -> UnitOfMeasurement.CubicMeter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        UnitOfMeasurementContract.VARIATION_ID_LITER -> UnitOfMeasurement.Liter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        else -> throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java)
    }

    private fun readType(cursor: Cursor) = cursor.safeGetEnum(UnitOfMeasurementContract.COLUMN_TYPE, UnitOfMeasurement.Type.values())

    private fun readName(cursor: Cursor) = cursor.safeGetString(UnitOfMeasurementContract.COLUMN_NAME)

    private fun readPrecision(cursor: Cursor) = cursor.safeGetEnum(UnitOfMeasurementContract.COLUMN_PRECISION, UnitOfMeasurement.Precision.values())
}