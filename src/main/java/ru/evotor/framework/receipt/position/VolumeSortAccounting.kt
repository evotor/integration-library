package ru.evotor.framework.receipt.position

import android.database.Cursor
import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.Utils
import ru.evotor.framework.optBigDecimal
import ru.evotor.framework.optQuantity
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.PositionTable
import java.math.BigDecimal


data class VolumeSortAccounting(
    /**
     * Идентификатор продукта GTIN
     */
    val gtin: String,
    /**
     * Количество товара по ОСУ
     */
    val quantity: BigDecimal? = null,
    /**
     * Тип выбытия по ОСУ
     */
    val type: RealizationType = RealizationType.HORECA
) : IBundlable {

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

    override fun toBundle() = Bundle().apply {
        putString(KEY_VOLUME_SORT_QUANTITY, quantity?.toPlainString())
        putString(KEY_GTIN, gtin)
        putString(REALIZATION_TYPE, type.name)
    }

    companion object {

        /**
         * Разрешение для редактирования ОСУ.
         *
         * Указывайте разрешение в манифесте приложения, в элементе `<uses-permission android:name="" />` до элемента `<application>`.
         */
        const val VOLUME_SORT_PERMISSION = "ru.evotor.permission.receipt.volumeSortAccounting.SET";

        private const val KEY_VOLUME_SORT_QUANTITY = "VolumeSortQuantity"
        private const val KEY_GTIN = "GTIN"
        private const val REALIZATION_TYPE = "REALIZATION_TYPE_KEY"

        @JvmStatic
        fun from(bundle: Bundle?): VolumeSortAccounting? = bundle?.let {
            val quantity = it.optBigDecimal(KEY_VOLUME_SORT_QUANTITY)
            val gtin = it.getString(KEY_GTIN) ?: return null
            val type = Utils.safeValueOf(
                RealizationType::class.java,
                it.getString(REALIZATION_TYPE),
                RealizationType.HORECA
            )

            VolumeSortAccounting(quantity = quantity, gtin = gtin, type = type)
        }

        @JvmStatic
        fun from(cursor: Cursor?): VolumeSortAccounting? {
            return VolumeSortAccounting(
                gtin = cursor?.optString(PositionTable.COLUMN_VOLUME_SORT_ACCOUNTING_GTIN) ?: return null,
                quantity = cursor.optQuantity(PositionTable.COLUMN_VOLUME_SORT_ACCOUNTING_QUANTITY),
                type = Utils.safeValueOf(
                    RealizationType::class.java,
                    cursor.optString(PositionTable.COLUMN_VOLUME_SORT_ACCOUNTING_REALIZATION_TYPE),
                    RealizationType.HORECA
                )
            )
        }
    }
}
