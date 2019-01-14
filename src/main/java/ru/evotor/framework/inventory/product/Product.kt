package ru.evotor.framework.inventory.product

import android.content.Context
import ru.evotor.framework.Quantity
import ru.evotor.framework.inventory.product.mapper.ProductMapper
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.payment.AmountOfRubles
import ru.evotor.framework.receipt.position.VatRate
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.math.BigDecimal
import java.util.*

/**
 * Товар в базе данных смарт-терминала
 */
abstract class Product internal constructor() {
    abstract val uuid: UUID
    abstract val groupUuid: UUID?
    abstract val name: String
    abstract val code: String?
    abstract val vendorCode: String?
    abstract val price: AmountOfRubles?
    abstract val vatRate: VatRate
    abstract val quantity: Quantity
    abstract val description: String?

    /**
     * Запрос на получение товаров из базы данных смарт-терминала
     */
    class Query : FilterBuilder<Query, Query.SortOrder, Product>(InventoryContract.URI_PRODUCTS) {
        val uuid = addFieldFilter<UUID>(InventoryContract.ProductColumns.UUID)
        val groupUuid = addFieldFilter<UUID?>(InventoryContract.ProductColumns.GROUP_UUID)
        val name = addFieldFilter<String>(InventoryContract.ProductColumns.NAME)
        val code = addFieldFilter<String?>(InventoryContract.ProductColumns.CODE)
        val vendorCode = addFieldFilter<String?>(InventoryContract.ProductColumns.VENDOR_CODE)
        val price = addFieldFilter<AmountOfRubles?>(InventoryContract.ProductColumns.SELLING_PRICE)
        val vatRate = addFieldFilter<VatRate>(InventoryContract.ProductColumns.VAT_RATE)
        val quantity = addInnerFilterBuilder(Quantity.Filter<Query, SortOrder, Product>())
        val description = addFieldFilter<String?>(InventoryContract.ProductColumns.DESCRIPTION)

        class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
            val uuid = addFieldSorter(InventoryContract.ProductColumns.UUID)
            val groupUuid = addFieldSorter(InventoryContract.ProductColumns.GROUP_UUID)
            val name = addFieldSorter(InventoryContract.ProductColumns.NAME)
            val code = addFieldSorter(InventoryContract.ProductColumns.CODE)
            val vendorCode = addFieldSorter(InventoryContract.ProductColumns.VENDOR_CODE)
            val price = addFieldSorter(InventoryContract.ProductColumns.SELLING_PRICE)
            val vatRate = addFieldSorter(InventoryContract.ProductColumns.VAT_RATE)
            val quantity = addInnerSortOrder(Quantity.Filter.SortOrder<SortOrder>())
            val description = addFieldSorter(InventoryContract.ProductColumns.DESCRIPTION)
        }

        override fun getValue(context: Context, cursor: Cursor<Product>): Product =
                ProductMapper.read(context, cursor)
    }
}