package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.receipt.Position

class AddingPositionByBarcodeRequestedEvent(val barcode: String) : IBundlable {

    override fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_BARCODE_EXTRA, barcode)
    }

    companion object {
        const val KEY_BARCODE_EXTRA = "key_barcode_extra"

        fun from(bundle: Bundle?): AddingPositionByBarcodeRequestedEvent? = bundle?.let {
            AddingPositionByBarcodeRequestedEvent(it.getString(KEY_BARCODE_EXTRA) ?: return null)
        }
    }

    data class Result(val positions: List<Position>) : IBundlable {
        constructor(position: Position): this(listOf(position))

        override fun toBundle(): Bundle = Bundle().apply {
            classLoader = Position::class.java.classLoader
            putInt(KEY_EXTRA_POSITIONS_COUNT, positions.size)
            for (i in 0 until positions.size) {
                putParcelable(KEY_EXTRA_POSITIONS + i, positions[i])
            }
        }

        companion object {
            const val KEY_EXTRA_POSITIONS = "extra_position"
            const val KEY_EXTRA_POSITIONS_COUNT = "extra_positions_count"

            fun from(bundle: Bundle?): Result? = bundle?.let {
                it.classLoader = Position::class.java.classLoader
                val count = it.getInt(KEY_EXTRA_POSITIONS_COUNT)
                val positions = mutableListOf<Position>()
                for (i in 0 until count) {
                    positions.add(it.getParcelable(KEY_EXTRA_POSITIONS + i))
                }
                Result(positions)
            }
        }
    }
}