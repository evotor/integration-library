package ru.evotor.framework.mapper

import android.database.Cursor
import ru.evotor.framework.provider.MultiVariationEntityContract
import ru.evotor.framework.safeGetInt

internal object MultiVariationEntityMapper {
    fun readVariationId(cursor: Cursor) = cursor.safeGetInt(MultiVariationEntityContract.COLUMN_VARIATION_ID)
}