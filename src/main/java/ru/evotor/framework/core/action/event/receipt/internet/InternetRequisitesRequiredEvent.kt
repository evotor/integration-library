package ru.evotor.framework.core.action.event.receipt.internet

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetPurchaserContactData

/**
 * Событие передачи реквизитов в случае интернет-расчёта.
 *
 * Происходит перед сразу после оплат.
 *
 * @param receiptUuid uuid чека
 * @param receiptFromInternet признак расчета в сети «Интернет»
 * @param paymentPlace текущее место расчетов в чеке
 * @param purchaserContactData текущие контакты покупателя в чеке
 */
class InternetRequisitesRequiredEvent(
    val receiptUuid: String,
    val receiptFromInternet: Boolean,
    val paymentPlace: String? = null,
    val purchaserContactData: SetPurchaserContactData? = null
) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid)
        result.putBoolean(KEY_RECEIPT_FROM_INTERNET, receiptFromInternet)
        result.putString(KEY_PAYMENT_PLACE, paymentPlace)
        result.putBundle(KEY_PURCHASER_CONTACT_DATA, purchaserContactData?.toBundle())
        return result
    }

    companion object {

        /**
         * Передача реквизитов в случае интернет-расчёта.
         *
         * Значение константы: <code>evo.v2.receipt.internetRequisites</code>.
         */
        const val NAME_ACTION = "evo.v2.receipt.internetRequisites"

        private const val KEY_RECEIPT_UUID = "receiptUuid"
        private const val KEY_RECEIPT_FROM_INTERNET = "receiptFromInternet"
        private const val KEY_PAYMENT_PLACE = "paymentPlace"
        private const val KEY_PURCHASER_CONTACT_DATA = "purchaserContactData"

        fun from(bundle: Bundle?): InternetRequisitesRequiredEvent? = bundle?.let {
            InternetRequisitesRequiredEvent(
                getReceiptUuid(it) ?: return null,
                getReceiptFromInternet(it) ?: return null,
                getPaymentPlace(it),
                getPurchaserContactData(it)
            )
        }

        private fun getReceiptUuid(bundle: Bundle): String? =
            bundle.getString(KEY_RECEIPT_UUID, null)

        private fun getReceiptFromInternet(bundle: Bundle): Boolean? =
            bundle.getBoolean(KEY_RECEIPT_FROM_INTERNET, false)

        private fun getPaymentPlace(bundle: Bundle): String? =
            bundle.getString(KEY_PAYMENT_PLACE, null)

        private fun getPurchaserContactData(bundle: Bundle): SetPurchaserContactData? =
            SetPurchaserContactData.from(bundle.getBundle(KEY_PURCHASER_CONTACT_DATA))
    }

}
