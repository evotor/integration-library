package ru.evotor.framework.core.action.event.receipt.changes.receipt

import android.os.Bundle
import ru.evotor.framework.core.action.event.receipt.changes.IChange

/**
 * Устанавливает интернет-реквизиты чека.
 *
 * @param receiptFromInternet признак расчета в сети Интернет
 * @param paymentPlace место расчетов
 */
data class SetInternetRequisites(
    val receiptFromInternet: Boolean,
    val paymentPlace: String
) : IChange {

    override fun toBundle(): Bundle {
        return Bundle().apply {
            putBoolean(KEY_RECEIPT_FROM_INTERNET, receiptFromInternet)
            putString(KEY_PAYMENT_PLACE, paymentPlace)
        }
    }

    override fun getType(): IChange.Type {
        return IChange.Type.SET_INTERNET_REQUISITES
    }

    companion object {
        private const val KEY_RECEIPT_FROM_INTERNET = "receiptFromInternet"
        private const val KEY_PAYMENT_PLACE = "paymentPlace"

        @JvmStatic
        fun from(bundle: Bundle?): SetInternetRequisites? {
            bundle ?: return null

            val receiptFromInternet = bundle.getBoolean(KEY_RECEIPT_FROM_INTERNET, false)
            val paymentPlace = bundle.getString(KEY_PAYMENT_PLACE)

            if (paymentPlace == null) {
                return null
            }

            return SetInternetRequisites(
                receiptFromInternet = receiptFromInternet,
                paymentPlace = paymentPlace
            )
        }
    }
}
