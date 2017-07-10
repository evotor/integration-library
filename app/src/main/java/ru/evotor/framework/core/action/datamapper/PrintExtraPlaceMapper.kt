package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.receipt.print_extras.*
import ru.evotor.framework.safeValueOf

object PrintExtraPlaceMapper {

    private const val KEY_TYPE = "type"

    fun toBundle(printExtraPlace: PrintExtraPlace): Bundle =
            Bundle().let {
                it.putString(KEY_TYPE, printExtraPlace.placeType.name)
                when (printExtraPlace) {
                    is PrintExtraPlacePrintGroupTop -> PrintExtraPlacePrintGroupTop.addToBundle(it, printExtraPlace)
                    is PrintExtraPlacePrintGroupHeader -> PrintExtraPlacePrintGroupHeader.addToBundle(it, printExtraPlace)
                    is PrintExtraPlacePrintGroupSummary -> PrintExtraPlacePrintGroupSummary.addToBundle(it, printExtraPlace)
                    is PrintExtraPlacePositionFooter -> PrintExtraPlacePositionFooter.addToBundle(it, printExtraPlace)
                    is PrintExtraPlacePositionAllSubpositionsFooter -> PrintExtraPlacePositionAllSubpositionsFooter.addToBundle(it, printExtraPlace)
                }
                it
            }


    fun fromBundle(bundle: Bundle): PrintExtraPlace? =
            when (safeValueOf<PrintExtraPlaceType>(bundle.getString(KEY_TYPE))) {
                PrintExtraPlaceType.PRINT_GROUP_TOP -> PrintExtraPlacePrintGroupTop.fromBundle(bundle)
                PrintExtraPlaceType.PRINT_GROUP_HEADER -> PrintExtraPlacePrintGroupHeader.fromBundle(bundle)
                PrintExtraPlaceType.PRINT_GROUP_SUMMARY -> PrintExtraPlacePrintGroupSummary.fromBundle(bundle)
                PrintExtraPlaceType.POSITION_FOOTER -> PrintExtraPlacePositionFooter.fromBundle(bundle)
                PrintExtraPlaceType.POSITION_ALL_SUBPOSITIONS_FOOTER -> PrintExtraPlacePositionAllSubpositionsFooter.fromBundle(bundle)
                else -> null
            }

}