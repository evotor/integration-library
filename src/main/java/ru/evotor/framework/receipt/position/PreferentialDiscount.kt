package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.receipt.position.mapper.PreferentialDiscountMapper
import java.math.BigDecimal

/**
 * Льгота для позиции, используется при продаже лекарственных препаратов
 */
data class PreferentialDiscount(
        val type : PreferentialDiscountType,
        val discountValue: BigDecimal
) : IBundlable{
    companion object{
        fun from(bundle: Bundle?): PreferentialDiscount? = PreferentialDiscountMapper.readFromBundle(bundle)
    }
    override fun toBundle(): Bundle = PreferentialDiscountMapper.writeToBundle(this)

    /**
     * Тип льготы
     */
    enum class PreferentialDiscountType {
        FULL_DISCOUNT,
        PARTIAL_DISCOUNT,
        NON_DISCOUNT
    }
}