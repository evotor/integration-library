package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.formatQuantity
import ru.evotor.framework.receipt.PositionTable
import ru.evotor.framework.receipt.position.Partial
import ru.evotor.framework.safeGetLong

internal object PositionPartialMapper {

    internal fun fromCursor(cursor: Cursor): Partial? {
        val initialQuantity = cursor.safeGetLong(PositionTable.COLUMN_PARTIAL_INITIAL_QUANTITY)
        val quantityInPackage = cursor.safeGetLong(PositionTable.COLUMN_PARTIAL_QUANTITY_IN_PACKAGE)
        if (initialQuantity == null || quantityInPackage == null) {
            return null
        }
        return Partial(
                initialQuantity = initialQuantity.formatQuantity(),
                quantityInPackage = quantityInPackage.formatQuantity()
        )
    }
}