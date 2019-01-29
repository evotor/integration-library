package ru.evotor.framework

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal fun android.database.Cursor.safeGetBoolean(columnName: String): Boolean? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return safeGetBoolean(index)
}

internal fun android.database.Cursor.safeGetBoolean(columnIndex: Int): Boolean? {
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

internal fun android.database.Cursor.safeGetInt(columnName: String): Int? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return safeGetInt(index)
}

internal fun android.database.Cursor.safeGetInt(columnIndex: Int): Int? {
    if (isNull(columnIndex)) {
        return null
    }

    return getInt(columnIndex)
}

internal fun android.database.Cursor.safeGetList(columnName: String): List<String>? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return safeGetList(index)
}

internal fun android.database.Cursor.safeGetList(columnIndex: Int): List<String>? {
    if (isNull(columnIndex)) {
        return null
    }

    return getString(columnIndex)?.let { Gson().fromJson(it, object : TypeToken<List<String>>() {}.type) }

}

internal fun <T : Enum<*>> android.database.Cursor.safeGetEnum(columnName: String, values: Array<T>): T? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return safeGetEnum(index, values)
}

internal fun <T : Enum<*>> android.database.Cursor.safeGetEnum(columnIndex: Int, values: Array<T>): T? {
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