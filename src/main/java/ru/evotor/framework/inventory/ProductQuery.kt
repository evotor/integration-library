package ru.evotor.framework.inventory

import ru.evotor.framework.Utils
import ru.evotor.framework.receipt.TaxNumber
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.math.BigDecimal

/**
 * Created by a.lunkov on 07.03.2018.
 */
class ProductQuery : FilterBuilder<ProductQuery, ProductQuery.SortOrder, ProductItem?>(ProductTable.URI) {

    @JvmField
    val uuid = addFieldFilter<String>("UUID")
    @JvmField
    val parentUuid = addFieldFilter<String?>("PARENT_UUID")
    @JvmField
    val code = addFieldFilter<String?>("CODE")
    @JvmField
    val name = addFieldFilter<String>("NAME")
    @JvmField
    val taxNumber = addFieldFilter<TaxNumber>("TAX_NUMBER")
    @JvmField
    val type = addFieldFilter<ProductType>("TYPE")
    @JvmField
    val price = addFieldFilter<BigDecimal>("PRICE_OUT")
    @JvmField
    val quantity = addFieldFilter<BigDecimal>("QUANTITY")
    @JvmField
    val description = addFieldFilter<String?>("DESCRIPTION")
    @JvmField
    val measureName = addFieldFilter<String>("MEASURE_NAME")
    @JvmField
    val measurePrecision = addFieldFilter<Int>("MEASURE_PRECISION")
    @JvmField
    val alcoholByVolume = addFieldFilter<BigDecimal?>("ALCOHOL_BY_VOLUME")
    @JvmField
    val alcoholProductKindCode = addFieldFilter<Long?>("ALCOHOL_PRODUCT_KIND_CODE")
    @JvmField
    val tareVolume = addFieldFilter<BigDecimal?>("TARE_VOLUME")

    override val currentQuery: ProductQuery
        get() = this

    class SortOrder : FilterBuilder.SortOrder<SortOrder>() {

        @JvmField
        val uuid = addFieldSorter("UUID")
        @JvmField
        val parentUuid = addFieldSorter("PARENT_UUID")
        @JvmField
        val code = addFieldSorter("CODE")
        @JvmField
        val name = addFieldSorter("NAME")
        @JvmField
        val taxNumber = addFieldSorter("TAX_NUMBER")
        @JvmField
        val type = addFieldSorter("TYPE")
        @JvmField
        val price = addFieldSorter("PRICE")
        @JvmField
        val quantity = addFieldSorter("QUANTITY")
        @JvmField
        val description = addFieldSorter("DESCRIPTION")
        @JvmField
        val measureName = addFieldSorter("MEASURE_NAME")
        @JvmField
        val measurePrecision = addFieldSorter("MEASURE_PRECISION")
        @JvmField
        val alcoholByVolume = addFieldSorter("ALCOHOL_BY_VOLUME")
        @JvmField
        val alcoholProductKindCode = addFieldSorter("ALCOHOL_PRODUCT_KIND_CODE")
        @JvmField
        val tareVolume = addFieldSorter("TARE_VOLUME")

        override val currentSortOrder: SortOrder
            get() = this

    }

    override fun getValue(cursor: Cursor<ProductItem?>): ProductItem? {
        try {
            if (cursor.getInt(cursor.getColumnIndex(ProductTable.ROW_IS_GROUP)) > 0) {
                return ProductItem.ProductGroup(
                        uuid = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_UUID)),
                        parentUuid = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_PARENT_UUID)),
                        code = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_CODE)),
                        name = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_NAME)),
                        taxNumber = Utils.safeValueOf(TaxNumber::class.java, cursor.getString(cursor.getColumnIndex(ProductTable.ROW_TAX_NUMBER)), TaxNumber.NO_VAT)
                )
            } else {
                return ProductItem.Product(
                        uuid = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_UUID)),
                        parentUuid = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_PARENT_UUID)),
                        code = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_CODE)),
                        type = Utils.safeValueOf(ProductType::class.java, cursor.getString(cursor.getColumnIndex(ProductTable.ROW_TYPE)), ProductType.NORMAL),
                        name = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_NAME)),
                        description = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_DESCRIPTION)),
                        price = BigDecimal(cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_PRICE_OUT))).divide(BigDecimal(100)),
                        quantity = BigDecimal(cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_QUANTITY))).divide(BigDecimal(1000)),
                        measureName = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_MEASURE_NAME)),
                        measurePrecision = cursor.getInt(cursor.getColumnIndex(ProductTable.ROW_MEASURE_PRECISION)),
                        alcoholByVolume = cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_ALCOHOL_BY_VOLUME)).let { BigDecimal(it).divide(BigDecimal(1000)) },
                        alcoholProductKindCode = cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_ALCOHOL_PRODUCT_KIND_CODE)),
                        tareVolume = cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_TARE_VOLUME)).let { BigDecimal(it).divide(BigDecimal(1000)) },
                        taxNumber = Utils.safeValueOf(TaxNumber::class.java, cursor.getString(cursor.getColumnIndex(ProductTable.ROW_TAX_NUMBER)), TaxNumber.NO_VAT)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}
