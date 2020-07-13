package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent
import ru.evotor.framework.receipt.Position

/**
 * Событие, которое приходит после сканирования штрихкода товара.
 *
 * Штрихкод может быть любого формата (EAN-13, QR-код, DataMatrix или другой) и, кроме цифрового значения, содержать различные данные, например, вес товара.
 *
 * Обрабатывая данные, содержащиеся в событии, приложения могут добавлять позиции в чек и / или создавать новые товары.
 *
 * @property barcode строка данных, полученных от сканера штрихкодов.
 * @property creatingNewProduct указывает на необходимость создать новый товар. Сразу после сканирования штрихкода всегда содержит false.
 * @see <a href="https://developer.evotor.ru/docs/doc_java_return_positions_for_barcode_requested.html">Обработка события сканирования штрихкода</a>
 */
data class ReturnPositionsForBarcodeRequestedEvent(
        val barcode: String,
        val creatingNewProduct: Boolean
) : IntegrationEvent() {

    override fun toBundle() = Bundle().apply {
        putString(KEY_BARCODE_EXTRA, barcode)
        putBoolean(KEY_CREATE_PRODUCT_EXTRA, creatingNewProduct)
    }

    companion object {
        const val KEY_BARCODE_EXTRA = "key_barcode_extra"
        const val KEY_CREATE_PRODUCT_EXTRA = "key_create_product_extra"

        @JvmStatic
        fun from(bundle: Bundle?) = bundle?.let {
            ReturnPositionsForBarcodeRequestedEvent(
                    barcode = it.getString(KEY_BARCODE_EXTRA)
                            ?: return null,
                    creatingNewProduct = it.getBoolean(KEY_CREATE_PRODUCT_EXTRA))
        }
    }

    /**
     * Результат обработки события сканирования штрихкода.
     *
     * @property positionsList список позиций, которые будут добавлны в чек раздельно
     * @property positions список позиций, которые будут добавлены в чек вместе
     * @property iCanCreateNewProduct указывает, будет приложение создавать товар на основе отсканированного штрихкода или нет.
     */
    data class Result(
            val positionsList: List<List<Position>>? = null,
            val positions: List<Position>,
            val iCanCreateNewProduct: Boolean
    ) : IntegrationEvent.Result() {

        constructor(
                positions: List<Position>,
                iCanCreateNewProduct: Boolean
        ) : this(null, positions, iCanCreateNewProduct)

        override fun toBundle() = Bundle().apply {
            classLoader = Position::class.java.classLoader
            putInt(KEY_EXTRA_POSITIONS_COUNT, positions.size)
            if (!positions.isNullOrEmpty()) {
                for (i in positions.indices) {
                    putParcelable(KEY_EXTRA_POSITIONS + i, positions[i])
                }
            }
            if (!positionsList.isNullOrEmpty()) {
                putInt(KEY_EXTRA_POSITIONS_LIST_COUNT, positionsList.size)
                for (i in positionsList.indices) {
                    putInt(KEY_EXTRA_SUB_POSITIONS_COUNT + i, positionsList[i].size)
                    for (j in positionsList[i].indices) {
                        putParcelable(KEY_EXTRA_SUB_POSITIONS_LIST + j, positionsList[i][j])
                    }
                }
            }

            putBoolean(KEY_EXTRA_CAN_CREATE, iCanCreateNewProduct)
        }

        companion object {
            const val KEY_EXTRA_POSITIONS_LIST_COUNT = "extra_positions_list_count"
            const val KEY_EXTRA_POSITIONS = "extra_position_"
            const val KEY_EXTRA_POSITIONS_COUNT = "extra_positions_count"
            const val KEY_EXTRA_SUB_POSITIONS_LIST = "extra_sub_positions_list_"
            const val KEY_EXTRA_SUB_POSITIONS_COUNT = "extra_sub_positions_count"
            const val KEY_EXTRA_CAN_CREATE = "extra_can_create_product"

            @JvmStatic
            fun from(bundle: Bundle?) = bundle?.let {
                it.classLoader = Position::class.java.classLoader
                val count = it.getInt(KEY_EXTRA_POSITIONS_COUNT)
                val positions = mutableListOf<Position>()
                for (i in 0 until count) {
                    positions.add(it.getParcelable(KEY_EXTRA_POSITIONS + i) ?: return null)
                }
                val iCanCreateNewProduct = it.getBoolean(KEY_EXTRA_CAN_CREATE)

                val listCount = it.getInt(KEY_EXTRA_POSITIONS_LIST_COUNT)
                val positionsList = mutableListOf<List<Position>>()
                if (listCount != 0) {
                    for (i in 0 until listCount) {
                        val subListCount = it.getInt(KEY_EXTRA_SUB_POSITIONS_COUNT + i)
                        val subList = mutableListOf<Position>()
                        for (j in 0 until subListCount) {
                            subList.add(
                                    it.getParcelable(KEY_EXTRA_SUB_POSITIONS_LIST + j)
                                            ?: return null
                            )
                        }
                        positionsList.add(subList)
                    }
                }

                Result(positionsList, positions, iCanCreateNewProduct)
            }

        }
    }
}
