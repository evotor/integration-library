package ru.evotor.framework.inventory

import android.database.Cursor
import ru.evotor.framework.Utils
import ru.evotor.framework.getMoney
import ru.evotor.framework.getQuantity
import ru.evotor.framework.optBoolean
import ru.evotor.framework.optInt
import ru.evotor.framework.optLong
import ru.evotor.framework.optMoney
import ru.evotor.framework.optString
import ru.evotor.framework.optVolume
import ru.evotor.framework.receipt.Measure
import ru.evotor.framework.receipt.TaxNumber

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
                        parentUuid = cursor.optString(ProductTable.ROW_PARENT_UUID),
                        code = cursor.optString(ProductTable.ROW_CODE),
                        name = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_NAME)),
                        taxNumber = Utils.safeValueOf(TaxNumber::class.java, cursor.getString(cursor.getColumnIndex(ProductTable.ROW_TAX_NUMBER)), TaxNumber.NO_VAT)
                )
            } else {
                val productType = Utils.safeValueOf(ProductType::class.java, cursor.getString(cursor.getColumnIndex(ProductTable.ROW_TYPE)), ProductType.NORMAL)
                return ProductItem.Product(
                        uuid = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_UUID)),
                        parentUuid = cursor.optString(ProductTable.ROW_PARENT_UUID),
                        code = cursor.optString(ProductTable.ROW_CODE),
                        type = productType,
                        name = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_NAME)),
                        description = cursor.optString(ProductTable.ROW_DESCRIPTION),
                        price = cursor.getMoney(ProductTable.ROW_PRICE_OUT),
                        costPrice = cursor.optMoney(ProductTable.ROW_COST_PRICE),
                        quantity = cursor.getQuantity(ProductTable.ROW_QUANTITY),
                        measure = readFromProductCursor(cursor),
                        alcoholByVolume = cursor.optVolume(ProductTable.ROW_ALCOHOL_BY_VOLUME),
                        alcoholProductKindCode = cursor.optLong(ProductTable.ROW_ALCOHOL_PRODUCT_KIND_CODE),
                        tareVolume = cursor.optVolume(ProductTable.ROW_TARE_VOLUME),
                        taxNumber = Utils.safeValueOf(TaxNumber::class.java, cursor.getString(cursor.getColumnIndex(ProductTable.ROW_TAX_NUMBER)), TaxNumber.NO_VAT),
                        classificationCode = cursor.optString(ProductTable.ROW_CLASSIFICATION_CODE),
                        allowPartialRealization = Utils.safeValueOf(ProductItem.AllowPartialRealization::class.java, cursor.optString(ProductTable.ROW_ALLOW_PARTIAL_REALIZATION), null),
                        isExcisable = cursor.optBoolean(ProductTable.ROW_IS_EXCISABLE)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun readFromProductCursor(cursor: Cursor): Measure {
        return cursor.let {
            Measure(
                    it.getString(cursor.getColumnIndex(ProductTable.ROW_MEASURE_NAME)),
                    it.getInt(cursor.getColumnIndex(ProductTable.ROW_MEASURE_PRECISION)),
                    it.optInt(ProductTable.ROW_MEASURE_CODE) ?: Measure.UNKNOWN_MEASURE_CODE
            )
        }
    }
}
