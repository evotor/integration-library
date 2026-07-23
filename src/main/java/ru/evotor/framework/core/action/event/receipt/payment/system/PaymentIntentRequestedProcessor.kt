package ru.evotor.framework.core.action.event.receipt.payment.system

import android.os.Bundle
import ru.evotor.framework.core.action.event.receipt.payment.system.event.*
import ru.evotor.framework.core.action.processor.ActionProcessor
import kotlin.run

abstract class PaymentIntentRequestedProcessor : ActionProcessor() {
    override fun process(action: String, bundle: Bundle?, callback: Callback) {
        val event = PaymentIntentRequestedEvent.create(bundle) ?: run {
            callback.skip()
            return
        }
        when (event.operationType) {
            PaymentIntentRequestedEvent.OperationType.SELL -> sell(action, event as PaymentIntentSellRequestedEvent, callback)
            PaymentIntentRequestedEvent.OperationType.SELL_CANCEL -> sellCancel(action, event as PaymentIntentSellCancelRequestedEvent, callback)
            else -> {
                // do nothing
            }
        }
    }

    abstract fun sell(action: String, event: PaymentIntentSellRequestedEvent, callback: Callback)

    abstract fun sellCancel(action: String, event: PaymentIntentSellCancelRequestedEvent, callback: Callback)
}
