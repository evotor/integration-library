package ru.evotor.framework.common.mapper

import android.os.Bundle
import ru.evotor.framework.common.Document
import ru.evotor.framework.safeGetSerializable
import java.util.*

internal object DocumentMapper {
    private const val KEY_UUID = "UUID"

    fun readUuid(bundle: Bundle?): UUID? = bundle?.safeGetSerializable(KEY_UUID)

    fun write(document: Document) = Bundle().apply {
    }
}