package ru.evotor.framework

import android.os.Bundle
import kotlin.IndexOutOfBoundsException

internal const val DEFAULT_LONG_VALUE = -1L

internal const val DEFAULT_INT_VALUE = -1

internal fun Bundle.safeGetLong(key: String): Long? =
        this.getLong(key, DEFAULT_LONG_VALUE).let { long ->
            if (long != DEFAULT_LONG_VALUE) long else null
        }

internal fun <T : Enum<*>> Bundle.safeGetEnum(key: String, values: Array<T>): T? =
        this.getInt(key, DEFAULT_INT_VALUE).let { int ->
            try {
                values[int]
            } catch (e: IndexOutOfBoundsException) {
                null
            }
        }

internal inline fun <reified T> Bundle.safeGetSerializable(key: String): T? =
        this.getSerializable(key)?.let { value -> if (value is T) value else null }

