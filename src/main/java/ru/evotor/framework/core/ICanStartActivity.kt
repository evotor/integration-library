package ru.evotor.framework.core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import java.lang.ref.WeakReference

/**
 * Created by a.kuznetsov on 26/04/2017.
 */
interface ICanStartActivity {
    fun startActivity(intent: Intent)
    fun startActivity(intent: Intent, options: Bundle?)
}

open class ActivityStarter(
    context: Context,
    private val isNewTask: Boolean = true
) : ICanStartActivity {

    private val contextRef = WeakReference(context)
    override fun startActivity(intent: Intent) {
        startActivity(intent, null)
    }

    override fun startActivity(intent: Intent, options: Bundle?) {
        if (isNewTask) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        contextRef.get()?.startActivity(intent, options)
            ?: throw IllegalStateException("Context has been deleted by garbage collector")
    }
}
