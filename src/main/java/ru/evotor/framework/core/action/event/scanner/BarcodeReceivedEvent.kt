package ru.evotor.framework.core.action.event.scanner

import android.os.Bundle

import ru.evotor.IBundlable

class BarcodeReceivedEvent(val barcode: String) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_BARCODE, barcode)
        return result
    }

    companion object {
        private const val KEY_BARCODE = "ScannedCode"

        fun from(bundle: Bundle): BarcodeReceivedEvent? {
            return BarcodeReceivedEvent(bundle.getString(KEY_BARCODE) ?: return null)
        }
    }
}
