package ru.evotor.framework.device.scanner.event.handler.service

import android.os.Bundle
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.device.scanner.event.BarcodeScanRequestedEvent

abstract class ScannerIntegrationService : IntegrationServiceV2() {

    @RequiresIntentAction(ACTION_BARCODE_SCAN_REQUEST)
    abstract fun handleBarcodeScanRequestedEvent(event: BarcodeScanRequestedEvent): BarcodeScanRequestedEvent.Result?

    override fun onEvent(action: String, bundle: Bundle) = when (action) {
        ACTION_BARCODE_SCAN_REQUEST -> BarcodeScanRequestedEvent.from(bundle)?.let { handleBarcodeScanRequestedEvent(it) }
        else -> null
    }

    companion object {

        const val ACTION_BARCODE_SCAN_REQUEST = "evotor.intent.action.BARCODE_SCAN_REQUEST"
    }
}