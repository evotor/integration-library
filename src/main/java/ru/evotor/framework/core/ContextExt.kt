package ru.evotor.framework.core

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable

internal fun Context.startIntegrationService(
        action: String,
        event: IBundlable,
        callback: IntegrationManagerCallback
) = this.packageManager
        .queryIntentServices(Intent(action), 0)
        ?.takeIf { it.isNotEmpty() }
        ?.first()
        ?.let {
            ComponentName(it.serviceInfo.packageName, it.serviceInfo.name)
        }
        ?.let { componentName ->
            IntegrationManagerImpl(this).call(
                    action,
                    componentName,
                    event,
                    ICanStartActivity { this.startActivity(it) },
                    callback,
                    Handler(Looper.getMainLooper())
            )
        }