package ru.evotor.framework.inventory.product.extension.mapper

import android.database.Cursor
import ru.evotor.framework.inventory.product.extension.provider.ExcisableProductContract
import ru.evotor.framework.optString

internal object ExcisableProductMapper {
    fun readMark(cursor: Cursor) = cursor.optString(ExcisableProductContract.COLUMN_MARK)
}