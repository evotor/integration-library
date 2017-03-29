package ru.evotor.integrations.inventory.field

class DictionaryField(
        name: String?,
        fieldUUID: String,
        title: String?,
        type: Type,
        val multiple: Boolean,
        val items: Array<Item>
) : Field(
        name,
        fieldUUID,
        title,
        type
) {
    class Item(
            val title: String,
            val value: Any,
            val data: Any?
    )
}