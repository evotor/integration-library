package ru.evotor.framework.inventory.product.mapper

import android.database.Cursor
import ru.evotor.framework.*
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.VatRate
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.provider.ProductContract
import ru.evotor.framework.mapper.MultiVariationEntityMapper
import java.util.*

internal object ProductMapper {
    private const val VARIATION_ID_UNCLASSIFIED_PRODUCT = 0
    private const val VARIATION_ID_WEAK_ALCOHOL = 1
    private const val VARIATION_ID_STRONG_ALCOHOL = 2
    private const val VARIATION_ID_TOBACCO = 3
    private const val VARIATION_ID_PAYABLE_SERVICE = 4

    fun read(cursor: Cursor) = when (MultiVariationEntityMapper.readVariationId(cursor)) {
        VARIATION_ID_UNCLASSIFIED_PRODUCT -> UnclassifiedProductMapper.read(cursor)
        VARIATION_ID_WEAK_ALCOHOL -> WeakAlcoholMapper.read(cursor)
        VARIATION_ID_STRONG_ALCOHOL -> StrongAlcoholMapper.read(cursor)
        VARIATION_ID_TOBACCO -> TobaccoMapper.read(cursor)
        VARIATION_ID_PAYABLE_SERVICE -> PayableServiceMapper.read(cursor)
        else -> throw IntegrationLibraryMappingException(Product::class.java.name)
    }

    fun readUuid(cursor: Cursor) = cursor.safeGetString(ProductContract.COLUMN_UUID)?.let { UUID.fromString(it) }

    fun readGroupUuid(cursor: Cursor) = cursor.safeGetString(ProductContract.COLUMN_GROUP_UUID)?.let { UUID.fromString(it) }

    fun readName(cursor: Cursor) = cursor.safeGetString(ProductContract.COLUMN_NAME)

    fun readCode(cursor: Cursor) = cursor.safeGetString(ProductContract.COLUMN_CODE)

    fun readVendorCode(cursor: Cursor) = cursor.safeGetString(ProductContract.COLUMN_VENDOR_CODE)

    fun readBarcodes(cursor: Cursor) = cursor.safeGetList(ProductContract.COLUMN_BARCODES)

    fun readPurchasePrice(cursor: Cursor) = cursor.safeGetMoney(ProductContract.COLUMN_PURCHASE_PRICE)

    fun readSellingPrice(cursor: Cursor) = cursor.safeGetMoney(ProductContract.COLUMN_SELLING_PRICE)

    fun readVatRate(cursor: Cursor) = cursor.safeGetEnum(ProductContract.COLUMN_VAT_RATE, VatRate.values())

    fun readQuantity(cursor: Cursor) = cursor.safeGetQuantity(ProductContract.COLUMN_QUANTITY)

    fun readDescription(cursor: Cursor) = cursor.safeGetString(ProductContract.COLUMN_DESCRIPTION)

    fun readAllowedToSell(cursor: Cursor) = cursor.safeGetBoolean(ProductContract.COLUMN_ALLOWED_TO_SELL)
}