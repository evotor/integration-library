package ru.evotor.framework.inventory.product.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.UnitOfMeasurement
import ru.evotor.framework.inventory.product.provider.UnitOfMeasurementContract
import ru.evotor.framework.mapper.MultiVariationEntityMapper
import ru.evotor.framework.safeGetString
import ru.evotor.framework.safeGetEnum

internal object UnitOfMeasurementMapper {
    private const val VARIATION_ID_CUSTOM = 0
    private const val VARIATION_ID_CONVENTIONAL_UNIT = 1
    private const val VARIATION_ID_PIECE = 2
    private const val VARIATION_ID_PACKAGING = 3
    private const val VARIATION_ID_KIT = 4
    private const val VARIATION_ID_KILOGRAM = 5
    private const val VARIATION_ID_METER = 6
    private const val VARIATION_ID_SQUARE_METER = 7
    private const val VARIATION_ID_CUBIC_METER = 8
    private const val VARIATION_ID_LITER = 9

    fun read(cursor: Cursor) = when (MultiVariationEntityMapper.readVariationId(cursor)) {
        VARIATION_ID_CUSTOM -> UnitOfMeasurement.Custom(
                readType(cursor) ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::type.name),
                readName(cursor) ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::name.name),
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::precision.name)
        )
        VARIATION_ID_CONVENTIONAL_UNIT -> UnitOfMeasurement.ConventionalUnit()
        VARIATION_ID_PIECE -> UnitOfMeasurement.Piece()
        VARIATION_ID_PACKAGING -> UnitOfMeasurement.Packaging()
        VARIATION_ID_KIT -> UnitOfMeasurement.Kit()
        VARIATION_ID_KILOGRAM -> UnitOfMeasurement.Kilogram(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::precision.name)
        )
        VARIATION_ID_METER -> UnitOfMeasurement.Meter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::precision.name)
        )
        VARIATION_ID_SQUARE_METER -> UnitOfMeasurement.SquareMeter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::precision.name)
        )
        VARIATION_ID_CUBIC_METER -> UnitOfMeasurement.CubicMeter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::precision.name)
        )
        VARIATION_ID_LITER -> UnitOfMeasurement.Liter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::precision.name)
        )
        else -> throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java.name)
    }

    private fun readType(cursor: Cursor) = cursor.safeGetEnum(UnitOfMeasurementContract.COLUMN_TYPE, UnitOfMeasurement.Type.values())

    private fun readName(cursor: Cursor) = cursor.safeGetString(UnitOfMeasurementContract.COLUMN_NAME)

    private fun readPrecision(cursor: Cursor) = cursor.safeGetEnum(UnitOfMeasurementContract.COLUMN_PRECISION, UnitOfMeasurement.Precision.values())
}