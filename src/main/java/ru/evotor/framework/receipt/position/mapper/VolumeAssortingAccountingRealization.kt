package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.optQuantity
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.PositionTable
import ru.evotor.framework.receipt.position.VolumeSortAccountingRealization

internal object VolumeAssortingAccountingRealization {

    internal fun fromCursor(cursor: Cursor): VolumeSortAccountingRealization? {
        val volumeSortQuantity = cursor.optQuantity(PositionTable.COLUMN_VOLUME_ASSORTING_QUANTITY)
                ?: return null
        val gtin = cursor.optString(PositionTable.COLUMN_VOLUME_ASSORTING_GTIN)
            ?: return null
        return VolumeSortAccountingRealization(
            volumeSortQuantity = volumeSortQuantity,
            gtin = gtin
        )
    }
}
