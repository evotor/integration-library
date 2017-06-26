package ru.evotor.framework.core.action.event.receipt.merges

import android.os.Bundle
import ru.evotor.IBundlable

/**
 * Created by ivan on 26.06.17.
 */

class MergeEvent(val receiptUuid: String, val merges: ArrayList<Merge>) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        result.putParcelableArrayList(KEY_MERGES, merges)
        return result
    }

    companion object {
        private val TAG = "PositionsMergedEvent"
        val NAME_SELL_RECEIPT = "evo.v2.receipt.sell.mergingPositions"
        val NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.mergingPositions"
        private val KEY_RECEIPT_UUID = "receiptUuid"
        private val KEY_MERGES = "merges"

        fun create(bundle: Bundle): MergeEvent {
            val receiptUuid = bundle.getString("receiptUuid", "")
            val merges = bundle.getParcelableArrayList<Merge>(KEY_MERGES)
            return MergeEvent(receiptUuid, merges)
        }
    }
}