package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent
import ru.evotor.framework.receipt.Position

/**
 * Событие, которое приходит после сканирования штрихкода товара.
 *
 * Штрихкод может быть любого формата (EAN-13, QR-код, DataMatrix или другой) и, кроме цифрового значения, содержать различные данные, например, вес товара.
 *
 * Обрабатывая данные, содержащиеся в событии, приложения могут добавлять позиции в чек возврата
 *
 * @property barcode строка данных, полученных от сканера штрихкодов.
 */
class ReturnPositionsForBarcodeRequestedPaybackEvent(
        val barcode: String
) : IntegrationEvent() {

    override fun toBundle() = Bundle().apply {
        putString(KEY_BARCODE_EXTRA, barcode)
    }

    companion object {
        const val KEY_BARCODE_EXTRA = "key_barcode_extra"

        @JvmStatic
        fun from(bundle: Bundle?) = bundle?.let {
            ReturnPositionsForBarcodeRequestedPaybackEvent(
                    barcode = it.getString(KEY_BARCODE_EXTRA)
                            ?: return null)
        }
    }

    /**
     * Результат обработки события сканирования штрихкода.
     * @property positions список позиций, которые будут отображены пользователю для выбора, будет добавлена только одна
     */
    data class Result(
            val positions: List<Position>
    ) : IntegrationEvent.Result() {

        override fun toBundle() = Bundle().apply {
            classLoader = Position::class.java.classLoader
            putInt(KEY_EXTRA_POSITIONS_COUNT, positions.size)
            if (!positions.isNullOrEmpty()) {
                for (i in positions.indices) {
                    putParcelable(KEY_EXTRA_POSITIONS + i, positions[i])
                }
            }
        }

        companion object {
            private const val KEY_EXTRA_POSITIONS = "extra_position_"
            private const val KEY_EXTRA_POSITIONS_COUNT = "extra_positions_count"

            @JvmStatic
            fun from(bundle: Bundle?) = bundle?.let {
                it.classLoader = Position::class.java.classLoader
                val count = it.getInt(KEY_EXTRA_POSITIONS_COUNT)
                val positions = mutableListOf<Position>()

                for (i in 0 until count) {
                    positions.add(it.getParcelable(KEY_EXTRA_POSITIONS + i) ?: return null)
                }

                Result(positions)
            }
        }
    }
}
