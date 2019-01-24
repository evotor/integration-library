package ru.evotor.framework.inventory.product.category.entertainment

import android.content.Context
import ru.evotor.framework.inventory.product.AmountOfLiters
import ru.evotor.framework.Quantity
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.receipt.position.VatRate
import ru.evotor.framework.inventory.product.category.entertainment.mapper.WeakAlcoholMapper
import ru.evotor.framework.inventory.product.extension.AlcoholProduct
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.payment.AmountOfRubles
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.math.BigDecimal
import java.util.*

/**
 * Товар категории "Слабый алкоголь"
 */
data class WeakAlcohol internal constructor(
        override val uuid: UUID,
        override val groupUuid: UUID?,
        override val name: String,
        override val code: String?,
        override val fsrarProductKindCode: Long,
        override val vendorCode: String?,
        override val price: AmountOfRubles?,
        override val vatRate: VatRate,
        override val quantity: Quantity,
        override val tareVolume: AmountOfLiters,
        override val alcoholPercentage: Float?,
        override val description: String?
) : Product(), AlcoholProduct {

    /**
     * Запрос на получение товаров категории "Слабый алкоголь" из базы данных смарт-терминала
     */
    class Query : FilterBuilder<Query, Query.SortOrder, WeakAlcohol>(
            InventoryContract.Product.WEAK_ALCOHOL_URI
    ) {
        val uuid = addFieldFilter<UUID>(InventoryContract.Product.UUID)
        val groupUuid = addFieldFilter<UUID?>(InventoryContract.Product.GROUP_UUID)
        val name = addFieldFilter<String>(InventoryContract.Product.NAME)
        val code = addFieldFilter<String?>(InventoryContract.Product.CODE)
        val fsrarProductKindCode = addFieldFilter<Long>(InventoryContract.Product.FSRAR_PRODUCT_KIND_CODE)
        val vendorCode = addFieldFilter<String?>(InventoryContract.Product.VENDOR_CODE)
        val price = addFieldFilter<AmountOfRubles?>(InventoryContract.Product.PRICE)
        val vatRate = addFieldFilter<VatRate>(InventoryContract.Product.VAT_RATE)
        val quantity = addInnerFilterBuilder(Quantity.Filter<Query, SortOrder, WeakAlcohol>())
        val tareVolume = addFieldFilter<AmountOfLiters>(InventoryContract.Product.TARE_VOLUME)
        val alcoholPercentage = addFieldFilter<Float?>(InventoryContract.Product.ALCOHOL_PERCENTAGE)
        val description = addFieldFilter<String?>(InventoryContract.Product.DESCRIPTION)

        class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
            val uuid = addFieldSorter(InventoryContract.Product.UUID)
            val groupUuid = addFieldSorter(InventoryContract.Product.GROUP_UUID)
            val name = addFieldSorter(InventoryContract.Product.NAME)
            val code = addFieldSorter(InventoryContract.Product.CODE)
            val fsrarProductKindCode = addFieldSorter(InventoryContract.Product.FSRAR_PRODUCT_KIND_CODE)
            val vendorCode = addFieldSorter(InventoryContract.Product.VENDOR_CODE)
            val price = addFieldSorter(InventoryContract.Product.PRICE)
            val vatRate = addFieldSorter(InventoryContract.Product.VAT_RATE)
            val quantity = addInnerSortOrder(Quantity.Filter.SortOrder<SortOrder>())
            val tareVolume = addFieldSorter(InventoryContract.Product.TARE_VOLUME)
            val alcoholPercentage = addFieldSorter(InventoryContract.Product.ALCOHOL_PERCENTAGE)
            val description = addFieldSorter(InventoryContract.Product.DESCRIPTION)
        }

        override fun getValue(context: Context, cursor: Cursor<WeakAlcohol>): WeakAlcohol =
                WeakAlcoholMapper.read(context, cursor, false)
    }
}