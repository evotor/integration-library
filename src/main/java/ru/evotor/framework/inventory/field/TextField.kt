package ru.evotor.framework.inventory.field

class TextField(
        name: String?,
        fieldUUID: String,
        title: String?,
        val data: String?
) : Field(
        name,
        fieldUUID,
        title,
        Type.TEXT_FIELD
)