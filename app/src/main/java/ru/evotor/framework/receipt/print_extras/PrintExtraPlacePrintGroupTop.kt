package ru.evotor.framework.receipt.print_extras

import android.os.Bundle

/**
 * Печатная информация будет добавлена сверху от чека: после клише, до текста "Кассовый чек"
 */
class PrintExtraPlacePrintGroupTop(
        val printGroupId: String?
) : PrintExtraPlace(PrintExtraPlaceType.PRINT_GROUP_TOP) {

    companion object {
        val KEY_PRINT_GROUP_ID = "printGroupId"

        fun addToBundle(bundle: Bundle, place: PrintExtraPlacePrintGroupTop): Bundle =
                bundle.apply { putString(KEY_PRINT_GROUP_ID, place.printGroupId) }

        fun fromBundle(bundle: Bundle): PrintExtraPlacePrintGroupTop? =
                PrintExtraPlacePrintGroupTop(bundle.getString(KEY_PRINT_GROUP_ID))
    }
}
