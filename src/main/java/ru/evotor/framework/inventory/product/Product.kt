package ru.evotor.framework.inventory.product

import android.content.Context
import ru.evotor.framework.inventory.product.mapper.ProductMapper
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.math.BigDecimal
import java.util.*

abstract class Product internal constructor() {
    abstract val uuid: UUID
    abstract val groupUuid: UUID?
    abstract val name: String
    abstract val code: String?
    abstract val vendorCode: String?
    abstract val barcodes: List<String>?
    abstract val purchasePrice: BigDecimal?
    abstract val sellingPrice: BigDecimal?
    abstract val vatRate: VatRate
    abstract val quantity: BigDecimal
    abstract val unitOfMeasurement: UnitOfMeasurement
    abstract val description: String?
    abstract val allowedToSell: Boolean

    class Query : FilterBuilder<Query, Query.SortOrder, Product>(InventoryContract.URI_PRODUCTS) {
        val uuid = addFieldFilter<UUID>(InventoryContract.ProductColumns.UUID)
        val groupUuid = addFieldFilter<UUID?>(InventoryContract.ProductColumns.GROUP_UUID)
        val name = addFieldFilter<String>(InventoryContract.ProductColumns.NAME)
        val code = addFieldFilter<String?>(InventoryContract.ProductColumns.CODE)
        val vendorCode = addFieldFilter<String?>(InventoryContract.ProductColumns.VENDOR_CODE)
        val purchasePrice = addFieldFilter<BigDecimal?>(InventoryContract.ProductColumns.PURCHASE_PRICE)
        val sellingPrice = addFieldFilter<BigDecimal?>(InventoryContract.ProductColumns.SELLING_PRICE)
        val vatRate = addFieldFilter<VatRate>(InventoryContract.ProductColumns.VAT_RATE)
        val quantity = addFieldFilter<BigDecimal>(InventoryContract.ProductColumns.QUANTITY)
        val unitOfMeasurement = addInnerFilterBuilder(UnitOfMeasurement.Filter<Query, Query.SortOrder, Product>())
        val description = addFieldFilter<String?>(InventoryContract.ProductColumns.DESCRIPTION)
        val allowedToSell = addFieldFilter<Boolean>(InventoryContract.ProductColumns.ALLOWED_TO_SELL)

        class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
            val uuid = addFieldSorter(InventoryContract.ProductColumns.UUID)
            val groupUuid = addFieldSorter(InventoryContract.ProductColumns.GROUP_UUID)
            val name = addFieldSorter(InventoryContract.ProductColumns.NAME)
            val code = addFieldSorter(InventoryContract.ProductColumns.CODE)
            val vendorCode = addFieldSorter(InventoryContract.ProductColumns.VENDOR_CODE)
            val purchasePrice = addFieldSorter(InventoryContract.ProductColumns.PURCHASE_PRICE)
            val sellingPrice = addFieldSorter(InventoryContract.ProductColumns.SELLING_PRICE)
            val vatRate = addFieldSorter(InventoryContract.ProductColumns.VAT_RATE)
            val quantity = addFieldSorter(InventoryContract.ProductColumns.QUANTITY)
            val unitOfMeasurement = addInnerSortOrder(UnitOfMeasurement.Filter.SortOrder<SortOrder>())
            val description = addFieldSorter(InventoryContract.ProductColumns.DESCRIPTION)
            val allowedToSell = addFieldSorter(InventoryContract.ProductColumns.ALLOWED_TO_SELL)
        }

        override fun getValue(context: Context, cursor: Cursor<Product>): Product =
                ProductMapper.read(context, cursor)
    }
}