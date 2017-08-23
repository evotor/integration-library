package ru.evotor.framework.core.action.event.receipt.payment.system.event

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.BundleUtils
import java.math.BigDecimal

class PaymentSystemSellEvent(
        val receiptUuid: String,
        val sum: BigDecimal,
        val description: String
) : PaymentSystemEvent(PaymentSystemEvent.OperationType.SELL) {

    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid)
        result.putString(KEY_SUM, sum.toPlainString())
        result.putString(KEY_DESCRIPTION, description)
        return result
    }

    companion object {
        private val KEY_RECEIPT_UUID = "receiptUuid"
        private val KEY_DESCRIPTION = "description"
        private val KEY_SUM = "sum"

        fun create(bundle: Bundle?): PaymentSystemSellEvent? {
            if (bundle == null) {
                return null
            }
            val receiptUuid = bundle.getString(KEY_RECEIPT_UUID, null)
            val sum = BundleUtils.getMoney(bundle, KEY_SUM)
            val description = bundle.getString(KEY_DESCRIPTION, null)
            return PaymentSystemSellEvent(receiptUuid, sum!!, description)
        }
    }
}