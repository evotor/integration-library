package ru.evotor.framework.core.action.event.receipt.payment.system.event

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.Utils
import ru.evotor.framework.getMoney
import java.math.BigDecimal
import kotlin.apply
import kotlin.jvm.java

abstract class PaymentIntentRequestedEvent(
    val operationType: OperationType,
    open val receiptUuid: String,
    open val sum: BigDecimal
) : IBundlable {
    enum class OperationType {
        UNKNOWN,
        SELL,
        SELL_CANCEL
    }

    override fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_OPERATION_TYPE, operationType.name)
        putString(KEY_RECEIPT_UUID, receiptUuid)
        putString(KEY_SUM, sum.toPlainString())
    }

    companion object {
        const val NAME_ACTION = "evo.v2.receipt.paymentIntent"
        private const val KEY_OPERATION_TYPE = "operationType"
        private const val KEY_RECEIPT_UUID = "receiptUuid"
        private const val KEY_SUM = "sum"

        fun create(bundle: Bundle?): PaymentIntentRequestedEvent? {
            if (bundle == null) {
                return null
            }
            val operationType = Utils.safeValueOf(OperationType::class.java, bundle.getString(KEY_OPERATION_TYPE, null), OperationType.UNKNOWN)
            val receiptUuid = bundle.getString(KEY_RECEIPT_UUID, null)
            val sum = bundle.getMoney(KEY_SUM)
            if (receiptUuid == null || sum == null) return null
            return when (operationType) {
                OperationType.SELL -> PaymentIntentSellRequestedEvent(receiptUuid, sum)
                OperationType.SELL_CANCEL -> PaymentIntentSellCancelRequestedEvent(receiptUuid, sum)
                else -> null
            }
        }
    }
}
