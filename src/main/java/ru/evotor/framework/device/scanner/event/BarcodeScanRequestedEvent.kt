package ru.evotor.framework.device.scanner.event

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent

class BarcodeScanRequestedEvent : IntegrationEvent() {
    override fun toBundle() = Bundle()

    class Result(val barcode: String) : IntegrationEvent.Result() {

        override fun toBundle() = Bundle().apply {
            putString(KEY_BARCODE, barcode)
        }

        companion object {
            private const val KEY_BARCODE = "BARCODE"

            @JvmStatic
            fun from(bundle: Bundle?) = bundle?.let {
                Result(it.getString(KEY_BARCODE) ?: return@let null)
            }
        }
    }

    companion object {

        @JvmStatic
        fun from(bundle: Bundle?) = bundle?.let {
            BarcodeScanRequestedEvent()
        }
    }
}
