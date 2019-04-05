package ru.evotor.framework.common.event.handler.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.evotor.framework.common.event.IntegrationEvent
import ru.evotor.framework.core.IntegrationManager
import ru.evotor.framework.core.IntegrationResponse

abstract class IntegrationActivityV2 : AppCompatActivity() {

    private var response: IntegrationResponse? = null
    private var result: IntegrationEvent.Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getBundleExtra(IntegrationManager.KEY_INTENT_DATA)?.let {
            response = it.getParcelable(IntegrationManager.KEY_INTEGRATION_RESPONSE)
            IntegrationEvent
                    .from(it.getParcelable(IntegrationManager.KEY_SOURCE_DATA))
                    ?.let { onCreate(it) }
        }
    }

    abstract fun onCreate(event: IntegrationEvent)

    fun setIntegrationResult(result: IntegrationEvent.Result) {
        this.result = result
    }

    override fun finish() {
        response?.let { response ->
            result?.let { result ->
                response.onResult(Bundle().apply {
                    putBundle(IntegrationManager.KEY_DATA, result.toBundle())
                })
            }
            this.response = null
        }
        super.finish()
    }
}