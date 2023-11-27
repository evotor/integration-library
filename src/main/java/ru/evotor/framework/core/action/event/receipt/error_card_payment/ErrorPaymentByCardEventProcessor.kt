package ru.evotor.framework.core.action.event.receipt.error_card_payment

import android.os.Bundle
import android.os.RemoteException
import ru.evotor.framework.core.action.processor.ActionProcessor

abstract class ErrorPaymentByCardEventProcessor : ActionProcessor() {

    @Throws(RemoteException::class)
    override fun process(action: String, bundle: Bundle?, callback: ActionProcessor.Callback) {
        val event = ErrorPaymentByCardEvent.create(bundle)
        if (event == null) {
            callback.skip()
            return
        }
        call(action, event, callback)
    }

    abstract fun call(action: String, event: ErrorPaymentByCardEvent, callback: ActionProcessor.Callback)
}
