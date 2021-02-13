package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.inventory.ProductTable
import ru.evotor.framework.optInt
import ru.evotor.framework.receipt.Measure
import ru.evotor.framework.receipt.PositionTable

internal object MeasureMapper {

    internal fun readFromPositionCursor(cursor: Cursor): Measure {
        return cursor.let {
            Measure(
                    it.getString(cursor.getColumnIndex(PositionTable.COLUMN_MEASURE_NAME)),
                    it.getInt(cursor.getColumnIndex(PositionTable.COLUMN_MEASURE_PRECISION)),
                    it.optInt(cursor.getColumnIndex(PositionTable.COLUMN_MEASURE_CODE)) ?: Measure.UNKNOWN_MEASURE_CODE
            )
        }
    }

    internal fun readFromProductCursor(cursor: Cursor): Measure {
        return cursor.let {
            Measure(
                    it.getString(cursor.getColumnIndex(ProductTable.ROW_MEASURE_NAME)),
                    it.getInt(cursor.getColumnIndex(ProductTable.ROW_MEASURE_PRECISION)),
                    it.optInt(cursor.getColumnIndex(ProductTable.ROW_MEASURE_CODE)) ?: Measure.UNKNOWN_MEASURE_CODE
            )
        }
    }
}