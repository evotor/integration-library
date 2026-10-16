package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.receipt.Receipt

object ReceiptStateMapper {
    private const val KEY_STATUS = "STATUS"
    private const val KEY_IS_PROCESSING = "IS_PROCESSING"

    fun from(bundle: Bundle): Receipt.State? {
        val status = if (bundle.containsKey(KEY_STATUS)) {
            Receipt.Status.values().getOrNull(bundle.getInt(KEY_STATUS)).let {
                it ?: return null
            }
        } else {
            return null
        }
        val isProcessing = if (bundle.containsKey(KEY_IS_PROCESSING)) {
            bundle.getBoolean(KEY_IS_PROCESSING)
        } else {
            null
        }
        return Receipt.State(status, isProcessing)
    }

    fun toBundle(state: Receipt.State) = Bundle().apply {
        putInt(KEY_STATUS, state.status.ordinal)
        state.isProcessing?.let { putBoolean(KEY_IS_PROCESSING, it) }
    }
}
