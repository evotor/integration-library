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
import java.math.BigDecimal
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
            Uri.withAppendedPath(
                    InventoryContract.URI_PRODUCTS,
                    InventoryContract.PATH_UNCLASSIFIED_PRODUCTS
            )
    ) {
        val uuid = addFieldFilter<UUID>(InventoryContract.ProductColumns.UUID)
        val groupUuid = addFieldFilter<UUID?>(InventoryContract.ProductColumns.GROUP_UUID)
        val name = addFieldFilter<String>(InventoryContract.ProductColumns.NAME)
        val code = addFieldFilter<String?>(InventoryContract.ProductColumns.CODE)
        val vendorCode = addFieldFilter<String?>(InventoryContract.ProductColumns.VENDOR_CODE)
        val price = addFieldFilter<AmountOfRubles?>(InventoryContract.ProductColumns.SELLING_PRICE)
        val vatRate = addFieldFilter<VatRate>(InventoryContract.ProductColumns.VAT_RATE)
        val quantity = addInnerFilterBuilder(Quantity.Filter<Query, Query.SortOrder, UnclassifiedProduct>())
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

        override fun getValue(context: Context, cursor: Cursor<UnclassifiedProduct>): UnclassifiedProduct =
                UnclassifiedProductMapper.read(cursor)
    }
}