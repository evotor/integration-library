package ru.evotor.framework.core.action.event.receipt.merges

import android.os.Bundle
import ru.evotor.IBundlable

/**
 * Created by ivan on 26.06.17.
 */

class PositionsMergeEvent(val receiptUuid: String, val merges: ArrayList<PositionsMerge>) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        result.putParcelableArrayList(KEY_MERGES, merges)
        return result
    }

    companion object {
        const val NAME_SELL_RECEIPT = "evo.v2.receipt.sell.mergingPositions"
        const val NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.mergingPositions"
        const val NAME_BUY_RECEIPT = "evo.v2.receipt.buy.mergingPositions"
        const val NAME_BUYBACK_RECEIPT = "evo.v2.receipt.buyback.mergingPositions"
        private val KEY_RECEIPT_UUID = "receiptUuid"
        private val KEY_MERGES = "merges"

        fun create(bundle: Bundle?): PositionsMergeEvent? {
            return bundle?.let {
                val receiptUuid = bundle.getString("receiptUuid", "")
                val merges = bundle.getParcelableArrayList<PositionsMerge>(KEY_MERGES)
                PositionsMergeEvent(receiptUuid, merges)
            }
        }
    }
}