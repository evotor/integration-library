package ru.evotor.framework.mapper

import android.database.Cursor
import android.os.Bundle
import ru.evotor.framework.Document
import ru.evotor.framework.optString
import ru.evotor.framework.provider.DocumentContract
import ru.evotor.framework.provider.FiscalDocumentContract
import ru.evotor.framework.safeGetSerializable
import java.util.*

internal object DocumentMapper {
    private const val KEY_UUID = "UUID"

    fun readUuid(bundle: Bundle?): UUID? = bundle?.safeGetSerializable(KEY_UUID)

    fun readUuid(cursor: Cursor): UUID? {
        return UUID.fromString(cursor.optString(DocumentContract.COLUMN_UUID) ?: return null)
    }

    fun write(document: Document) = Bundle().apply {
        this.putSerializable(KEY_UUID, document.uuid)
    }
}