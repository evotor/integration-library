package ru.evotor.framework.inventory.product.extension.mapper

import android.database.Cursor
import ru.evotor.framework.inventory.product.extension.provider.ExcisableProductContract
import ru.evotor.framework.safeGetString

internal object ExcisableProductMapper {
    fun readMark(cursor: Cursor) = cursor.safeGetString(ExcisableProductContract.COLUMN_MARK)
}