package ru.evotor.framework.core.action.event.receipt.print_extra

import android.os.Bundle
import ru.evotor.framework.core.action.processor.ActionProcessor

abstract class PrintExtraRequiredEventProcessor : ActionProcessor() {

    override fun process(action: String, bundle: Bundle?, callback: ActionProcessor.Callback) {
        val event = PrintExtraRequiredEvent.create(bundle) ?: run {
            callback.skip()
            return
        }
        call(action, event, callback)
    }

    abstract fun call(action: String, event: PrintExtraRequiredEvent, callback: ActionProcessor.Callback)

}