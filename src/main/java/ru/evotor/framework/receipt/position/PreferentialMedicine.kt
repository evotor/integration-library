package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.receipt.position.PreferentialMedicine.PreferentialMedicineType
import ru.evotor.framework.receipt.position.mapper.PreferentialMedicineMapper
import java.math.BigDecimal

/**
 * Льгота для позиции, используется при продаже лекарственных препаратов
 */
data class PreferentialMedicine(
        /**
         * Тип льготы
         */
        val type : PreferentialMedicineType,
        /**
         * Сумма льготы.
         * Указывается только в случае рецепта с частичной льготой [PreferentialMedicineType.PARTIAL_PREFERENTIAL_MEDICINE]
         *
         * Это десятичное число с фиксированной точностью 2 знака после десятичного разделителя целой и дробной части.
         * Например, при субсидии 123 руб 00 коп значение 123.00
         */
        val preferentialValue: BigDecimal? = null
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