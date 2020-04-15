package ru.evotor.framework.core.action.event.receipt.payment.system.event

import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.getMoney
import java.math.BigDecimal

/**
 * Событие, которое возникает при оплате чека продажи сторонней платёжной системой.
 */
class PaymentSystemSellEvent(
        val receiptUuid: String,
        val accoundId: String?,
        val sum: BigDecimal,
        val description: String?
) : PaymentSystemEvent(PaymentSystemEvent.OperationType.SELL) {

    override fun toBundle(): Bundle {
        val result = super.toBundle()
        result.putString(KEY_RECEIPT_UUID, receiptUuid)
        result.putString(KEY_ACCOUNT_ID, accoundId)
        result.putString(KEY_SUM, sum.toPlainString())
        result.putString(KEY_DESCRIPTION, description)
        return result
    }

    companion object {
        private val KEY_RECEIPT_UUID = "receiptUuid"
        private val KEY_ACCOUNT_ID = "accountId"
        private val KEY_DESCRIPTION = "description"
        private val KEY_SUM = "sum"

        fun create(bundle: Bundle?): PaymentSystemSellEvent? {
            if (bundle == null) {
                return null
            }
            val receiptUuid = bundle.getString(KEY_RECEIPT_UUID, null)
            val accoundId = bundle.getString(KEY_ACCOUNT_ID, null)
            val sum = bundle.getMoney(KEY_SUM)
            val description = bundle.getString(KEY_DESCRIPTION, null)
            return PaymentSystemSellEvent(receiptUuid, accoundId, sum!!, description)
        }
    }
}