package ru.evotor.framework.core.action.event.receipt.discount_required

import android.os.Bundle
import android.os.RemoteException
import ru.evotor.framework.core.action.processor.ActionProcessor

abstract class ReceiptDiscountRequiredEventProcessor : ActionProcessor() {
    @Throws(RemoteException::class)
    override fun process(action: String, bundle: Bundle?, callback: ActionProcessor.Callback) {
        val event = ReceiptDiscountRequiredEvent.create(bundle)
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

    abstract fun call(action: String, event: ReceiptDiscountRequiredEvent, callback: ActionProcessor.Callback)
}

