package ru.evotor.framework.core.action.event.receipt.payment.combined

import android.os.Bundle
import ru.evotor.framework.core.action.event.receipt.payment.combined.event.CombinedPaymentEvent
import ru.evotor.framework.core.action.processor.ActionProcessor

abstract class CombinedPaymentEventProcessor: ActionProcessor() {
    override fun process(action: String, bundle: Bundle?, callback: Callback) {
        val event = CombinedPaymentEvent.create(bundle) ?: return
        call(
                action,
                event,
                callback
        )
    }

    abstract fun call(action: String, event: CombinedPaymentEvent, callback: ActionProcessor.Callback)
}