package ru.evotor.framework.core

import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * Created by a.kuznetsov on 26/04/2017.
 */
interface ICanStartActivity {
    fun startActivity(intent: Intent)
    fun startActivity(intent: Intent, options: Bundle?)
}

class ActivityStarter(private val context: Context) : ICanStartActivity {
    override fun startActivity(intent: Intent) {
        startActivity(intent, null)
    }

    override fun startActivity(intent: Intent, options: Bundle?) {
        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}
