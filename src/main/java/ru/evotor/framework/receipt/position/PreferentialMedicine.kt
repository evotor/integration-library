package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.receipt.position.mapper.PreferentialMedicineMapper
import java.math.BigDecimal

/**
 * Льгота для позиции, используется при продаже лекарственных препаратов
 */
data class PreferentialMedicine(
        val type : PreferentialMedicineType,
        val preferentialValue: BigDecimal
) : IBundlable{
    companion object{
        @JvmStatic
        fun from(bundle: Bundle?): PreferentialMedicine? = PreferentialMedicineMapper.readFromBundle(bundle)
    }
    override fun toBundle(): Bundle = PreferentialMedicineMapper.writeToBundle(this)

    /**
     * Тип льготы
     */
    enum class PreferentialMedicineType {
        FULL_PREFERENTIAL_MEDICINE,
        PARTIAL_PREFERENTIAL_MEDICINE,
        NON_PREFERENTIAL_MEDICINE
    }
}