package ru.evotor.framework.inventory

import android.database.Cursor
import ru.evotor.framework.Utils
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.TaxNumber
import java.math.BigDecimal

/**
 * Created by a.lunkov on 13.03.2018.
 */
internal object ProductMapper {
    @JvmStatic
    fun getValueFromCursor(cursor: Cursor): ProductItem? {
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
                        taxNumber = Utils.safeValueOf(TaxNumber::class.java, cursor.getString(cursor.getColumnIndex(ProductTable.ROW_TAX_NUMBER)), TaxNumber.NO_VAT),
                        classificationCode = cursor.optString(cursor.getColumnIndex(ProductTable.ROW_CLASSIFICATION_CODE))
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}