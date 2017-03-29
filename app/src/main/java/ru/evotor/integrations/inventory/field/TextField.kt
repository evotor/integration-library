package ru.evotor.integrations.inventory.field

class TextField(
        name: String?,
        fieldUUID: String,
        title: String?,
        type: Type,
        val data: String?
) : Field(
        name,
        fieldUUID,
        title,
        type
)