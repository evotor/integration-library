package ru.evotor.framework.inventory.product.extension.mapper

import android.content.Context
import android.database.Cursor
import ru.evotor.framework.core.safeGetAmountOfLiters
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.core.safeGetLong
import ru.evotor.framework.core.safeGetPercents
import java.util.*

internal object AlcoholProductMapper {
    fun loadExtension(context: Context, productUuid: UUID): Cursor? =
            context.contentResolver
                    .query(
                            InventoryContract.ProductExtension.ALCOHOL_PRODUCTS_URI,
                            null,
                            "${InventoryContract.ProductExtension.PRODUCT_UUID}=?",
                            arrayOf(productUuid.toString()),
                            null
                    )

    fun readFsrarProductKindCode(cursor: Cursor) = cursor.safeGetLong(InventoryContract.ProductExtension.FSRAR_PRODUCT_KIND_CODE)

    fun readTareVolume(cursor: Cursor) = cursor.safeGetAmountOfLiters(InventoryContract.ProductExtension.TARE_VOLUME)

    fun readAlcoholPercentage(cursor: Cursor) = cursor.safeGetPercents(InventoryContract.ProductExtension.ALCOHOL_PERCENTAGE)
}