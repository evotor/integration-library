package ru.evotor.framework.core.action.event.receipt.payment.system.result

import android.os.Bundle
import ru.evotor.framework.Utils
import ru.evotor.framework.payment.CashlessInfo
import ru.evotor.framework.payment.PaymentType

class PaymentSystemPaymentOkResult(
        val rrn: String,
        val slip: List<String>,
        val paymentInfo: String?,
        val paymentType: PaymentType = PaymentType.ELECTRON,
        val cashlessInfo: CashlessInfo? = null
) : PaymentSystemPaymentResult(ResultType.OK) {

    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putString(KEY_RRN, rrn)
        result.putStringArrayList(KEY_SLIP, if (slip is ArrayList) slip else ArrayList(slip))
        result.putString(KEY_PAYMENT_INFO, paymentInfo)
        result.putString(KEY_PAYMENT_TYPE, paymentType.name)
        result.putBundle(KEY_CASHLESS_INFO, cashlessInfo?.toBundle())
        return result
    }

    companion object {
        private val KEY_RRN = "rrn"
        private val KEY_SLIP = "slip"
        private val KEY_PAYMENT_INFO = "paymentInfo"
        private val KEY_PAYMENT_TYPE = "paymentType"
        private val KEY_CASHLESS_INFO = "cashlessInfo"

        fun create(bundle: Bundle?): PaymentSystemPaymentOkResult? {
            if (bundle == null) {
                return null
            }
            val rrn = bundle.getString(KEY_RRN, null)
            val slip = bundle.getStringArrayList(KEY_SLIP) ?: emptyList<String>()
            val paymentInfo = bundle.getString(KEY_PAYMENT_INFO, null)
            val paymentType = Utils.safeValueOf(PaymentType::class.java, bundle.getString(KEY_PAYMENT_TYPE), PaymentType.UNKNOWN)
            val cashlessInfo = CashlessInfo.fromBundle(bundle.getBundle(KEY_CASHLESS_INFO))
            return PaymentSystemPaymentOkResult(rrn, slip, paymentInfo, paymentType, cashlessInfo)
        }
    }
}
