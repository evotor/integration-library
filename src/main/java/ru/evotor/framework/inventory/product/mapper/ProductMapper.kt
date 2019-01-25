package ru.evotor.framework.inventory.product.mapper

import android.content.Context
import android.database.Cursor
import ru.evotor.framework.Quantity
import ru.evotor.framework.core.*
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.receipt.position.VatRate
import ru.evotor.framework.inventory.product.category.entertainment.mapper.StrongAlcoholMapper
import ru.evotor.framework.inventory.product.category.entertainment.mapper.TobaccoMapper
import ru.evotor.framework.inventory.product.category.entertainment.mapper.WeakAlcoholMapper
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.mapper.QuantityMapper
import ru.evotor.framework.payment.mapper.AmountOfRublesMapper
import ru.evotor.framework.receipt.position.mapper.VatRateMapper
import java.util.*

internal object ProductMapper {
    fun read(context: Context, cursor: Cursor) = when (cursor.safeGetString(InventoryContract.Product.VARIATION_ID)) {
        InventoryContract.Product.VARIATION_ID_UNCLASSIFIED_PRODUCT -> UnclassifiedProductMapper.read(cursor)
        InventoryContract.Product.VARIATION_ID_UNCLASSIFIED_SERVICE -> UnclassifiedServiceMapper.read(cursor)
        InventoryContract.Product.VARIATION_ID_WEAK_ALCOHOL -> WeakAlcoholMapper.read(context, cursor, true)
        InventoryContract.Product.VARIATION_ID_STRONG_ALCOHOL -> StrongAlcoholMapper.read(context, cursor, true)
        InventoryContract.Product.VARIATION_ID_TOBACCO -> TobaccoMapper.read(cursor)
        else -> throw IntegrationLibraryMappingException(Product::class.java)
    }

    fun readUuid(cursor: Cursor) = cursor.safeGetString(InventoryContract.Product.UUID)?.let { UUID.fromString(it) }

    fun readGroupUuid(cursor: Cursor) = cursor.safeGetString(InventoryContract.Product.GROUP_UUID)?.let { UUID.fromString(it) }

    fun readName(cursor: Cursor) = cursor.safeGetString(InventoryContract.Product.NAME)

    fun readCode(cursor: Cursor) = cursor.safeGetString(InventoryContract.Product.CODE)

    fun readVendorCode(cursor: Cursor) = cursor.safeGetString(InventoryContract.Product.VENDOR_CODE)

    fun readPrice(cursor: Cursor) = AmountOfRublesMapper.read(cursor, InventoryContract.Product.PRICE)

    fun readVatRate(cursor: Cursor): VatRate? = VatRateMapper.read(cursor)

    fun readQuantity(cursor: Cursor): Quantity? = QuantityMapper.read(
            cursor,
            InventoryContract.Product.QUANTITY_UNSCALED_VALUE,
            InventoryContract.Product.QUANTITY_SCALE,
            InventoryContract.Product.UNIT_OF_MEASUREMENT_VARIATION_ID,
            InventoryContract.Product.UNIT_OF_MEASUREMENT_NAME,
            InventoryContract.Product.UNIT_OF_MEASUREMENT_TYPE
    )

    fun readDescription(cursor: Cursor) = cursor.safeGetString(InventoryContract.Product.DESCRIPTION)
}