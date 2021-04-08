package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.optQuantity
import ru.evotor.framework.receipt.PositionTable
import ru.evotor.framework.receipt.position.PartialRealization

internal object PositionPartialMapper {

    internal fun fromCursor(cursor: Cursor): PartialRealization? {
        val quantityInPackage = cursor.optQuantity(PositionTable.COLUMN_PARTIAL_QUANTITY_IN_PACKAGE)
                ?: return null
        return PartialRealization(
                quantityInPackage = quantityInPackage
        )
    }
}