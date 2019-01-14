package ru.evotor.framework.inventory.product.mapper

import android.content.Context
import android.database.Cursor
import ru.evotor.framework.Utils
import ru.evotor.framework.core.*
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.receipt.position.VatRate
import ru.evotor.framework.inventory.product.category.entertainment.mapper.StrongAlcoholMapper
import ru.evotor.framework.inventory.product.category.entertainment.mapper.TobaccoMapper
import ru.evotor.framework.inventory.product.category.entertainment.mapper.WeakAlcoholMapper
import ru.evotor.framework.inventory.provider.InventoryContract
import java.util.*

internal object ProductMapper {
    fun read(context: Context, cursor: Cursor) = when (cursor.safeGetString(InventoryContract.ProductColumns.VARIATION_ID)) {
        InventoryContract.ProductColumns.VARIATION_ID_UNCLASSIFIED_PRODUCT -> UnclassifiedProductMapper.read(cursor)
        InventoryContract.ProductColumns.VARIATION_ID_UNCLASSIFIED_SERVICE -> UnclassifiedServiceMapper.read(cursor)
        InventoryContract.ProductColumns.VARIATION_ID_WEAK_ALCOHOL -> WeakAlcoholMapper.read(context, cursor, true)
        InventoryContract.ProductColumns.VARIATION_ID_STRONG_ALCOHOL -> StrongAlcoholMapper.read(context, cursor, true)
        InventoryContract.ProductColumns.VARIATION_ID_TOBACCO -> TobaccoMapper.read(cursor)
        else -> throw IntegrationLibraryMappingException(Product::class.java)
    }

    fun readUuid(cursor: Cursor) = cursor.safeGetString(InventoryContract.ProductColumns.UUID)?.let { UUID.fromString(it) }

    fun readGroupUuid(cursor: Cursor) = cursor.safeGetString(InventoryContract.ProductColumns.GROUP_UUID)?.let { UUID.fromString(it) }

    fun readName(cursor: Cursor) = cursor.safeGetString(InventoryContract.ProductColumns.NAME)

    fun readCode(cursor: Cursor) = cursor.safeGetString(InventoryContract.ProductColumns.CODE)

    fun readVendorCode(cursor: Cursor) = cursor.safeGetString(InventoryContract.ProductColumns.VENDOR_CODE)

    fun readSellingPrice(cursor: Cursor) = cursor.safeGetMoney(InventoryContract.ProductColumns.SELLING_PRICE)

    fun readVatRate(cursor: Cursor): VatRate = Utils.safeValueOf(VatRate::class.java, cursor.safeGetString(InventoryContract.ProductColumns.VAT_RATE), VatRate.WITHOUT_VAT)

    fun readDescription(cursor: Cursor) = cursor.safeGetString(InventoryContract.ProductColumns.DESCRIPTION)
}