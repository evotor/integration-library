package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.optBigDecimal
import java.math.BigDecimal


data class VolumeSortAccountingRealization (
    /**
     * Идентификатор продукта GTIN
     */
    val gtin: String,
    /**
     * Количество товара по ОСУ
     */
    val volumeSortQuantity: BigDecimal
) : IBundlable {

    override fun toBundle() = Bundle().apply {
        putString(KEY_VOLUME_SORT_QUANTITY, volumeSortQuantity.toPlainString())
        putString(KEY_GTIN, gtin)
    }

    companion object {

        private const val KEY_VOLUME_SORT_QUANTITY = "VolumeSortQuantity"
        private const val KEY_GTIN = "GTIN"

        @JvmStatic
        fun from(bundle: Bundle?): VolumeSortAccountingRealization? = bundle?.let {
            val volumeSortQuantity = it.optBigDecimal(KEY_VOLUME_SORT_QUANTITY) ?: return null
            val gtin = it.getString(KEY_GTIN) ?: return null

            VolumeSortAccountingRealization(volumeSortQuantity = volumeSortQuantity, gtin = gtin)
        }
    }
}
