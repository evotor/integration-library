package ru.evotor.framework.core.action.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

abstract class AbstractBroadcastReceiver : BroadcastReceiver() {

    lateinit var context: Context
        private set

    final override fun onReceive(p0: Context, p1: Intent) {
        context = p0
        if (p1.action != null && p1.extras != null) {
            onEvent(p1.action, p1.extras)
        }
    }

    protected abstract fun onEvent(action: String, bundle: Bundle)

}