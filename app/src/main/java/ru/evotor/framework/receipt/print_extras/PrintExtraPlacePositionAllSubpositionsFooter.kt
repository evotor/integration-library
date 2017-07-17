package ru.evotor.framework.receipt.print_extras

import android.os.Bundle

/**
 * Печатная информация будет добавлена после позиции в чеке и после всех её модификаторов (подпозиций)
 */
class PrintExtraPlacePositionAllSubpositionsFooter(
        val positionUuid: String?
) : PrintExtraPlace(PrintExtraPlaceType.POSITION_ALL_SUBPOSITIONS_FOOTER) {

    companion object {
        val KEY_POSITION_UUID = "positionUuid"

        fun addToBundle(bundle: Bundle, place: PrintExtraPlacePositionAllSubpositionsFooter): Bundle =
                bundle.apply { putString(KEY_POSITION_UUID, place.positionUuid) }

        fun fromBundle(bundle: Bundle): PrintExtraPlacePositionAllSubpositionsFooter? =
                bundle.getString(KEY_POSITION_UUID)?.let {
                    PrintExtraPlacePositionAllSubpositionsFooter(it)
                }
    }
}
