package ru.evotor.framework.inventory.product.extension.mapper

import android.content.Context
import android.database.Cursor
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.core.safeGetLong
import ru.evotor.framework.core.safeGetPercents
import ru.evotor.framework.mapper.VolumeMapper
import java.util.*
import kotlin.math.roundToLong

internal object AlcoholProductMapper {
    val alcoholPercentageConverter = { it: Float? -> it?.times(1000)?.roundToLong() ?: 0L }

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

    fun readTareVolume(cursor: Cursor) = VolumeMapper.read(
            cursor,
            InventoryContract.Product.TARE_VOLUME_EXACT_VALUE,
            InventoryContract.Product.TARE_VOLUME_SCALE,
            InventoryContract.Product.TARE_VOLUME_UNIT_OF_MEASUREMENT_VARIATION_ID,
            InventoryContract.Product.TARE_VOLUME_UNIT_OF_MEASUREMENT_NAME
    )

    fun readAlcoholPercentage(cursor: Cursor) = cursor.safeGetPercents(InventoryContract.ProductExtension.ALCOHOL_PERCENTAGE)
}