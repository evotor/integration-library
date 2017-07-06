package ru.evotor.framework.receipt.print_extras

import android.os.Bundle

class PrintExtraPlacePrintGroupHeader(
        val printGroupId: String?
) : PrintExtraPlace(PrintExtraPlaceType.PRINT_GROUP_HEADER) {

    companion object {
        val KEY_PRINT_GROUP_ID = "printGroupId"

        fun addToBundle(bundle: Bundle, place: PrintExtraPlacePrintGroupHeader): Bundle =
                bundle.apply { putString(KEY_PRINT_GROUP_ID, place.printGroupId) }

        fun fromBundle(bundle: Bundle): PrintExtraPlacePrintGroupHeader? =
                bundle.getString(KEY_PRINT_GROUP_ID)?.let {
                    PrintExtraPlacePrintGroupHeader(it)
                }
    }
}
