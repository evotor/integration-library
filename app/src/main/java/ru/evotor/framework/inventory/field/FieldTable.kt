package ru.evotor.framework.inventory.field

import android.net.Uri
import ru.evotor.framework.inventory.InventoryApi

/**
 * Created by a.kuznetsov on 26/03/2017.
 */

object FieldTable {
    val URI = Uri.withAppendedPath(InventoryApi.BASE_URI, "Field")

    const val ROW_NAME = "NAME"
    const val ROW_FIELD_UUID = "FIELD_UUID"
    const val ROW_TITLE = "TITLE"
    const val ROW_TYPE = "TYPE"
    const val ROW_SPECIFIC_DATA = "SPECIFIC_DATA"

    const val TYPE_TEXT_FIELD = 1
    const val TYPE_DICTIONARY = 2
}