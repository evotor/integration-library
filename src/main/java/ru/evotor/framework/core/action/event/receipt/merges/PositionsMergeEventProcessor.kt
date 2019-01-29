package ru.evotor.framework.core.action.event.receipt.merges

import android.os.Bundle
import ru.evotor.framework.core.action.processor.ActionProcessor

/**
 * Created by ivan on 26.06.17.
 */

abstract class PositionsMergeEventProcessor : ActionProcessor() {

    override fun process(action: String, bundle: Bundle?, callback: ActionProcessor.Callback) {
        val event = PositionsMergeEvent.create(bundle) ?: run {
            callback.skip()
            return
        }

        call(action, event, callback)
    }

    abstract fun call(action: String, event: PositionsMergeEvent, callback: ActionProcessor.Callback)
}