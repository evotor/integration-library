package ru.evotor.framework.inventory.product

import android.content.Context
import android.net.Uri
import ru.evotor.framework.inventory.product.extension.PayableService
import ru.evotor.framework.inventory.product.mapper.UnclassifiedPayableServiceMapper
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.math.BigDecimal
import java.util.*

data class UnclassifiedPayableService internal constructor(
        override val uuid: UUID,
        override val groupUuid: UUID?,
        override val name: String,
        override val code: String?,
        override val vendorCode: String?,
        override val barcodes: List<String>?,
        override val purchasePrice: BigDecimal?,
        override val sellingPrice: BigDecimal?,
        override val vatRate: VatRate,
        override val quantity: BigDecimal,
        override val unitOfMeasurement: UnitOfMeasurement,
        override val description: String?,
        override val allowedToSell: Boolean
) : Product(), PayableService {
    class Query : FilterBuilder<Query, Query.SortOrder, UnclassifiedPayableService>(
            Uri.withAppendedPath(
                    InventoryContract.URI_PRODUCTS,
                    InventoryContract.PATH_UNCLASSIFIED_PAYABLE_SERVICES
            )
    ) {
        val uuid = addFieldFilter<UUID>(InventoryContract.ProductColumns.UUID)
        val groupUuid = addFieldFilter<UUID?>(InventoryContract.ProductColumns.GROUP_UUID)
        val name = addFieldFilter<String>(InventoryContract.ProductColumns.NAME)
        val code = addFieldFilter<String?>(InventoryContract.ProductColumns.CODE)
        val vendorCode = addFieldFilter<String?>(InventoryContract.ProductColumns.VENDOR_CODE)
        val purchasePrice = addFieldFilter<BigDecimal?>(InventoryContract.ProductColumns.PURCHASE_PRICE)
        val sellingPrice = addFieldFilter<BigDecimal?>(InventoryContract.ProductColumns.SELLING_PRICE)
        val vatRate = addFieldFilter<VatRate>(InventoryContract.ProductColumns.VAT_RATE)
        val quantity = addFieldFilter<BigDecimal>(InventoryContract.ProductColumns.QUANTITY)
        val unitOfMeasurement = addInnerFilterBuilder(UnitOfMeasurement.Filter<Query, Query.SortOrder, UnclassifiedPayableService>())
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

        override fun getValue(context: Context, cursor: Cursor<UnclassifiedPayableService>): UnclassifiedPayableService =
                UnclassifiedPayableServiceMapper.read(cursor)
    }
}