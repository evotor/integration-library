package ru.evotor.framework.core.action.event.receipt.merges

import android.os.Bundle
import ru.evotor.IBundlable

/**
 * Событие, которое возникает при объединении позиций чека.
 *
 * Константы события указывают тип чека, позиции которого будут объединены.
 *
 * Чтобы приложение получало событие, значение константы необходимо указать в элементе <code><action></code> intent-фильтра соотвествующей службы.
 */

class PositionsMergeEvent(val receiptUuid: String, val merges: ArrayList<PositionsMerge>) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid)
        result.putParcelableArrayList(KEY_MERGES, merges)
        return result
    }

    companion object {
        /**
         * Объединение позиций чека продажи.
         *
         * Значение константы: <code>evo.v2.receipt.sell.mergingPositions</code>.
         */
        const val NAME_SELL_RECEIPT = "evo.v2.receipt.sell.mergingPositions"

        /**
         * Объединение позиций чека возврата.
         *
         * Значение константы: <code>evo.v2.receipt.payback.mergingPositions</code>.
         */
        const val NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.mergingPositions"

        /**
         * Объединение позиций чека покупки.
         *
         * Значение константы: <code>evo.v2.receipt.buy.mergingPositions</code>.
         */
        const val NAME_BUY_RECEIPT = "evo.v2.receipt.buy.mergingPositions"

        /**
         * Объединение позиций чека возврата покупки.
         *
         * Значение константы: <code>evo.v2.receipt.buyback.mergingPositions</code>.
         */
        const val NAME_BUYBACK_RECEIPT = "evo.v2.receipt.buyback.mergingPositions"

        /**
         * Объединение позиций чека коррекции прихода.
         *
         * Значение константы: <code>evo.v2.receipt.correction.income.mergingPositions</code>.
         */
        const val NAME_CORRECTION_INCOME_RECEIPT = "evo.v2.receipt.correction.income.mergingPositions"

        /**
         * Объединение позиций чека коррекции расхода.
         *
         * Значение константы: <code>evo.v2.receipt.correction.outcome.mergingPositions</code>.
         */
        const val NAME_CORRECTION_OUTCOME_RECEIPT = "evo.v2.receipt.correction.outcome.mergingPositions"

        /**
         * Объединение позиций чека коррекции возврата прихода.
         *
         * Значение константы: <code>evo.v2.receipt.correction.return.income.mergingPositions</code>.
         */
        const val NAME_CORRECTION_RETURN_INCOME_RECEIPT = "evo.v2.receipt.correction.return.income.mergingPositions"

        /**
         * Объединение позиций чека коррекции возврата расхода.
         *
         * Значение константы: <code>evo.v2.receipt.correction.return.outcome.mergingPositions</code>.
         */
        const val NAME_CORRECTION_RETURN_OUTCOME_RECEIPT = "evo.v2.receipt.correction.return.outcome.mergingPositions"


        private val KEY_RECEIPT_UUID = "receiptUuid"
        private val KEY_MERGES = "merges"

        fun create(bundle: Bundle?): PositionsMergeEvent? {
            return bundle?.let {
                val receiptUuid = bundle.getString("receiptUuid", "")
                val merges = bundle.getParcelableArrayList<PositionsMerge>(KEY_MERGES)
                        ?: ArrayList<PositionsMerge>()
                PositionsMergeEvent(receiptUuid, merges)
            }
        }
    }
}