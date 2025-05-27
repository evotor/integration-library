package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.receipt.position.mapper.VolumeSortAccountingMapper
import java.math.BigDecimal

sealed class VolumeSortAccounting : IBundlable {

    override fun toBundle(): Bundle {
        return VolumeSortAccountingMapper.toBundle(this)
    }

    data class Piece(
        /**
         * Идентификатор продукта GTIN
         */
        val gtin: String,
        /**
         * Тип выбытия по ОСУ
         */
        val type: RealizationType = RealizationType.HORECA
    ) : VolumeSortAccounting()

    data class Measured(
        /**
         * Идентификатор продукта GTIN
         */
        val gtin: String,
        /**
         * Количество товара по ОСУ
         */
        val quantity: BigDecimal,
        /**
         * Тип выбытия по ОСУ
         */
        val type: RealizationType = RealizationType.HORECA
    ) : VolumeSortAccounting()

    enum class RealizationType {
        /**
         * Общепит
         */
        HORECA,

        /**
         * Оптовая торговля с организацией или ИП
         */
        WHOLESALE
    }

    companion object {
        @JvmStatic
        fun fromBundle(bundle: Bundle?): VolumeSortAccounting? {
            return VolumeSortAccountingMapper.fromBundle(bundle)
        }
    }
}
