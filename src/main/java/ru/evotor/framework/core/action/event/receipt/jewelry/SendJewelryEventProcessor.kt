package ru.evotor.framework.core.action.event.receipt.jewelry

import android.os.Bundle
import ru.evotor.framework.core.action.processor.ActionProcessor

/**
 * Обработчик события [SendJewelryEvent].
 */
abstract class SendJewelryEventProcessor : ActionProcessor() {

    override fun process(action: String, bundle: Bundle?, callback: ActionProcessor.Callback) {
        val event = SendJewelryEvent.from(bundle) ?: run {
            callback.skip()
            return
        }
        when (action) {
            SendJewelryEvent.NAME_SELL_RECEIPT -> sell(action, event, callback)
            SendJewelryEvent.NAME_SELL_CANCEL_RECEIPT -> sellCancel(action, event, callback)
            SendJewelryEvent.NAME_PAYBACK_RECEIPT -> payback(action, event, callback)
            SendJewelryEvent.NAME_PAYBACK_CANCEL_RECEIPT -> paybackCancel(action, event, callback)
            else -> {
                // do nothing
            }
        }
    }

    abstract fun sell(action: String, event: SendJewelryEvent, callback: ActionProcessor.Callback)
    abstract fun sellCancel(action: String, event: SendJewelryEvent, callback: ActionProcessor.Callback)
    abstract fun payback(action: String, event: SendJewelryEvent, callback: ActionProcessor.Callback)
    abstract fun paybackCancel(action: String, event: SendJewelryEvent, callback: ActionProcessor.Callback)

}
