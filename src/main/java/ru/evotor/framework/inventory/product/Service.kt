package ru.evotor.framework.inventory.product

import android.content.Context
import ru.evotor.framework.Quantity
import ru.evotor.framework.inventory.product.mapper.ServiceMapper
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.payment.AmountOfRubles
import ru.evotor.framework.payment.mapper.AmountOfRublesMapper
import ru.evotor.framework.receipt.TaxNumber
import ru.evotor.framework.receipt.position.VatRate
import ru.evotor.framework.receipt.position.mapper.VatRateMapper
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.util.*

/**
 * Услуга
 */
data class Service internal constructor(
        override val uuid: UUID,
        override val groupUuid: UUID?,
        override val name: String,
        override val code: String?,
        override val vendorCode: String?,
        override val price: AmountOfRubles?,
        override val vatRate: VatRate,
        override val quantity: Quantity,
        override val description: String?
) : Product(){

    /**
     * Запрос на получение услуг из базы данных смарт-терминала
     */
    class Query : FilterBuilder<Query, Query.SortOrder, Service>(
            InventoryContract.Product.SERVICES_URI
    ) {
        val uuid = addFieldFilter<UUID>(InventoryContract.Product.UUID)
        val groupUuid = addFieldFilter<UUID?>(InventoryContract.Product.GROUP_UUID)
        val name = addFieldFilter<String>(InventoryContract.Product.NAME)
        val code = addFieldFilter<String?>(InventoryContract.Product.CODE)
        val vendorCode = addFieldFilter<String?>(InventoryContract.Product.VENDOR_CODE)
        val price = addFieldFilter<AmountOfRubles?, Long>(InventoryContract.Product.PRICE, AmountOfRublesMapper.converter)
        val vatRate = addFieldFilter<VatRate, TaxNumber>(InventoryContract.Product.VAT_RATE, VatRateMapper.converter)
        val quantity = addInnerFilterBuilder(Quantity.Filter<Query, Query.SortOrder, Service>(
                InventoryContract.Product.QUANTITY_EXACT_VALUE,
                InventoryContract.Product.UNIT_OF_MEASUREMENT_NAME,
                InventoryContract.Product.UNIT_OF_MEASUREMENT_TYPE
        ))
        val description = addFieldFilter<String?>(InventoryContract.Product.DESCRIPTION)

        class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
            val uuid = addFieldSorter(InventoryContract.Product.UUID)
            val groupUuid = addFieldSorter(InventoryContract.Product.GROUP_UUID)
            val name = addFieldSorter(InventoryContract.Product.NAME)
            val code = addFieldSorter(InventoryContract.Product.CODE)
            val vendorCode = addFieldSorter(InventoryContract.Product.VENDOR_CODE)
            val price = addFieldSorter(InventoryContract.Product.PRICE)
            val vatRate = addFieldSorter(InventoryContract.Product.VAT_RATE)
            val quantity = addInnerSortOrder(Quantity.Filter.SortOrder<SortOrder>(
                    InventoryContract.Product.QUANTITY_EXACT_VALUE,
                    InventoryContract.Product.UNIT_OF_MEASUREMENT_NAME,
                    InventoryContract.Product.UNIT_OF_MEASUREMENT_TYPE
            ))
            val description = addFieldSorter(InventoryContract.Product.DESCRIPTION)
        }

        override fun getValue(context: Context, cursor: Cursor<Service>): Service =
                ServiceMapper.read(cursor)
    }
}