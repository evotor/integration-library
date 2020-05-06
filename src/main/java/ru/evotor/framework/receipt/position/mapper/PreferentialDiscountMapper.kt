package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.BundleUtils
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.PositionTable
import ru.evotor.framework.receipt.position.PreferentialDiscount
import ru.evotor.framework.safeGetInt
import java.math.BigDecimal

internal object PreferentialDiscountMapper {

    private const val KEY_DISCOUNT_TYPE = "DiscountType"
    private const val KEY_DISCOUNT_VALUE = "DiscountValue"


    internal fun readFromCursor(cursor: Cursor): PreferentialDiscount =
            PreferentialDiscount(
                    type = cursor.safeGetInt(PositionTable.COLUMN_PREFERENTIAL_DISCOUNT)?.let {
                        PreferentialDiscount.PreferentialDiscountType.values()[it]

                    } ?: PreferentialDiscount.PreferentialDiscountType.NON_DISCOUNT,
                    discountValue = cursor.optString(PositionTable.COLUMN_PREFERENTIAL_DISCOUNT_AMOUNT)?.toBigDecimalOrNull()
                            ?: BigDecimal.ZERO
            )

    fun readFromBundle(bundle: Bundle?): PreferentialDiscount? = bundle?.let {
        val type = it.getString(KEY_DISCOUNT_TYPE) ?: return@let null
        PreferentialDiscount(
                type = PreferentialDiscount.PreferentialDiscountType.valueOf(type),
                discountValue = BundleUtils.getBigDecimal(it, KEY_DISCOUNT_VALUE) ?: BigDecimal.ZERO
        )

    }

    fun writeToBundle(preferentialDiscount: PreferentialDiscount) = Bundle().apply {
        this.putString(KEY_DISCOUNT_TYPE, preferentialDiscount.type.name)
        this.putString(KEY_DISCOUNT_VALUE, preferentialDiscount.discountValue.toPlainString())
    }
}