package ru.evotor.framework.inventory.product.extension.mapper

import android.database.Cursor
import ru.evotor.framework.inventory.product.extension.provider.AlcoholProductContract
import ru.evotor.framework.optLong
import ru.evotor.framework.safeGetBigDecimal

internal object AlcoholProductMapper {
    fun readFsrarProductKindCode(cursor: Cursor) = cursor.optLong(AlcoholProductContract.COLUMN_FSAR_PRODUCT_KIND_CODE)

    fun readTareVolume(cursor: Cursor) = cursor.safeGetBigDecimal(AlcoholProductContract.COLUMN_TARE_VOLUME)

    fun readAlcoholPercentage(cursor: Cursor) = cursor.safeGetBigDecimal(AlcoholProductContract.COLUMN_ALCOHOL_PERCENTAGE)
}