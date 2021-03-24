package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.Utils
import ru.evotor.framework.core.action.datamapper.BundleUtils
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.PositionTable
import ru.evotor.framework.receipt.position.PreferentialMedicine
import ru.evotor.framework.safeGetInt

internal object PreferentialMedicineMapper {

    private const val KEY_PREFERENTIAL_MEDICINE_TYPE = "PreferentialMedicineType"
    private const val KEY_PREFERENTIAL_MEDICINE_VALUE = "PreferentialMedicineValue"

    internal fun readFromCursor(cursor: Cursor): PreferentialMedicine? = cursor.safeGetInt(PositionTable.COLUMN_PREFERENTIAL_MEDICINE)?.let {
        PreferentialMedicine(
                type = PreferentialMedicine.PreferentialMedicineType.values()[it],
                preferentialValue = cursor.optString(PositionTable.COLUMN_PREFERENTIAL_MEDICINE_AMOUNT)?.toBigDecimalOrNull()
        )
    }

    fun readFromBundle(bundle: Bundle?): PreferentialMedicine? = bundle?.let {
        val type = it.getString(KEY_PREFERENTIAL_MEDICINE_TYPE)
        if (type.isNullOrEmpty()) {
            return@let null
        }
        PreferentialMedicine(
                type = Utils.safeValueOf(PreferentialMedicine.PreferentialMedicineType::class.java,
                        type, PreferentialMedicine.PreferentialMedicineType.NON_PREFERENTIAL_MEDICINE),
                preferentialValue = BundleUtils.optBigDecimal(it, KEY_PREFERENTIAL_MEDICINE_VALUE)
        )
    }

    fun writeToBundle(preferentialMedicine: PreferentialMedicine) = Bundle().apply {
        this.putString(KEY_PREFERENTIAL_MEDICINE_TYPE, preferentialMedicine.type.name)
        this.putString(KEY_PREFERENTIAL_MEDICINE_VALUE, preferentialMedicine.preferentialValue?.toPlainString())
    }
}