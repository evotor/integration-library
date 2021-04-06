package ru.evotor.framework

import java.math.BigDecimal

/**
 * Returns the sum of all values produced by [selector] function applied to each element in the collection.
 */
@Deprecated("Это внутренний метод. Он не должен быть публичным", level = DeprecationLevel.ERROR)
inline fun <T> Iterable<T>.sumByBigDecimal(selector: (T) -> BigDecimal): BigDecimal {
    var sum = BigDecimal.ZERO
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

@Deprecated("Это внутренний метод. Он не должен быть публичным", level = DeprecationLevel.ERROR)
fun BigDecimal.isEquals(value: BigDecimal): Boolean = this.compareTo(value) == 0

@Deprecated("Это внутренний метод. Он не должен быть публичным", level = DeprecationLevel.ERROR)
fun BigDecimal.isGreaterThan(value: BigDecimal): Boolean = this.compareTo(value) > 0

@Deprecated("Это внутренний метод. Он не должен быть публичным", level = DeprecationLevel.ERROR)
fun BigDecimal.isLessThan(value: BigDecimal): Boolean = this.compareTo(value) < 0

@Deprecated("Это внутренний метод. Он не должен быть публичным",
        ReplaceWith(expression = "Utils.safeValueOf(T::class.java, type, default)", imports = ["ru.evotor.framework.Utils"]),
        DeprecationLevel.ERROR)
inline fun <reified T : kotlin.Enum<T>> safeValueOf(type: String?, default: T? = null): T? {
    if (type == null) {
        return default
    }

    return try {
        java.lang.Enum.valueOf(T::class.java, type)
    } catch (e: IllegalArgumentException) {
        default
    }
}


@Deprecated("Это внутренний метод. Он не должен быть публичным", level = DeprecationLevel.ERROR)
fun min(a: BigDecimal, b: BigDecimal) = if (a <= b) a else b

@Deprecated("Это внутренний метод. Он не должен быть публичным",
        ReplaceWith(expression = "safeGetLong(columnName)", imports = ["ru.evotor.framework.CursorExt"]),
        level = DeprecationLevel.ERROR)
fun android.database.Cursor.optLong(columnName: String): Long? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return safeGetLong(index)
}

@Deprecated("Это внутренний метод. Он не должен быть публичным",
        ReplaceWith(expression = "safeGetLong(columnIndex)", imports = ["ru.evotor.framework.CursorExt"]),
        level = DeprecationLevel.ERROR)
fun android.database.Cursor.optLong(columnIndex: Int): Long? {
    if (isNull(columnIndex)) {
        return null
    }

    return getLong(columnIndex)
}

@Deprecated("Это внутренний метод. Он не должен быть публичным",
        ReplaceWith(expression = "safeGetString(columnName)", imports = ["ru.evotor.framework.CursorExt"]),
        level = DeprecationLevel.ERROR)
fun android.database.Cursor.optString(columnName: String): String? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return safeGetString(index)
}

@Deprecated("Это внутренний метод. Он не должен быть публичным",
        ReplaceWith(expression = "safeGetString(columnIndex)", imports = ["ru.evotor.framework.CursorExt"]),
        level = DeprecationLevel.ERROR)
fun android.database.Cursor.optString(columnIndex: Int): String? {
    if (isNull(columnIndex)) {
        return null
    }

    return getString(columnIndex)
}