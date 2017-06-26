package ru.evotor.framework.inventory.field

class DictionaryField(
        name: String?,
        fieldUUID: String,
        title: String?,
        val multiple: Boolean,
        val items: Array<Item>
) : Field(
        name,
        fieldUUID,
        title,
        Type.DICTIONARY_FIELD
) {
    class Item(
            val title: String,
            val value: Any,
            val data: Any?
    )
}