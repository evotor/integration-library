package ru.evotor.framework.core

import android.content.Context
import android.os.Handler
import android.os.Looper
import ru.evotor.IBundlable

internal fun Context.startIntegrationService(action: String, event: IBundlable, callback: IntegrationManagerCallback) =
        IntegrationManagerImpl
                .convertImplicitIntentToExplicitIntent(action, this)
                ?.takeIf {
                    it.isNotEmpty()
                }
                ?.let { componentNameList ->
                    IntegrationManagerImpl(this)
                            .call(action,
                                    componentNameList[0],
                                    event,
                                    ICanStartActivity { this.startActivity(it) },
                                    callback,
                                    Handler(Looper.getMainLooper())
                            )
                }