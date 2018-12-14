package ru.evotor.framework.inventory.product.category.entertainment

import android.content.Context
import android.net.Uri
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.UnitOfMeasurement
import ru.evotor.framework.inventory.product.VatRate
import ru.evotor.framework.inventory.product.extension.AlcoholProduct
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
import ru.evotor.framework.inventory.product.extension.provider.AlcoholProductContract
import ru.evotor.framework.inventory.product.extension.provider.ExcisableProductContract
import ru.evotor.framework.inventory.product.category.entertainment.mapper.StrongAlcoholMapper
import ru.evotor.framework.inventory.product.provider.ProductContract
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.math.BigDecimal
import java.util.*

data class StrongAlcohol internal constructor(
        override val uuid: UUID,
        override val groupUuid: UUID?,
        override val name: String,
        override val code: String?,
        override val fsrarProductKindCode: Long,
        override val vendorCode: String?,
        override val barcodes: List<String>?,
        override val mark: String,
        override val purchasePrice: BigDecimal?,
        override val sellingPrice: BigDecimal?,
        override val vatRate: VatRate,
        override val quantity: BigDecimal,
        override val unitOfMeasurement: UnitOfMeasurement,
        override val tareVolume: BigDecimal,
        override val alcoholPercentage: BigDecimal,
        override val description: String?,
        override val allowedToSell: Boolean
) : Product(), AlcoholProduct, ExcisableProduct {
    class Query : FilterBuilder<Query, Query.SortOrder, StrongAlcohol>(
            Uri.withAppendedPath(InventoryContract.BASE_URI, ProductContract.PATH),
            ProductContract.getQueryInitialEditions(ProductContract.VARIATION_ID_PAYABLE_SERVICE)
    ) {
        val uuid = addFieldFilter<UUID>(ProductContract.COLUMN_UUID)
        val groupUuid = addFieldFilter<UUID?>(ProductContract.COLUMN_GROUP_UUID)
        val name = addFieldFilter<String>(ProductContract.COLUMN_NAME)
        val code = addFieldFilter<String?>(ProductContract.COLUMN_CODE)
        val fsrarProductKindCode = addFieldFilter<Long>(AlcoholProductContract.COLUMN_FSAR_PRODUCT_KIND_CODE)
        val vendorCode = addFieldFilter<String?>(ProductContract.COLUMN_VENDOR_CODE)
        val mark = addFieldFilter<String>(ExcisableProductContract.COLUMN_MARK)
        val purchasePrice = addFieldFilter<BigDecimal?>(ProductContract.COLUMN_PURCHASE_PRICE)
        val sellingPrice = addFieldFilter<BigDecimal?>(ProductContract.COLUMN_SELLING_PRICE)
        val vatRate = addFieldFilter<VatRate>(ProductContract.COLUMN_VAT_RATE)
        val quantity = addFieldFilter<BigDecimal>(ProductContract.COLUMN_QUANTITY)
        val unitOfMeasurement = addInnerFilterBuilder(UnitOfMeasurement.Filter<Query, SortOrder, StrongAlcohol>())
        val tareVolume = addFieldFilter<BigDecimal>(AlcoholProductContract.COLUMN_TARE_VOLUME)
        val alcoholPercentage = addFieldFilter<BigDecimal>(AlcoholProductContract.COLUMN_ALCOHOL_PERCENTAGE)
        val description = addFieldFilter<String?>(ProductContract.COLUMN_DESCRIPTION)
        val allowedToSell = addFieldFilter<Boolean>(ProductContract.COLUMN_ALLOWED_TO_SELL)

        class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
            val uuid = addFieldSorter(ProductContract.COLUMN_UUID)
            val groupUuid = addFieldSorter(ProductContract.COLUMN_GROUP_UUID)
            val name = addFieldSorter(ProductContract.COLUMN_NAME)
            val code = addFieldSorter(ProductContract.COLUMN_CODE)
            val fsrarProductKindCode = addFieldSorter(AlcoholProductContract.COLUMN_FSAR_PRODUCT_KIND_CODE)
            val vendorCode = addFieldSorter(ProductContract.COLUMN_VENDOR_CODE)
            val mark = addFieldSorter(ExcisableProductContract.COLUMN_MARK)
            val purchasePrice = addFieldSorter(ProductContract.COLUMN_PURCHASE_PRICE)
            val sellingPrice = addFieldSorter(ProductContract.COLUMN_SELLING_PRICE)
            val vatRate = addFieldSorter(ProductContract.COLUMN_VAT_RATE)
            val quantity = addFieldSorter(ProductContract.COLUMN_QUANTITY)
            val unitOfMeasurement = addInnerSortOrder(UnitOfMeasurement.Filter.SortOrder<SortOrder>())
            val tareVolume = addFieldSorter(AlcoholProductContract.COLUMN_TARE_VOLUME)
            val alcoholPercentage = addFieldSorter(AlcoholProductContract.COLUMN_ALCOHOL_PERCENTAGE)
            val description = addFieldSorter(ProductContract.COLUMN_DESCRIPTION)
            val allowedToSell = addFieldSorter(ProductContract.COLUMN_ALLOWED_TO_SELL)
        }

        override fun getValue(context: Context, cursor: Cursor<StrongAlcohol>): StrongAlcohol =
                StrongAlcoholMapper.read(cursor)
    }
}