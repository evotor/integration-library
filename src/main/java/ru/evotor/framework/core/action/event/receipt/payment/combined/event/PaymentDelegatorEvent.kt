package ru.evotor.framework.core.action.event.receipt.payment.combined.event

import android.os.Bundle
import ru.evotor.IBundlable

class PaymentDelegatorEvent(val receiptUuid: String) : IBundlable {
    override fun toBundle(): Bundle =
            Bundle().apply { putString(KEY_RECEIPT_UUID, receiptUuid) }

    companion object {
        const val NAME_ACTION = "evo.v2.receipt.sell.payment.COMBINED"
        const val NAME_PERMISSION = "ru.evotor.permission.COMBINED"

        private const val KEY_RECEIPT_UUID = "receiptUuid"

        fun create(bundle: Bundle?): PaymentDelegatorEvent? {
            if (bundle == null) {
                return null
            }
            val receiptUuid = PaymentDelegatorEvent.getReceiptUuid(bundle) ?: return null
            return PaymentDelegatorEvent(receiptUuid)

        }

        fun getReceiptUuid(bundle: Bundle?): String? =
                bundle?.getString(KEY_RECEIPT_UUID)
    }
}