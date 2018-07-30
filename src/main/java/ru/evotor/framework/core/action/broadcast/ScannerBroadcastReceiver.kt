package ru.evotor.framework.core.action.broadcast

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.annotation.RequiresPermission

/**
 * Широковещательный приёмник событий сканера штрихкодов.
 * @see <a href="https://developer.evotor.ru/docs/tobi_pizda">Использование широковещательного приёмника</a>
 */
abstract class ScannerBroadcastReceiver : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий получения штрихкода.
     */
    @RequiresPermission(RECEIVER_PERMISSION)
    @RequiresIntentAction(ACTION_BARCODE_RECEIVED)
    protected abstract fun handleBarcodeReceivedEvent(context: Context, barcode: String)

    @SuppressLint("MissingPermission")
    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        bundle.getString(KEY_SCANNED_CODE)?.let {
            if (it.isNotEmpty()) {
                handleBarcodeReceivedEvent(context, it)
            }
        }
    }

    companion object {

        const val ACTION_BARCODE_RECEIVED = "ru.evotor.devices.ScannedCode"

        const val SENDER_PERMISSION = "ru.evotor.devices.SCANNER_SENDER"

        const val RECEIVER_PERMISSION = "ru.evotor.devices.SCANNER_RECEIVER"

        const val KEY_SCANNED_CODE = "ScannedCode"

    }

}