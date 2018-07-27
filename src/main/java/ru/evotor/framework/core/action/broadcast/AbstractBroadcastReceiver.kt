package ru.evotor.framework.core.action.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

abstract class AbstractBroadcastReceiver : BroadcastReceiver() {

    final override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null && intent.extras != null) {
            onEvent(context, intent.action, intent.extras)
        }
    }

    protected abstract fun onEvent(context: Context, action: String, bundle: Bundle)

}