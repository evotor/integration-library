package ru.evotor.framework.core.action.event.receipt.internet

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetPurchaserContactData

/**
 * Результат обработки события [InternetRequisitesRequiredEvent].
 */
class InternetRequisitesRequiredEventResult(
    val receiptFromInternet: Boolean,
    val paymentPlace: String,
    val purchaserContactData: SetPurchaserContactData
) : IBundlable {

    override fun toBundle(): Bundle {
        if (purchaserContactData.email.isNullOrEmpty() xor purchaserContactData.phone.isNullOrEmpty()) {
            throw IllegalArgumentException("email or phone should be not null or empty") // TODO
        }
        val bundle = Bundle()
        bundle.putBoolean(KEY_RECEIPT_FROM_INTERNET, receiptFromInternet)
        bundle.putString(KEY_PAYMENT_PLACE, paymentPlace)
        bundle.putBundle(KEY_PURCHASER_CONTACT_DATA, purchaserContactData.toBundle())
        return bundle
    }

    companion object {
        private const val KEY_RECEIPT_FROM_INTERNET = "receiptFromInternet"
        private const val KEY_PAYMENT_PLACE = "paymentPlace"
        private const val KEY_PURCHASER_CONTACT_DATA = "purchaserContactData"

        fun create(bundle: Bundle?): InternetRequisitesRequiredEventResult? = bundle?.let {
            val receiptFromInternet = bundle.getBoolean(KEY_RECEIPT_FROM_INTERNET, false)
            val paymentPlace = bundle.getString(KEY_PAYMENT_PLACE) ?: return null
            val purchaserContactData = SetPurchaserContactData.from(bundle.getBundle(KEY_PURCHASER_CONTACT_DATA)) ?: return null
            return InternetRequisitesRequiredEventResult(
                receiptFromInternet,
                paymentPlace,
                purchaserContactData
            )
        }
    }
}
