package ru.evotor.integrations

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import java.lang.NullPointerException

/**
 * Created by nixan on 28.04.17.
 */

private const val ACTION_SCANNED = "ru.evotor.devices.ScannedCode"
private const val EXTRA_SCANNED_CODE = "ScannedCode"

class BarcodeBroadcastReceiver : BroadcastReceiver() {

    private val scannerListeners = arrayListOf<BarcodeScannerListener>()

    fun register(activity: Activity?) {
        if (activity == null) {
            throw NullPointerException("Activity should not be null")
        }
        activity.registerReceiver(this, IntentFilter(ACTION_SCANNED))
    }

    fun unregister(activity: Activity?) {
        if (activity == null) {
            throw NullPointerException("Activity should not be null")
        }
        activity.unregisterReceiver(this)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.extras?.getString(EXTRA_SCANNED_CODE)?.let { code ->
            if (code.isNotEmpty()) {
                scannerListeners.forEach { it.barcodeScanned(code) }
            }
        }
    }

}

interface BarcodeScannerListener {
    fun barcodeScanned(barcode: String)
}