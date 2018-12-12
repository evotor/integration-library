package ru.evotor.framework.inventory.product.mapper

import android.database.Cursor
import ru.evotor.framework.core.OutdatedLibraryException
import ru.evotor.framework.inventory.product.UnitOfMeasurement
import ru.evotor.framework.inventory.product.provider.UnitOfMeasurementContract
import ru.evotor.framework.optString
import ru.evotor.framework.safeGetEnum
import ru.evotor.framework.safeGetInt

internal object UnitOfMeasurementMapper {
    private const val CUSTOM_CLASS_ID = 0
    private const val KILOGRAM_CLASS_ID = 1
    private const val PIECE_CLASS_ID = 2

    fun read(cursor: Cursor) = when (cursor.safeGetInt(UnitOfMeasurementContract.COLUMN_CLASS_ID)) {
        CUSTOM_CLASS_ID -> UnitOfMeasurement.Custom(
                readType(cursor) ?: throw OutdatedLibraryException(UnitOfMeasurement::type.name),
                readName(cursor) ?: throw OutdatedLibraryException(UnitOfMeasurement::name.name),
                readPrecision(cursor)
                        ?: throw OutdatedLibraryException(UnitOfMeasurement::precision.name)
        )
        KILOGRAM_CLASS_ID -> UnitOfMeasurement.Kilogram(
                readPrecision(cursor)
                        ?: throw OutdatedLibraryException(UnitOfMeasurement::precision.name)
        )
        PIECE_CLASS_ID -> UnitOfMeasurement.Piece()
        else -> throw OutdatedLibraryException(UnitOfMeasurement::class.java.name)
    }

    private fun readType(cursor: Cursor) = cursor.safeGetEnum(UnitOfMeasurementContract.COLUMN_TYPE, UnitOfMeasurement.Type.values())

    private fun readName(cursor: Cursor) = cursor.optString(UnitOfMeasurementContract.COLUMN_NAME)

    private fun readPrecision(cursor: Cursor) = cursor.safeGetEnum(UnitOfMeasurementContract.COLUMN_PRECISION, UnitOfMeasurement.Precision.values())
}