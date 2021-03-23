package ru.evotor.framework.core

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

abstract class BroadcastEventReceiver : BroadcastReceiver() {

    final override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        val extras = intent.extras
        if (action != null && extras != null) {
            onEvent(context, action, extras)
        }
    }

    protected abstract fun onEvent(context: Context, action: String, bundle: Bundle)

}