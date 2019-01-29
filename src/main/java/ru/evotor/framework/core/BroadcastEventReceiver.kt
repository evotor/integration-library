package ru.evotor.framework.core

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

abstract class BroadcastEventReceiver : BroadcastReceiver() {

    final override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null && intent.extras != null) {
            onEvent(context, intent.action, intent.extras)
        }
    }

    protected abstract fun onEvent(context: Context, action: String, bundle: Bundle)

}