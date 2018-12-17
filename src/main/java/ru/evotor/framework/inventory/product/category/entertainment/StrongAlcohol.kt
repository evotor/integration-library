package ru.evotor.framework.inventory.product.category.entertainment

import android.content.Context
import android.net.Uri
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.UnitOfMeasurement
import ru.evotor.framework.inventory.product.VatRate
import ru.evotor.framework.inventory.product.category.entertainment.mapper.StrongAlcoholMapper
import ru.evotor.framework.inventory.product.extension.AlcoholProduct
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
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
        override val purchasePrice: BigDecimal?,
        override val sellingPrice: BigDecimal?,
        override val vatRate: VatRate,
        override val quantity: BigDecimal,
        override val unitOfMeasurement: UnitOfMeasurement,
        override val tareVolume: BigDecimal,
        override val alcoholPercentage: BigDecimal?,
        override val description: String?,
        override val allowedToSell: Boolean
) : Product(), AlcoholProduct, ExcisableProduct {
    class Query : FilterBuilder<Query, Query.SortOrder, StrongAlcohol>(
            Uri.withAppendedPath(
                    InventoryContract.URI_PRODUCTS,
                    InventoryContract.PATH_STRONG_ALCOHOL
            )
    ) {
        override val currentQuery: Query
            get() = this
        val uuid = addFieldFilter<UUID>(InventoryContract.ProductColumns.UUID)
        val groupUuid = addFieldFilter<UUID?>(InventoryContract.ProductColumns.GROUP_UUID)
        val name = addFieldFilter<String>(InventoryContract.ProductColumns.NAME)
        val code = addFieldFilter<String?>(InventoryContract.ProductColumns.CODE)
        val fsrarProductKindCode = addFieldFilter<Long>(InventoryContract.AlcoholProductColumns.FSAR_PRODUCT_KIND_CODE)
        val vendorCode = addFieldFilter<String?>(InventoryContract.ProductColumns.VENDOR_CODE)
        val purchasePrice = addFieldFilter<BigDecimal?>(InventoryContract.ProductColumns.PURCHASE_PRICE)
        val sellingPrice = addFieldFilter<BigDecimal?>(InventoryContract.ProductColumns.SELLING_PRICE)
        val vatRate = addFieldFilter<VatRate>(InventoryContract.ProductColumns.VAT_RATE)
        val quantity = addFieldFilter<BigDecimal>(InventoryContract.ProductColumns.QUANTITY)
        val unitOfMeasurement = addInnerFilterBuilder(UnitOfMeasurement.Filter<Query, SortOrder, StrongAlcohol>())
        val tareVolume = addFieldFilter<BigDecimal>(InventoryContract.AlcoholProductColumns.TARE_VOLUME)
        val alcoholPercentage = addFieldFilter<BigDecimal?>(InventoryContract.AlcoholProductColumns.ALCOHOL_PERCENTAGE)
        val description = addFieldFilter<String?>(InventoryContract.ProductColumns.DESCRIPTION)
        val allowedToSell = addFieldFilter<Boolean>(InventoryContract.ProductColumns.ALLOWED_TO_SELL)

        class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
            override val currentSortOrder: SortOrder
                get() = this
            val uuid = addFieldSorter(InventoryContract.ProductColumns.UUID)
            val groupUuid = addFieldSorter(InventoryContract.ProductColumns.GROUP_UUID)
            val name = addFieldSorter(InventoryContract.ProductColumns.NAME)
            val code = addFieldSorter(InventoryContract.ProductColumns.CODE)
            val fsrarProductKindCode = addFieldSorter(InventoryContract.AlcoholProductColumns.FSAR_PRODUCT_KIND_CODE)
            val vendorCode = addFieldSorter(InventoryContract.ProductColumns.VENDOR_CODE)
            val purchasePrice = addFieldSorter(InventoryContract.ProductColumns.PURCHASE_PRICE)
            val sellingPrice = addFieldSorter(InventoryContract.ProductColumns.SELLING_PRICE)
            val vatRate = addFieldSorter(InventoryContract.ProductColumns.VAT_RATE)
            val quantity = addFieldSorter(InventoryContract.ProductColumns.QUANTITY)
            val unitOfMeasurement = addInnerSortOrder(UnitOfMeasurement.Filter.SortOrder<SortOrder>())
            val tareVolume = addFieldSorter(InventoryContract.AlcoholProductColumns.TARE_VOLUME)
            val alcoholPercentage = addFieldSorter(InventoryContract.AlcoholProductColumns.ALCOHOL_PERCENTAGE)
            val description = addFieldSorter(InventoryContract.ProductColumns.DESCRIPTION)
            val allowedToSell = addFieldSorter(InventoryContract.ProductColumns.ALLOWED_TO_SELL)
        }

        override fun getValue(context: Context, cursor: Cursor<StrongAlcohol>): StrongAlcohol =
                StrongAlcoholMapper.read(context, cursor, false)
    }
}