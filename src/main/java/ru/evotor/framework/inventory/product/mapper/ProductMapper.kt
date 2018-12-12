package ru.evotor.framework.inventory.product.mapper

import android.database.Cursor
import ru.evotor.framework.*
import ru.evotor.framework.core.OutdatedLibraryException
import ru.evotor.framework.inventory.product.VatRate
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.provider.ProductContract
import java.util.*

internal object ProductMapper {
    private const val UNCLASSIFIED_PRODUCT_CLASS_ID = 0
    private const val FREE_PRICE_PRODUCT_CLASS_ID = 1
    private const val WEAK_ALCOHOL_CLASS_ID = 2
    private const val STRONG_ALCOHOL_CLASS_ID = 3
    private const val TOBACCO_CLASS_ID = 4

    fun read(cursor: Cursor) = when (cursor.safeGetInt(ProductContract.COLUMN_CLASS_ID)) {
        UNCLASSIFIED_PRODUCT_CLASS_ID -> UnclassifiedProductMapper.read(cursor)
        FREE_PRICE_PRODUCT_CLASS_ID -> FreePriceProductMapper.read(cursor)
        WEAK_ALCOHOL_CLASS_ID -> WeakAlcoholMapper.read(cursor)
        STRONG_ALCOHOL_CLASS_ID -> StrongAlcoholMapper.read(cursor)
        TOBACCO_CLASS_ID -> TobaccoMapper.read(cursor)
        else -> throw OutdatedLibraryException(Product::class.java.name)
    }

    fun readUuid(cursor: Cursor) = cursor.optString(ProductContract.COLUMN_UUID)?.let { UUID.fromString(it) }

    fun readGroupUuid(cursor: Cursor) = cursor.optString(ProductContract.COLUMN_GROUP_UUID)?.let { UUID.fromString(it) }

    fun readName(cursor: Cursor) = cursor.optString(ProductContract.COLUMN_NAME)

    fun readCode(cursor: Cursor) = cursor.optString(ProductContract.COLUMN_CODE)

    fun readVendorCode(cursor: Cursor) = cursor.optString(ProductContract.COLUMN_VENDOR_CODE)

    fun readBarcodes(cursor: Cursor) = cursor.safeGetList(ProductContract.COLUMN_BARCODES)

    fun readPurchasePrice(cursor: Cursor) = cursor.safeGetMoney(ProductContract.COLUMN_PURCHASE_PRICE)

    fun readSellingPrice(cursor: Cursor) = cursor.safeGetMoney(ProductContract.COLUMN_SELLING_PRICE)

    fun readVatRate(cursor: Cursor) = cursor.safeGetEnum(ProductContract.COLUMN_VAT_RATE, VatRate.values())

    fun readQuantity(cursor: Cursor) = cursor.safeGetQuantity(ProductContract.COLUMN_QUANTITY)

    fun readDescription(cursor: Cursor) = cursor.optString(ProductContract.COLUMN_DESCRIPTION)

    fun readAllowedToSell(cursor: Cursor) = cursor.safeGetBoolean(ProductContract.COLUMN_ALLOWED_TO_SELL)
}