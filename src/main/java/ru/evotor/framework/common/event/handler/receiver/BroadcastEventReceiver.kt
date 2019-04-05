package ru.evotor.framework.common.event.handler.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

abstract class BroadcastEventReceiver internal constructor() : BroadcastReceiver() {

    final override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null && intent.extras != null) {
            onEvent(context, intent.action, intent.extras)
        }
    }

    protected abstract fun onEvent(context: Context, action: String, bundle: Bundle)

}