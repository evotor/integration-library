package ru.evotor.framework.core.action.event.receipt.payment.combined.event

import android.os.Bundle
import ru.evotor.IBundlable

class PaybackPaymentDelegatorEvent(
    val receiptUuid: String,
    val availablePaybackSum: ArrayList<PaymentDelegatorPaybackData>? = null
) : IBundlable {
    override fun toBundle(): Bundle =
        Bundle().apply {
            putString(KEY_RECEIPT_UUID, receiptUuid)
            putParcelableArrayList(KEY_AVAILABLE_PAYBACK_SUM, availablePaybackSum)
        }

    companion object {
        /**
         * Оплата чека продажи будет передана другому приложению.
         *
         * Значение константы: <code>evo.v2.receipt.sell.payment.COMBINED</code>.
         */
        const val NAME_ACTION = "evo.v2.receipt.sell.payment.COMBINED_PAYBACK"
        /**
         * Разрешение, которое необходимо указать в манифесте приложения.
         */
        const val NAME_PERMISSION = "ru.evotor.permission.COMBINED"

        private const val KEY_RECEIPT_UUID = "receiptUuid"
        private const val KEY_AVAILABLE_PAYBACK_SUM = "availablePaybackSum"

        fun create(bundle: Bundle?): PaybackPaymentDelegatorEvent? {
            if (bundle == null) {
                return null
            }
            bundle.classLoader = PaymentDelegatorPaybackData::class.java.classLoader
            val receiptUuid = getReceiptUuid(bundle) ?: return null
            val remains = getAvailablePaybackSum(bundle)
            return PaybackPaymentDelegatorEvent(receiptUuid, remains)

        }

        fun getReceiptUuid(bundle: Bundle?): String? =
            bundle?.getString(KEY_RECEIPT_UUID)

        fun getAvailablePaybackSum(bundle: Bundle?): ArrayList<PaymentDelegatorPaybackData>? =
            bundle?.let { b ->
                b.getParcelableArrayList(KEY_AVAILABLE_PAYBACK_SUM)
            }
    }
}
