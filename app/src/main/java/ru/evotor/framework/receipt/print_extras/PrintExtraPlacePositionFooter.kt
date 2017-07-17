package ru.evotor.framework.receipt.print_extras

import android.os.Bundle

/**
 * Печатная информация будет добавлена сразу после позиции в чеке, до списка её модификаторов (подпозиций)
 */
class PrintExtraPlacePositionFooter(
        val positionUuid: String?
) : PrintExtraPlace(PrintExtraPlaceType.POSITION_FOOTER) {

    companion object {
        val KEY_POSITION_UUID = "positionUuid"

        fun addToBundle(bundle: Bundle, place: PrintExtraPlacePositionFooter): Bundle =
                bundle.apply { putString(KEY_POSITION_UUID, place.positionUuid) }

        fun fromBundle(bundle: Bundle): PrintExtraPlacePositionFooter? =
                bundle.getString(KEY_POSITION_UUID)?.let {
                    PrintExtraPlacePositionFooter(it)
                }
    }
}
