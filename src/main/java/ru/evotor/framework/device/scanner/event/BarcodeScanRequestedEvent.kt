package ru.evotor.framework.device.scanner.event

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent

class BarcodeScanRequestedEvent : IntegrationEvent() {

    class Result(val barcode: String) : IntegrationEvent.Result() {

        override fun toBundle() = Bundle().apply {
            putString(KEY_BARCODE, barcode)
        }
    }

    companion object {

        private const val KEY_BARCODE = "BARCODE"

        fun from(bundle: Bundle?) = bundle?.let {
            BarcodeScanRequestedEvent()
        }
    }
}