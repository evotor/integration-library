package ru.evotor.framework.core.action.event.receipt.payment.combined

import android.os.Bundle
import ru.evotor.framework.core.action.event.receipt.payment.combined.event.PaybackPaymentDelegatorEvent
import ru.evotor.framework.core.action.processor.ActionProcessor

abstract class PaybackPaymentDelegatorEventProcessor : ActionProcessor() {
    override fun process(action: String, bundle: Bundle?, callback: Callback) {
        val event = PaybackPaymentDelegatorEvent.create(bundle) ?: run {
            callback.skip()
            return
        }

        call(action, event, callback)
    }

    abstract fun call(action: String, event: PaybackPaymentDelegatorEvent, callback: ActionProcessor.Callback)
}
