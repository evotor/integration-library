package ru.evotor.framework

import org.apache.commons.lang3.StringUtils

fun android.database.Cursor.optInt(columnName: String): Int? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optInt(index)
}

fun android.database.Cursor.optInt(columnIndex: Int): Int? {
    if (isNull(columnIndex)) {
        return null
    }

    return getInt(columnIndex)
}

fun android.database.Cursor.optList(columnName: String): List<String>? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optList(index)
}

private const val LIST_SEPARATOR = ","

fun android.database.Cursor.optList(columnIndex: Int): List<String>? {
    if (isNull(columnIndex)) {
        return null
    }

    return getString(columnIndex).let {
        StringUtils.split(it, LIST_SEPARATOR)?.toList()
    }
}