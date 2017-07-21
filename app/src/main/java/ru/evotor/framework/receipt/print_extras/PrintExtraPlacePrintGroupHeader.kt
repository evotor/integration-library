package ru.evotor.framework.receipt.print_extras

import android.os.Bundle

/**
 * Печатная информация будет добавлена в верхней части чека: после текста "Кассовый чек", до имени пользователя
 */
class PrintExtraPlacePrintGroupHeader(
        val printGroupId: String?
) : PrintExtraPlace(PrintExtraPlaceType.PRINT_GROUP_HEADER) {

    companion object {
        val KEY_PRINT_GROUP_ID = "printGroupId"

        fun addToBundle(bundle: Bundle, place: PrintExtraPlacePrintGroupHeader): Bundle =
                bundle.apply { putString(KEY_PRINT_GROUP_ID, place.printGroupId) }

        fun fromBundle(bundle: Bundle): PrintExtraPlacePrintGroupHeader? =
                PrintExtraPlacePrintGroupHeader(bundle.getString(KEY_PRINT_GROUP_ID))
    }
}
