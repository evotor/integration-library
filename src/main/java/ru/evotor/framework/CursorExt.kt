package ru.evotor.framework

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal fun android.database.Cursor.optInt(columnName: String): Int? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optInt(index)
}

internal fun android.database.Cursor.optInt(columnIndex: Int): Int? {
    if (isNull(columnIndex)) {
        return null
    }

    return getInt(columnIndex)
}

internal fun android.database.Cursor.optList(columnName: String): List<String>? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optList(index)
}

internal fun android.database.Cursor.optList(columnIndex: Int): List<String>? {
    if (isNull(columnIndex)) {
        return null
    }

    return getString(columnIndex)?.let { Gson().fromJson(it, object : TypeToken<List<String>>() {}.type) }

}

internal fun <T : Enum<*>> android.database.Cursor.optEnum(columnName: String, values: Array<T>): T? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optEnum(index, values)
}

internal fun <T : Enum<*>> android.database.Cursor.optEnum(columnIndex: Int, values: Array<T>): T? {
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