package ru.evotor.framework.core.action.event.receipt.payment.system

import android.os.Bundle
import ru.evotor.framework.core.action.event.receipt.payment.system.event.*
import ru.evotor.framework.core.action.processor.ActionProcessor

abstract class PaymentSystemProcessor : ActionProcessor() {
    override fun process(action: String, bundle: Bundle?, callback: ActionProcessor.Callback) {
        val event = PaymentSystemEvent.create(bundle) ?: run {
            callback.skip()
            return
        }
        when (event.operationType) {
            PaymentSystemEvent.OperationType.SELL -> sell(action, event as PaymentSystemSellEvent, callback)
            PaymentSystemEvent.OperationType.SELL_CANCEL -> sellCancel(action, event as PaymentSystemSellCancelEvent, callback)
            PaymentSystemEvent.OperationType.PAYBACK -> payback(action, event as PaymentSystemPaybackEvent, callback)
            PaymentSystemEvent.OperationType.PAYBACK_CANCEL -> paybackCancel(action, event as PaymentSystemPaybackCancelEvent, callback)
            else -> null
        }
    }

    abstract fun sell(action: String, event: PaymentSystemSellEvent, callback: ActionProcessor.Callback)
    abstract fun sellCancel(action: String, event: PaymentSystemSellCancelEvent, callback: ActionProcessor.Callback)
    abstract fun payback(action: String, event: PaymentSystemPaybackEvent, callback: ActionProcessor.Callback)
    abstract fun paybackCancel(action: String, event: PaymentSystemPaybackCancelEvent, callback: ActionProcessor.Callback)
}

