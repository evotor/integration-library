package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.receipt.Receipt

object ReceiptStateMapper {
    private const val KEY_STATUS = "STATUS"
    private const val KEY_IS_RECEIPT_LOCKED = "IS_RECEIPT_LOCKED"

    fun from(bundle: Bundle): Receipt.State? {
        val status = if (bundle.containsKey(KEY_STATUS)) {
            Receipt.Status.values().getOrNull(bundle.getInt(KEY_STATUS)).let {
                it ?: return null
            }
        } else {
            return null
        }
        val isReceiptLocked = if (bundle.containsKey(KEY_IS_RECEIPT_LOCKED)) {
            bundle.getBoolean(KEY_IS_RECEIPT_LOCKED)
        } else {
            return null
        }
        return Receipt.State(status, isReceiptLocked)
    }

    fun toBundle(state: Receipt.State) = Bundle().apply {
        putInt(KEY_STATUS, state.status.ordinal)
        state.isReceiptLocked?.let { putBoolean(KEY_IS_RECEIPT_LOCKED, it) }
    }
}
