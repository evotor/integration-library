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
                onBarcodeReceived(code, context)
            }
        }
    }

    public abstract fun onBarcodeReceived(barcode: String, context: Context?)


    companion object {

        @JvmField
        public val ACTION_SCANNED = "ru.evotor.devices.ScannedCode"

        @JvmField
        public val EXTRA_SCANNED_CODE = "ScannedCode"

        @JvmField
        public val SENDER_PERMISSION = "ru.evotor.devices.SCANNER_SENDER"

        @JvmField
        public val RECEIVER_PERMISSION = "ru.evotor.devices.SCANNER_RECEIVER"

        @JvmField
        public val BARCODE_INTENT_FILTER = IntentFilter(ACTION_SCANNED)

    }

}