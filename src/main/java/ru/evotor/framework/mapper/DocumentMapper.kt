package ru.evotor.framework.mapper

import android.os.Bundle
import ru.evotor.framework.Document
import ru.evotor.framework.safeGetSerializable
import java.util.*

internal object DocumentMapper {
    private const val KEY_UUID = "UUID"

    fun readUuid(bundle: Bundle?): UUID? = bundle?.safeGetSerializable(KEY_UUID)

    fun write(document: Document) = Bundle().apply {
    }
}