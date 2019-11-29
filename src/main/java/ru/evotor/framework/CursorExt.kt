package ru.evotor.framework

import android.database.Cursor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal fun Cursor.safeGetBoolean(columnName: String): Boolean? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return safeGetBoolean(index)
}

internal fun Cursor.safeGetBoolean(columnIndex: Int): Boolean? {
    if (isNull(columnIndex)) {
        return null
    }

    return getInt(columnIndex).let { int ->
        when(int) {
            0 -> false
            1 -> true
            else -> null
        }
    }
}

internal fun Cursor.safeGetInt(columnName: String): Int? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return safeGetInt(index)
}

internal fun Cursor.safeGetInt(columnIndex: Int): Int? {
    if (isNull(columnIndex)) {
        return null
    }

    return getInt(columnIndex)
}

internal fun Cursor.safeGetLong(columnName: String): Long? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return safeGetLong(index)
}

internal fun Cursor.safeGetLong(columnIndex: Int): Long? {
    if (isNull(columnIndex)) {
        return null
    }

    return getLong(columnIndex)
}

internal fun Cursor.safeGetList(columnName: String): List<String>? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return safeGetList(index)
}

internal fun Cursor.safeGetList(columnIndex: Int): List<String>? {
    if (isNull(columnIndex)) {
        return null
    }

    return getString(columnIndex)?.let { Gson().fromJson(it, object : TypeToken<List<String>>() {}.type) }

}

internal fun <T : Enum<*>> Cursor.safeGetEnum(columnName: String, values: Array<T>): T? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return safeGetEnum(index, values)
}

internal fun <T : Enum<*>> Cursor.safeGetEnum(columnIndex: Int, values: Array<T>): T? {
    if (isNull(columnIndex)) {
        return null
    }

    return getInt(columnIndex).let {
        try {
            values[it]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

}

internal fun Cursor.safeGetString(columnName: String) = getColumnIndex(columnName).let {
    when(it) {
        -1 -> null
        else -> getString(it)
    }
}