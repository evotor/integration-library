package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.Utils
import ru.evotor.framework.optInt
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.PositionTable
import ru.evotor.framework.receipt.attribute.VeterinaryAttribute

internal object VeterinaryAttributeMapper {

    private const val KEY_VETERINARY_ATTRIBUTE_TYPE = "VeterinaryAttributeType"
    private const val KEY_VETERINARY_ATTRIBUTE_DOCUMENT_DATE_VALUE = "VeterinaryAttributeDocumentDateValue"
    private const val KEY_VETERINARY_ATTRIBUTE_DOCUMENT_NUMBER_VALUE = "VeterinaryAttributeDocumentNumberValue"
    private const val KEY_VETERINARY_ATTRIBUTE_DOCUMENT_VERSION = "VeterinaryAttributeVersion"

    internal fun readFromCursor(cursor: Cursor): VeterinaryAttribute? = cursor.optInt(PositionTable.COLUMN_VETERINARY_ATTRIBUTE)?.let {
        VeterinaryAttribute(
            type = VeterinaryAttribute.VeterinaryDocumentType.values()[it],
            documentNumber = cursor.optString(PositionTable.COLUMN_VETERINARY_ATTRIBUTE_DOCUMENT_NUMBER),
            documentDate = cursor.optString(PositionTable.COLUMN_VETERINARY_ATTRIBUTE_DOCUMENT_DATE)
        )
    }

    fun readFromBundle(bundle: Bundle?): VeterinaryAttribute? = bundle?.let {
        val type = it.getString(KEY_VETERINARY_ATTRIBUTE_TYPE)
        if (type.isNullOrEmpty()) {
            return@let null
        }
        VeterinaryAttribute(
            type = Utils.safeValueOf(
                VeterinaryAttribute.VeterinaryDocumentType::class.java,
                type,
                VeterinaryAttribute.VeterinaryDocumentType.VPR
            ),
            documentNumber = it.getString(PositionTable.COLUMN_VETERINARY_ATTRIBUTE_DOCUMENT_NUMBER),
            documentDate = it.getString(PositionTable.COLUMN_VETERINARY_ATTRIBUTE_DOCUMENT_DATE)
        )
    }

    fun writeToBundle(veterinaryAttribute: VeterinaryAttribute) = Bundle().apply {
        this.putInt(KEY_VETERINARY_ATTRIBUTE_DOCUMENT_VERSION, VeterinaryAttribute.VERSION)
        this.putString(KEY_VETERINARY_ATTRIBUTE_TYPE, veterinaryAttribute.type.name)
        this.putString(KEY_VETERINARY_ATTRIBUTE_DOCUMENT_DATE_VALUE, veterinaryAttribute.documentDate)
        this.putString(KEY_VETERINARY_ATTRIBUTE_DOCUMENT_NUMBER_VALUE, veterinaryAttribute.documentNumber)
    }
}
