package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
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
 * @property extractedData коллекция данных, которые удалось получить из отсканированного штрихкода
 * @property creatingNewProduct указывает на необходимость создать новый товар. Сразу после сканирования штрихкода всегда содержит false.
 * @see <a href="https://developer.evotor.ru/docs/doc_java_return_positions_for_barcode_requested.html">Обработка события сканирования штрихкода</a>
 */
data class ReturnPositionsForBarcodeRequestedEvent(
        val barcode: String,
        val extractedData: ArrayList<DataExtracted>? = null,
        val creatingNewProduct: Boolean
) : IntegrationEvent() {

    override fun toBundle() = Bundle().apply {
        putString(KEY_BARCODE_EXTRA, barcode)
        putParcelableArrayList(KEY_EXTRACTED_DATA_EXTRA, extractedData)
        putBoolean(KEY_CREATE_PRODUCT_EXTRA, creatingNewProduct)
    }

    companion object {
        const val KEY_BARCODE_EXTRA = "key_barcode_extra"
        const val KEY_EXTRACTED_DATA_EXTRA = "key_extracted_data_extra"
        const val KEY_CREATE_PRODUCT_EXTRA = "key_create_product_extra"

        @JvmStatic
        fun from(bundle: Bundle?) = bundle?.let {
            ReturnPositionsForBarcodeRequestedEvent(
                    barcode = it.getString(KEY_BARCODE_EXTRA)
                            ?: return null,
                    extractedData = it.getParcelableArrayList(KEY_EXTRACTED_DATA_EXTRA),
                    creatingNewProduct = it.getBoolean(KEY_CREATE_PRODUCT_EXTRA))
        }
    }

    /**
     * Результат обработки события сканирования штрихкода.
     *
     * @property positionsList список списков позиций, все позиции из одного списка будут добавлены в чек вместе. Используется на версиях ЭвоторПос выше 6.35.0
     * @property positions список позиций, которые будут отображены пользователю для выбора, будет добавлена только одна  (использовать для обратной совместимости)
     * @property iCanCreateNewProduct указывает, будет приложение создавать товар на основе отсканированного штрихкода или нет.
     */
    data class Result(
            val iCanCreateNewProduct: Boolean,
            @Deprecated("""Использовать для обратной совместимости.
                    На старых версиях кассы (с приложением ЭвоторПос ниже 6.35) будут браться значения отсюда,
                    на новых версиях кассы (с приложением ЭвоторПос выше 6.35) будут браться из @property positionsList
                    Будет удалено после обновления рынка.""")
            val positions: List<Position>,
            val positionsList: List<List<Position>>
    ) : IntegrationEvent.Result() {

        @Deprecated("Используйте основной конструктор")
        constructor(
                positions: List<Position>,
                iCanCreateNewProduct: Boolean
        ) : this(iCanCreateNewProduct, positions, emptyList())

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
                        putParcelable("${KEY_EXTRA_SUB_POSITIONS_LIST}_${i}_${j}", positionsList[i][j]) // KEY_EXTRA_SUB_POSITIONS_LIST0
                    }
                }
            }

            putBoolean(KEY_EXTRA_CAN_CREATE, iCanCreateNewProduct)
        }

        companion object {
            private const val KEY_EXTRA_POSITIONS = "extra_position_"
            private const val KEY_EXTRA_POSITIONS_COUNT = "extra_positions_count"
            const val KEY_EXTRA_POSITIONS_LIST_COUNT = "extra_positions_list_count"
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
                                    it.getParcelable("${KEY_EXTRA_SUB_POSITIONS_LIST}_${i}_${j}")
                                            ?: return null
                            )
                        }
                        positionsList.add(subList)
                    }
                }

                Result(iCanCreateNewProduct, positions, positionsList)
            }
        }
    }

    sealed class DataExtracted: Parcelable {

        protected abstract fun writeFieldsToParcel(dest: Parcel, flags: Int)

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            // Determine position in parcel for writing data size
            val dataSizePosition = parcel.dataPosition()
            // Use integer placeholder for data size
            parcel.writeInt(0)
            //Determine position of data start
            val startDataPosition = parcel.dataPosition()

            writeFieldsToParcel(parcel, flags)

            // Calculate data size
            val dataSize = parcel.dataPosition() - startDataPosition
            // Save position at the end of data
            val endOfDataPosition = parcel.dataPosition()
            //Set position to start to write additional data size
            parcel.setDataPosition(dataSizePosition)
            parcel.writeInt(dataSize)
            // Go back to the end of parcel
            parcel.setDataPosition(endOfDataPosition)
        }

        class GS1(val ean: String): DataExtracted() {

            override fun writeFieldsToParcel(dest: Parcel, flags: Int) {
                dest.writeString(ean)
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(VERSION)
                super.writeToParcel(parcel, flags)
            }

            override fun describeContents(): Int = 0

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is GS1) return false

                if (ean != other.ean) return false

                return true
            }

            override fun hashCode(): Int {
                return ean.hashCode()
            }

            companion object {
                private const val VERSION = 1

                @JvmStatic
                private fun readFromParcel(parcel: Parcel): GS1 {
                    val version = parcel.readInt()
                    val dataSize = parcel.readInt()
                    val dataStartPosition = parcel.dataPosition()
                    // read fields here
                    val eanValue = parcel.readString()
                    parcel.setDataPosition(dataStartPosition + dataSize)
                    requireNotNull(eanValue)
                    return GS1(eanValue)
                }

                @JvmField
                val CREATOR: Parcelable.Creator<GS1> = object : Parcelable.Creator<GS1> {
                    override fun createFromParcel(parcel: Parcel): GS1 = readFromParcel(parcel)

                    override fun newArray(size: Int): Array<GS1?> = arrayOfNulls(size)
                }
            }
        }
    }
}
