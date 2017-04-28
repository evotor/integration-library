package ru.evotor.integrations

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

/**
 * Created by nixan on 28.04.17.
 */


abstract class BarcodeBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.extras?.getString(EXTRA_SCANNED_CODE)?.let { code ->
            if (code.isNotEmpty()) {
                onReceiveBarcode(code)
            }
        }
    }

    abstract fun onReceiveBarcode(barcode: String)

    companion object {

        @JvmStatic
        public val ACTION_SCANNED = "ru.evotor.devices.ScannedCode"

        @JvmStatic
        public val EXTRA_SCANNED_CODE = "ScannedCode"

        @JvmStatic
        public val BARCODE_INTENT_FILTER = IntentFilter(ACTION_SCANNED)
    }

}