package ru.evotor.framework

import java.math.BigDecimal

/**
 * Returns the sum of all values produced by [selector] function applied to each element in the collection.
 */
inline fun <T> Iterable<T>.sumByBigDecimal(selector: (T) -> BigDecimal): BigDecimal {
    var sum = BigDecimal.ZERO
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

inline fun <reified T : kotlin.Enum<T>> safeValueOf(type: String?): T? {
    if (type == null) {
        return null
    }

    try {
        return java.lang.Enum.valueOf(T::class.java, type)
    } catch (e: IllegalArgumentException) {
        return null
    }
}

fun min(a: BigDecimal, b: BigDecimal) = if (a <= b) a else b