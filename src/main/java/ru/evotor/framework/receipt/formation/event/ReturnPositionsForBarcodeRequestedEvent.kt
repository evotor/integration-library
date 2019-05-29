package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.common.event.IntegrationEvent
import ru.evotor.framework.receipt.Position

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

    data class Result(
            val positions: List<Position>,
            val iCanCreateNewProduct: Boolean
    ) : IntegrationEvent.Result() {

        override fun toBundle() = Bundle().apply {
            classLoader = Position::class.java.classLoader
            putInt(KEY_EXTRA_POSITIONS_COUNT, positions.size)
            for (i in 0 until positions.size) {
                putParcelable(KEY_EXTRA_POSITIONS + i, positions[i])
            }
            putBoolean(KEY_EXTRA_CAN_CREATE, iCanCreateNewProduct)
        }

        companion object {
            const val KEY_EXTRA_POSITIONS = "extra_position_"
            const val KEY_EXTRA_POSITIONS_COUNT = "extra_positions_count"
            const val KEY_EXTRA_CAN_CREATE = "extra_can_create_product"

            @JvmStatic
            fun from(bundle: Bundle?) = bundle?.let {
                it.classLoader = Position::class.java.classLoader
                val count = it.getInt(KEY_EXTRA_POSITIONS_COUNT)
                val positions = mutableListOf<Position>()
                for (i in 0 until count) {
                    positions.add(it.getParcelable(KEY_EXTRA_POSITIONS + i))
                }
                val iCanCreateNewProduct = it.getBoolean(KEY_EXTRA_CAN_CREATE)
                Result(positions, iCanCreateNewProduct)
            }

        }
    }
}