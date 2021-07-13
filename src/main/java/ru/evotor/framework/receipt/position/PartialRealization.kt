package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.optBigDecimal
import java.math.BigDecimal

/**
 * Частичное выбытие
 */
data class PartialRealization(
        /**
         * Количество товара в упаковке всего. Необходимое для заполнения поле,
         * при частичной продаже маркированного товара.
         */
        val quantityInPackage: BigDecimal
) : IBundlable {

        override fun toBundle(): Bundle = Bundle().apply {
                putString(KEY_QUANTITY_IN_PACKAGE, quantityInPackage.toPlainString())
        }

        companion object {

                private const val KEY_QUANTITY_IN_PACKAGE = "QuantityInPackage"

                @JvmStatic
                fun from(bundle: Bundle?): PartialRealization? = bundle?.let {
                        val quantityInPackage = it.optBigDecimal(KEY_QUANTITY_IN_PACKAGE) ?: BigDecimal.ZERO

                        PartialRealization(quantityInPackage = quantityInPackage)
                }
        }
}