package ru.evotor.framework.receipt.status.event

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.receipt.Receipt

class ResumeLockedReceiptRequestedEvent(val type: Receipt.Type) : IBundlable {
    companion object {
        private const val KEY_TYPE = "TYPE"

        fun fromBundle(bundle: Bundle) =
            (bundle.getSerializable(KEY_TYPE) as Receipt.Type).let(::ResumeLockedReceiptRequestedEvent)
    }

    override fun toBundle() = Bundle().apply {
        putSerializable(KEY_TYPE, type)
    }
}
