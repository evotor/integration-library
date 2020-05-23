package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.BundleUtils
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.PositionTable
import ru.evotor.framework.receipt.position.PreferentialMedicine
import ru.evotor.framework.safeGetInt
import java.math.BigDecimal

internal object PreferentialMedicineMapper {

    private const val KEY_PREFERENTIAL_MEDICINE_TYPE = "DiscountType"
    private const val KEY_PREFERENTIAL_MEDICINE_VALUE = "DiscountValue"


    internal fun readFromCursor(cursor: Cursor): PreferentialMedicine =
            PreferentialMedicine(
                    type = cursor.safeGetInt(PositionTable.COLUMN_PREFERENTIAL_MEDICINE)?.let {
                        PreferentialMedicine.PreferentialMedicineType.values()[it]

                    } ?: PreferentialMedicine.PreferentialMedicineType.NON_PREFERENTIAL_MEDICINE,
                    discountValue = cursor.optString(PositionTable.COLUMN_PREFERENTIAL_MEDICINE_AMOUNT)?.toBigDecimalOrNull()
                            ?: BigDecimal.ZERO
            )

    fun readFromBundle(bundle: Bundle?): PreferentialMedicine? = bundle?.let {
        val type = it.getString(KEY_PREFERENTIAL_MEDICINE_TYPE) ?: return@let null
        PreferentialMedicine(
                type = PreferentialMedicine.PreferentialMedicineType.valueOf(type),
                discountValue = BundleUtils.getBigDecimal(it, KEY_PREFERENTIAL_MEDICINE_VALUE) ?: BigDecimal.ZERO
        )

    }

    fun writeToBundle(preferentialMedicine: PreferentialMedicine) = Bundle().apply {
        this.putString(KEY_PREFERENTIAL_MEDICINE_TYPE, preferentialMedicine.type.name)
        this.putString(KEY_PREFERENTIAL_MEDICINE_VALUE, preferentialMedicine.discountValue.toPlainString())
    }
}