package ru.evotor.framework.receipt.print_extras

import android.os.Bundle

class PrintExtraPlacePrintGroupSummary(
        val printGroupId: String?
) : PrintExtraPlace(PrintExtraPlaceType.PRINT_GROUP_SUMMARY) {

    companion object {
        val KEY_PRINT_GROUP_ID = "printGroupId"

        fun addToBundle(bundle: Bundle, place: PrintExtraPlacePrintGroupSummary): Bundle =
                bundle.apply { putString(KEY_PRINT_GROUP_ID, place.printGroupId) }

        fun fromBundle(bundle: Bundle): PrintExtraPlacePrintGroupSummary? =
                bundle.getString(KEY_PRINT_GROUP_ID)?.let {
                    PrintExtraPlacePrintGroupSummary(it)
                }
    }
}