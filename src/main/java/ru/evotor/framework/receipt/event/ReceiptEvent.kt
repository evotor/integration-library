package ru.evotor.framework.receipt.event

import android.os.Bundle

import ru.evotor.IBundlable

abstract class ReceiptEvent internal constructor(val receiptUuid: String) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid)
        return result
    }

    companion object {

        private const val KEY_RECEIPT_UUID = "receiptUuid"

        internal fun getReceiptUuid(bundle: Bundle): String? =
                bundle.getString(KEY_RECEIPT_UUID, null)

    }

}
