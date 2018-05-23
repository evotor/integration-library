package ru.evotor.framework.inventory

import ru.evotor.framework.receipt.TaxNumber
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.math.BigDecimal

/**
 * Created by a.lunkov on 07.03.2018.
 */
class ProductQuery : FilterBuilder<ProductQuery, ProductQuery.SortOrder, ProductItem?>(ProductTable.URI) {

    @JvmField
    val uuid = addFieldFilter<String>(ProductTable.ROW_UUID)
    @JvmField
    val parentUuid = addFieldFilter<String?>(ProductTable.ROW_PARENT_UUID)
    @JvmField
    val code = addFieldFilter<String?>(ProductTable.ROW_CODE)
    @JvmField
    val name = addFieldFilter<String>(ProductTable.ROW_NAME)
    @JvmField
    val taxNumber = addFieldFilter<TaxNumber>(ProductTable.ROW_TAX_NUMBER)
    @JvmField
    val type = addFieldFilter<ProductType>(ProductTable.ROW_TYPE)
    @JvmField
    val price = addFieldFilter<BigDecimal, BigDecimal>(ProductTable.ROW_PRICE_OUT, {it.multiply(BigDecimal(100))})
    @JvmField
    val quantity = addFieldFilter<BigDecimal, BigDecimal>(ProductTable.ROW_QUANTITY, {it.multiply(BigDecimal(1000))})
    @JvmField
    val description = addFieldFilter<String?>(ProductTable.ROW_DESCRIPTION)
    @JvmField
    val measureName = addFieldFilter<String>(ProductTable.ROW_MEASURE_NAME)
    @JvmField
    val measurePrecision = addFieldFilter<Int>(ProductTable.ROW_MEASURE_PRECISION)
    @JvmField
    val alcoholByVolume = addFieldFilter<BigDecimal?, BigDecimal?>(ProductTable.ROW_ALCOHOL_BY_VOLUME, {it?.multiply(BigDecimal(1000))})
    @JvmField
    val alcoholProductKindCode = addFieldFilter<Long?>(ProductTable.ROW_ALCOHOL_PRODUCT_KIND_CODE)
    @JvmField
    val tareVolume = addFieldFilter<BigDecimal?, BigDecimal?>(ProductTable.ROW_TARE_VOLUME, {it?.multiply(BigDecimal(1000))})

    override val currentQuery: ProductQuery
        get() = this

    class SortOrder : FilterBuilder.SortOrder<SortOrder>() {

        @JvmField
        val uuid = addFieldSorter(ProductTable.ROW_UUID)
        @JvmField
        val parentUuid = addFieldSorter(ProductTable.ROW_PARENT_UUID)
        @JvmField
        val code = addFieldSorter(ProductTable.ROW_CODE)
        @JvmField
        val name = addFieldSorter(ProductTable.ROW_NAME)
        @JvmField
        val taxNumber = addFieldSorter(ProductTable.ROW_TAX_NUMBER)
        @JvmField
        val type = addFieldSorter(ProductTable.ROW_TYPE)
        @JvmField
        val price = addFieldSorter(ProductTable.ROW_PRICE_OUT)
        @JvmField
        val quantity = addFieldSorter(ProductTable.ROW_QUANTITY)
        @JvmField
        val description = addFieldSorter(ProductTable.ROW_DESCRIPTION)
        @JvmField
        val measureName = addFieldSorter(ProductTable.ROW_MEASURE_NAME)
        @JvmField
        val measurePrecision = addFieldSorter(ProductTable.ROW_MEASURE_PRECISION)
        @JvmField
        val alcoholByVolume = addFieldSorter(ProductTable.ROW_ALCOHOL_BY_VOLUME)
        @JvmField
        val alcoholProductKindCode = addFieldSorter(ProductTable.ROW_ALCOHOL_PRODUCT_KIND_CODE)
        @JvmField
        val tareVolume = addFieldSorter(ProductTable.ROW_TARE_VOLUME)

        override val currentSortOrder: SortOrder
            get() = this

    }

    override fun getValue(cursor: Cursor<ProductItem?>): ProductItem? {
        return ProductMapper.getValueFromCursor(cursor)
    }

}
