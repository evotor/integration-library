package ru.evotor.framework.receipt.position

import android.database.Cursor
import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.optBigDecimal
import ru.evotor.framework.optQuantity
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.PositionTable
import java.math.BigDecimal

/**
 * Данные партионного учёта маркированной продукции.
 */
data class BatchAccounting(
    /**
     * Идентификатор продукта GTIN
     */
    val gtin: String,
    /**
     * Номер производственной партии
     */
    val batchNumber: String,
    /**
     * Количество товара
     */
    val quantity: BigDecimal? = null
) : IBundlable {
    override fun toBundle() = Bundle().apply {
        putString(KEY_GTIN, gtin)
        putString(KEY_BATCH_NUMBER, batchNumber)
        putString(KEY_QUANTITY, quantity?.toPlainString())
    }

    companion object {
        /**
         * Разрешение для редактирования партионного учёта.
         *
         * Указывайте разрешение в манифесте приложения, в элементе `<uses-permission android:name="" />` до элемента `<application>`.
         */
        const val BATCH_ACCOUNTING_PERMISSION = "ru.evotor.permission.receipt.batchAccounting.SET"

        private const val KEY_GTIN = "GTIN"
        private const val KEY_BATCH_NUMBER = "BATCH_NUMBER"
        private const val KEY_QUANTITY = "BATCH_QUANTITY"

        @JvmStatic
        fun from(bundle: Bundle?): BatchAccounting? = bundle?.let {
            val gtin = it.getString(KEY_GTIN) ?: return null
            val batchNumber = it.getString(KEY_BATCH_NUMBER) ?: return null
            val quantity = it.optBigDecimal(KEY_QUANTITY)

            BatchAccounting(gtin = gtin, batchNumber = batchNumber, quantity = quantity)
        }

        @JvmStatic
        fun from(cursor: Cursor?): BatchAccounting? {
            return BatchAccounting(
                gtin = cursor?.optString(PositionTable.COLUMN_BATCH_ACCOUNTING_GTIN) ?: return null,
                batchNumber = cursor.optString(PositionTable.COLUMN_BATCH_ACCOUNTING_BATCH_NUMBER) ?: return null,
                quantity = cursor.optQuantity(PositionTable.COLUMN_BATCH_ACCOUNTING_QUANTITY)
            )
        }
    }
}
