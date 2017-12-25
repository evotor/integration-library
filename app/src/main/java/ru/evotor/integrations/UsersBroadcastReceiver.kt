package ru.evotor.integrations

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by n.churilov on 25.12.2017.
 */

abstract class UsersBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.compareTo(USER_CHANGED) == 0) {
            onUserChanged()
        }
    }

    abstract fun onUserChanged()

    companion object {
        @JvmField
        val USER_CHANGED = "evotor.intent.event.user.CHANGED"
    }
}