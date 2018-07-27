package ru.evotor.framework.core.action.broadcast

import android.content.Context
import android.os.Bundle

/**
 * Класс, получающий широковещательные сообщения о событиях сканера штрихкодов.
 */
abstract class ScannerBroadcastReceiver : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий получения штрихкода.
     */
    protected abstract fun handleBarcodeReceivedEvent(context: Context, barcode: String)

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

        private const val KEY_SCANNED_CODE = "ScannedCode"

    }

}