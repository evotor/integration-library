package ru.evotor.framework.core.action.broadcast

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.annotation.RequiresPermission
import ru.evotor.framework.core.action.event.scanner.BarcodeReceivedEvent

/**
 * Широковещательный приёмник событий сканера штрихкодов.
 * @see <a href="https://developer.evotor.ru/docs/beta/doc_java_barcode_scanner.html">Использование широковещательного приёмника</a>
 */
abstract class ScannerBroadcastReceiver : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий получения штрихкода.
     */
    @RequiresPermission(RECEIVER_PERMISSION)
    @RequiresIntentAction(ACTION_BARCODE_RECEIVED)
    protected abstract fun handleBarcodeReceivedEvent(context: Context, barcodeReceivedEvent: BarcodeReceivedEvent)

    @SuppressLint("MissingPermission")
    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        handleBarcodeReceivedEvent(context, BarcodeReceivedEvent.from(bundle) ?: return)
    }

    companion object {

        const val ACTION_BARCODE_RECEIVED = "ru.evotor.devices.ScannedCode"

        const val SENDER_PERMISSION = "ru.evotor.devices.SCANNER_SENDER"

        const val RECEIVER_PERMISSION = "ru.evotor.devices.SCANNER_RECEIVER"

    }

}