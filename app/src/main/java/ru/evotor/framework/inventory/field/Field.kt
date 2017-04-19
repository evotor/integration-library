package ru.evotor.framework.inventory.field

abstract class Field(
        val name: String?,
        val fieldUUID: String,
        val title: String?,
        val type: Type
) {
    enum class Type {
        TEXT_FIELD,
        DICTIONARY_FIELD
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Field

        if (fieldUUID != other.fieldUUID) return false

        return true
    }

    override fun hashCode(): Int {
        return fieldUUID.hashCode()
    }

}