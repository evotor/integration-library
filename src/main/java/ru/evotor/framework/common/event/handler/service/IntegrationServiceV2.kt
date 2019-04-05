package ru.evotor.framework.common.event.handler.service

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import ru.evotor.IBundlable
import ru.evotor.framework.core.IIntegrationManager
import ru.evotor.framework.core.IIntegrationManagerResponse
import ru.evotor.framework.core.IntegrationManager
import ru.evotor.framework.core.IntegrationResponse

private fun IIntegrationManagerResponse.onEmptyResult() =
        onResult(Bundle().apply { putBoolean(IntegrationManager.KEY_SKIP, true) })

private fun IIntegrationManagerResponse.onResultWithData(data: Bundle) =
        onResult(Bundle().apply { putBundle(IntegrationManager.KEY_DATA, data) })

private fun IIntegrationManagerResponse.onResultWithIntent(sourceData: Bundle?, intent: Intent) =
        onResult(Bundle().apply {
            putParcelable(IntegrationManager.KEY_INTENT, intent.apply {
                putExtra(IntegrationManager.KEY_INTENT_DATA, Bundle().apply {
                    putParcelable(IntegrationManager.KEY_INTEGRATION_RESPONSE, IntegrationResponse(this@onResultWithIntent))
                    putParcelable(IntegrationManager.KEY_SOURCE_DATA, sourceData)
                })
            })
        })

abstract class IntegrationServiceV2 internal constructor() : Service() {

    @Volatile
    private var intentToIntegrationActivity: Intent? = null

    private val binder = object : IIntegrationManager.Stub() {
        @Throws(RemoteException::class)
        override fun call(response: IIntegrationManagerResponse, action: String, bundle: Bundle?) {
            bundle
                    ?.let {
                        onEvent(action, it)
                    }
                    ?.let {
                        response.onResultWithData(it.toBundle())
                    }
                    ?: intentToIntegrationActivity?.let {
                        response.onResultWithIntent(bundle, it)
                    }
                    ?: response.onEmptyResult()
            intentToIntegrationActivity = null
        }
    }

    override fun onBind(intent: Intent): IBinder = binder.asBinder()

    abstract fun onEvent(action: String, bundle: Bundle): IBundlable?

    protected fun startIntegrationActivity(intent: Intent): Nothing? {
        intentToIntegrationActivity = intent
        return null
    }
}