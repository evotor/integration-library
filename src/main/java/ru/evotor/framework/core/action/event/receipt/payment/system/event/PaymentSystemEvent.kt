package ru.evotor.framework.core.action.event.receipt.payment.system.event

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.Utils

abstract class PaymentSystemEvent(
        val operationType: OperationType
) : IBundlable {

    public enum class OperationType {
        UNKNOWN, SELL, SELL_CANCEL, PAYBACK, PAYBACK_CANCEL
    }

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_OPERATION_TYPE, operationType.name)
        return result
    }

    companion object {
        const val NAME_ACTION = "evo.v2.receipt.paymentSystem"
        const val NAME_PERMISSION = "ru.evotor.permission.PAYMENT_SYSTEM"
        const val META_NAME_PAYMENT_SYSTEM_ID = "ru.evotor.paymentSystem.PAYMENT_SYSTEM_ID"

        private val KEY_OPERATION_TYPE = "operationType"

        fun create(bundle: Bundle?): PaymentSystemEvent? {
            if (bundle == null) {
                return null
            }
            val operationType = Utils.safeValueOf(OperationType::class.java, bundle.getString(KEY_OPERATION_TYPE, null), OperationType.UNKNOWN)
            return when (operationType) {
                OperationType.SELL -> PaymentSystemSellEvent.create(bundle)
                OperationType.SELL_CANCEL -> PaymentSystemSellCancelEvent.create(bundle)
                OperationType.PAYBACK -> PaymentSystemPaybackEvent.create(bundle)
                OperationType.PAYBACK_CANCEL -> PaymentSystemPaybackCancelEvent.create(bundle)
                else -> null
            }
        }
    }
}