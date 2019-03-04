package ru.evotor.framework.core

import android.os.Bundle

internal const val DEFAULT_INT_VALUE = -1

internal fun <T : Enum<*>> Bundle.getEnum(key: String, values: Array<T>): T? =
        this.getInt(key, DEFAULT_INT_VALUE).let { int ->
            if (int in 0..values.size) values[int] else null
        }