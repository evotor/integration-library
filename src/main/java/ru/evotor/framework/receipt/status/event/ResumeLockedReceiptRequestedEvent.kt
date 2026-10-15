package ru.evotor.framework.receipt.status.event

import android.os.Bundle
import ru.evotor.IBundlable

class ResumeLockedReceiptRequestedEvent internal constructor(val receiptUuid: String) : IBundlable {
    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid)
        return result
    }

    companion object {
        private const val KEY_RECEIPT_UUID = "receiptUuid"

        fun from(bundle: Bundle?) = bundle?.getString(KEY_RECEIPT_UUID, null)?.let(::ResumeLockedReceiptRequestedEvent)
    }
}
