package ru.evotor.framework.core.action.event.receipt.payment.combined

import android.os.Bundle
import ru.evotor.framework.core.action.event.receipt.payment.combined.event.PaymentDelegatorEvent
import ru.evotor.framework.core.action.processor.ActionProcessor

abstract class PaymentDelegatorEventProcessor: ActionProcessor() {
    override fun process(action: String, bundle: Bundle?, callback: Callback) {
        val event = PaymentDelegatorEvent.create(bundle) ?: run {
            callback.skip()
            return
        }
        call(
                action,
                event,
                callback
        )
    }

    abstract fun call(action: String, event: PaymentDelegatorEvent, callback: ActionProcessor.Callback)
}