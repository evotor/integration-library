package ru.evotor.framework.inventory.product.extension.mapper

import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.core.safeGetLong
import ru.evotor.framework.core.safeGetBigDecimal
import ru.evotor.framework.core.safeGetPercent
import java.util.*

internal object AlcoholProductMapper {
    fun loadExtension(context: Context, productUuid: UUID): Cursor? =
            context.contentResolver
                    .query(
                            Uri.withAppendedPath(
                                    InventoryContract.URI_PRODUCT_EXTENSIONS,
                                    InventoryContract.PATH_ALCOHOL_PRODUCTS
                            ),
                            null,
                            "${InventoryContract.AlcoholProductColumns.PRODUCT_UUID}=?",
                            arrayOf(productUuid.toString()),
                            null
                    )

    fun readFsrarProductKindCode(cursor: Cursor) = cursor.safeGetLong(InventoryContract.AlcoholProductColumns.FSAR_PRODUCT_KIND_CODE)

    fun readTareVolume(cursor: Cursor) = cursor.safeGetBigDecimal(InventoryContract.AlcoholProductColumns.TARE_VOLUME)

    fun readAlcoholPercentage(cursor: Cursor) = cursor.safeGetPercent(InventoryContract.AlcoholProductColumns.ALCOHOL_PERCENTAGE)
}