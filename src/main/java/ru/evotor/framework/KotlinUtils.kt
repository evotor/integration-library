package ru.evotor.framework

import java.math.BigDecimal

/**
 * Returns the sum of all values produced by [selector] function applied to each element in the collection.
 */
@Deprecated("Это внутренний метод. Он не должен быть публичным")
inline fun <T> Iterable<T>.sumByBigDecimal(selector: (T) -> BigDecimal): BigDecimal {
    var sum = BigDecimal.ZERO
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

@Deprecated("Это внутренний метод. Он не должен быть публичным")
fun BigDecimal.isEquals(value: BigDecimal): Boolean = this.compareTo(value) == 0

@Deprecated("Это внутренний метод. Он не должен быть публичным")
fun BigDecimal.isGreaterThan(value: BigDecimal): Boolean = this.compareTo(value) > 0

@Deprecated("Это внутренний метод. Он не должен быть публичным")
fun BigDecimal.isLessThan(value: BigDecimal): Boolean = this.compareTo(value) < 0

@Deprecated("Это внутренний метод. Он не должен быть публичным")
inline fun <reified T : kotlin.Enum<T>> safeValueOf(type: String?, default: T? = null): T? {
    if (type == null) {
        return default
    }

    try {
        return java.lang.Enum.valueOf(T::class.java, type)
    } catch (e: IllegalArgumentException) {
        return default
    }
}

@Deprecated("Это внутренний метод. Он не должен быть публичным")
fun min(a: BigDecimal, b: BigDecimal) = if (a <= b) a else b

@Deprecated("Это внутренний метод. Он не должен быть публичным")
fun android.database.Cursor.optLong(columnName: String): Long? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optLong(index)
}

@Deprecated("Это внутренний метод. Он не должен быть публичным")
fun android.database.Cursor.optLong(columnIndex: Int): Long? {
    if (isNull(columnIndex)) {
        return null
    }

    return getLong(columnIndex)
}

@Deprecated("Это внутренний метод. Он не должен быть публичным")
fun android.database.Cursor.optString(columnName: String): String? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optString(index)
}

@Deprecated("Это внутренний метод. Он не должен быть публичным")
fun android.database.Cursor.optString(columnIndex: Int): String? {
    if (isNull(columnIndex)) {
        return null
    }

    return getString(columnIndex)
}