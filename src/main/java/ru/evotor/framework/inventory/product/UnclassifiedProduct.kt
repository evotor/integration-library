package ru.evotor.framework.inventory.product

import android.content.Context
import android.net.Uri
import ru.evotor.framework.Quantity
import ru.evotor.framework.inventory.product.mapper.UnclassifiedProductMapper
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.payment.AmountOfRubles
import ru.evotor.framework.receipt.position.VatRate
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.util.*

/**
 * Неклассифицированный товар
 */
data class UnclassifiedProduct internal constructor(
        override val uuid: UUID,
        override val groupUuid: UUID?,
        override val name: String,
        override val code: String?,
        override val vendorCode: String?,
        override val price: AmountOfRubles?,
        override val vatRate: VatRate,
        override val quantity: Quantity,
        override val description: String?
) : Product() {

    /**
     * Запрос на получение неклассифицированных товаров из базы данных смарт-терминала
     */
    class Query : FilterBuilder<Query, Query.SortOrder, UnclassifiedProduct>(
            InventoryContract.Product.UNCLASSIFIED_PRODUCTS_URI
    ) {
        val uuid = addFieldFilter<UUID>(InventoryContract.Product.UUID)
        val groupUuid = addFieldFilter<UUID?>(InventoryContract.Product.GROUP_UUID)
        val name = addFieldFilter<String>(InventoryContract.Product.NAME)
        val code = addFieldFilter<String?>(InventoryContract.Product.CODE)
        val vendorCode = addFieldFilter<String?>(InventoryContract.Product.VENDOR_CODE)
        val price = addFieldFilter<AmountOfRubles?>(InventoryContract.Product.SELLING_PRICE)
        val vatRate = addFieldFilter<VatRate>(InventoryContract.Product.VAT_RATE)
        val quantity = addInnerFilterBuilder(Quantity.Filter<Query, Query.SortOrder, UnclassifiedProduct>())
        val description = addFieldFilter<String?>(InventoryContract.Product.DESCRIPTION)

        class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
            val uuid = addFieldSorter(InventoryContract.Product.UUID)
            val groupUuid = addFieldSorter(InventoryContract.Product.GROUP_UUID)
            val name = addFieldSorter(InventoryContract.Product.NAME)
            val code = addFieldSorter(InventoryContract.Product.CODE)
            val vendorCode = addFieldSorter(InventoryContract.Product.VENDOR_CODE)
            val price = addFieldSorter(InventoryContract.Product.SELLING_PRICE)
            val vatRate = addFieldSorter(InventoryContract.Product.VAT_RATE)
            val quantity = addInnerSortOrder(Quantity.Filter.SortOrder<SortOrder>())
            val description = addFieldSorter(InventoryContract.Product.DESCRIPTION)
        }

        override fun getValue(context: Context, cursor: Cursor<UnclassifiedProduct>): UnclassifiedProduct =
                UnclassifiedProductMapper.read(cursor)
    }
}