package ru.evotor.framework.inventory.product.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.UnitOfMeasurement
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.core.safeGetString
import ru.evotor.framework.core.safeGetEnum
import ru.evotor.framework.core.safeGetInt

internal object UnitOfMeasurementMapper {
    fun read(cursor: Cursor) = when (cursor.safeGetInt(InventoryContract.UnitOfMeasurementColumns.VARIATION_ID)) {
        InventoryContract.UnitOfMeasurementColumns.VARIATION_ID_CUSTOM -> UnitOfMeasurement.Custom(
                readType(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::type),
                readName(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::name),
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        InventoryContract.UnitOfMeasurementColumns.VARIATION_ID_CONVENTIONAL_UNIT -> UnitOfMeasurement.ConventionalUnit()
        InventoryContract.UnitOfMeasurementColumns.VARIATION_ID_PIECE -> UnitOfMeasurement.Piece()
        InventoryContract.UnitOfMeasurementColumns.VARIATION_ID_PACKAGING -> UnitOfMeasurement.Packaging()
        InventoryContract.UnitOfMeasurementColumns.VARIATION_ID_KIT -> UnitOfMeasurement.Kit()
        InventoryContract.UnitOfMeasurementColumns.VARIATION_ID_KILOGRAM -> UnitOfMeasurement.Kilogram(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        InventoryContract.UnitOfMeasurementColumns.VARIATION_ID_METER -> UnitOfMeasurement.Meter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        InventoryContract.UnitOfMeasurementColumns.VARIATION_ID_SQUARE_METER -> UnitOfMeasurement.SquareMeter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        InventoryContract.UnitOfMeasurementColumns.VARIATION_ID_CUBIC_METER -> UnitOfMeasurement.CubicMeter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        InventoryContract.UnitOfMeasurementColumns.VARIATION_ID_LITER -> UnitOfMeasurement.Liter(
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        else -> UnitOfMeasurement.Custom(
                readType(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::type),
                readName(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::name),
                readPrecision(cursor)
                        ?: throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java, UnitOfMeasurement::precision)
        )
        //throw IntegrationLibraryMappingException(UnitOfMeasurement::class.java)
    }

    private fun readType(cursor: Cursor) = cursor.safeGetEnum(InventoryContract.UnitOfMeasurementColumns.TYPE, UnitOfMeasurement.Type.values())
            ?: UnitOfMeasurement.Type.QUANTITY_UNIT

    private fun readName(cursor: Cursor) = cursor.safeGetString(InventoryContract.UnitOfMeasurementColumns.NAME)

    private fun readPrecision(cursor: Cursor) = cursor.safeGetEnum(InventoryContract.UnitOfMeasurementColumns.PRECISION, UnitOfMeasurement.Precision.values())
}