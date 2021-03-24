package ru.evotor.framework.receipt.position

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.core.action.datamapper.BundleUtils
import java.math.BigDecimal

/**
 * Частичное выбытие
 */
data class Partial(
        /**
         * Остаток товара до выполнения операции. Необходимое для заполнения поле,
         * при частичной продаже маркированного товара.
         */
        val initialQuantity: BigDecimal,
        /**
         * Количество товара в упаковке всего. Необходимое для заполнения поле,
         * при частичной продаже маркированного товара.
         */
        val quantityInPackage: BigDecimal
) : IBundlable {

        override fun toBundle(): Bundle = Bundle().apply {
                putString(KEY_INITIAL_QUANTITY, initialQuantity.toPlainString())
                putString(KEY_QUANTITY_IN_PACKAGE, quantityInPackage.toPlainString())
        }

        companion object {

                private const val KEY_INITIAL_QUANTITY = "InitialQuantity"
                private const val KEY_QUANTITY_IN_PACKAGE = "QuantityInPackage"

                @JvmStatic
                fun from(bundle: Bundle?): Partial? = bundle?.let {
                        val initialQuantity = BundleUtils.getBigDecimal(it, KEY_INITIAL_QUANTITY, BigDecimal.ZERO)
                        val quantityInPackage = BundleUtils.getBigDecimal(it, KEY_QUANTITY_IN_PACKAGE, BigDecimal.ZERO)

                        Partial(initialQuantity = initialQuantity, quantityInPackage = quantityInPackage)
                }
        }
}