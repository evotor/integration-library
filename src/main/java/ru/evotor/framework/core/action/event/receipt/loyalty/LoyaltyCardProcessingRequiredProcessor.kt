package ru.evotor.framework.core.action.event.receipt.loyalty

import android.os.Bundle
import android.os.RemoteException
import ru.evotor.framework.core.action.processor.ActionProcessor

abstract class LoyaltyCardProcessingRequiredProcessor : ActionProcessor() {
    @Throws(RemoteException::class)
    override fun process(action: String, bundle: Bundle?, callback: Callback) {
        val event = LoyaltyCardProcessingRequiredEvent.create(bundle)
        if (event == null) {
            callback.skip()
            return
        }
        call(
            action,
            event,
            callback
        )
    }

    abstract fun call(action: String, event: LoyaltyCardProcessingRequiredEvent, callback: Callback)
}
