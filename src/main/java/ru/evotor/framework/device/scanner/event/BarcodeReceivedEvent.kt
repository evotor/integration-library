package ru.evotor.framework.device.scanner.event

import android.os.Bundle

import ru.evotor.IBundlable

/**
 * Событие получения штрихкода.
 *
 * Происходит при сканировании штрихкода сканером штрихкодов, подключённым к смарт-терминалу.
 *
 * Обрабатывать это событие можно с помощью широковещательного приёмника событий сканера штрихкодов:
 * [ru.evotor.framework.device.scanner.event.handler.receiver.ScannerBroadcastReceiver]
 *
 * @param barcode отсканированный штрихкод
 */
class BarcodeReceivedEvent(val barcode: String) : IBundlable {

    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_BARCODE, barcode)
        return result
    }

    companion object {

        private const val KEY_BARCODE = "ScannedCode"

        fun from(bundle: Bundle?): BarcodeReceivedEvent? = bundle?.let {
            BarcodeReceivedEvent(it.getString(KEY_BARCODE) ?: return null)
        }

    }

}