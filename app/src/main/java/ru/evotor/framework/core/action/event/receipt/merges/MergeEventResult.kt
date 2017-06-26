package ru.evotor.framework.core.action.event.receipt.merges

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra

/**
 * Created by ivan on 26.06.17.
 */

class MergeEventResult(val extra: SetExtra?) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putBundle(KEY_RECEIPT_EXTRA, extra?.toBundle())
        return result
    }

    companion object {
        private val KEY_RECEIPT_EXTRA = "extra"

        fun create(bundle: Bundle?): MergeEventResult? {
            return if (bundle == null)
                null
            else
                MergeEventResult(SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)))
        }
    }
}
