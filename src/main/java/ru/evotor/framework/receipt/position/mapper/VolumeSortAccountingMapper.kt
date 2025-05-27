package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.Utils
import ru.evotor.framework.optBigDecimal
import ru.evotor.framework.optQuantity
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.PositionTable
import ru.evotor.framework.receipt.position.VolumeSortAccounting
import ru.evotor.framework.receipt.position.VolumeSortAccounting.Measured
import ru.evotor.framework.receipt.position.VolumeSortAccounting.Piece

internal object VolumeSortAccountingMapper {

    fun toBundle(volumeSortAccounting: VolumeSortAccounting): Bundle {
        val result = Bundle()
        result.putString(CLASS_TYPE_KEY, this::class.java.name)
        when (volumeSortAccounting) {
            is Piece -> {
                volumeSortAccounting.writeToBundle(result)
            }

            is Measured -> {
                volumeSortAccounting.writeToBundle(result)
            }
        }
        return result
    }

    private fun Piece.writeToBundle(bundle: Bundle) {
        bundle.putString(this.gtin, GTIN_KEY)
        bundle.putString(this.type.name, REALIZATION_TYPE_KEY)
    }

    private fun Measured.writeToBundle(bundle: Bundle) {
        bundle.putString(this.gtin, GTIN_KEY)
        bundle.putString(this.quantity.toPlainString(), QUANTITY_KEY)
        bundle.putString(this.type.name, REALIZATION_TYPE_KEY)
    }

    fun fromBundle(bundle: Bundle?): VolumeSortAccounting? {
        return when (bundle?.getString(CLASS_TYPE_KEY)) {
            Piece::class.java.name -> {
                bundle.readVolumeSortAccountingPiece()
            }

            Measured::class.java.name -> {
                bundle.readVolumeSortAccountingMeasured()
            }

            else -> {
                null
            }
        }
    }

    private fun Bundle.readVolumeSortAccountingPiece(): Piece? {
        return Piece(
            gtin = this.getString(GTIN_KEY, null) ?: return null,
            type = Utils.safeValueOf(VolumeSortAccounting.RealizationType::class.java, this.getString(REALIZATION_TYPE_KEY), VolumeSortAccounting.RealizationType.HORECA)
        )
    }

    private fun Bundle.readVolumeSortAccountingMeasured(): Measured? {
        return Measured(
            gtin = this.getString(GTIN_KEY, null) ?: return null,
            quantity = this.optBigDecimal(QUANTITY_KEY) ?: return null,
            type = Utils.safeValueOf(VolumeSortAccounting.RealizationType::class.java, this.getString(REALIZATION_TYPE_KEY), VolumeSortAccounting.RealizationType.HORECA)
        )
    }

    internal fun fromCursor(cursor: Cursor): VolumeSortAccounting? {
        return when(cursor.optString(PositionTable.COLUMN_VOLUME_SORT_ACCOUNTING_CLASS)) {
            Piece::class.java.name -> {
                cursor.readVolumeSortAccountingPiece()
            }

            Measured::class.java.name -> {
                cursor.readVolumeSortAccountingMeasured()
            }

            else -> {
                null
            }
        }
    }

    private fun Cursor.readVolumeSortAccountingPiece(): Piece? {
        return Piece(
            gtin = this.optString(PositionTable.COLUMN_VOLUME_SORT_ACCOUNTING_GTIN) ?: return null,
            type = Utils.safeValueOf(
                VolumeSortAccounting.RealizationType::class.java,
                this.optString(PositionTable.COLUMN_VOLUME_SORT_ACCOUNTING_REALIZATION_TYPE),
                VolumeSortAccounting.RealizationType.HORECA
            )
        )
    }

    private fun Cursor.readVolumeSortAccountingMeasured(): Measured? {
        return Measured(
            gtin = this.optString(PositionTable.COLUMN_VOLUME_SORT_ACCOUNTING_GTIN) ?: return null,
            quantity = this.optQuantity(PositionTable.COLUMN_VOLUME_SORT_ACCOUNTING_QUANTITY) ?: return null,
            type = Utils.safeValueOf(
                VolumeSortAccounting.RealizationType::class.java,
                this.optString(PositionTable.COLUMN_VOLUME_SORT_ACCOUNTING_REALIZATION_TYPE),
                VolumeSortAccounting.RealizationType.HORECA
            )
        )
    }

    private const val CLASS_TYPE_KEY = "CLASS_TYPE_KEY"

    private const val REALIZATION_TYPE_KEY = "REALIZATION_TYPE_KEY"
    private const val GTIN_KEY = "GTIN_KEY"
    private const val QUANTITY_KEY = "QUANTITY_KEY"
}
