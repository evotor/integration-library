@file:JvmName("BundleUtils")

package ru.evotor.framework

import android.os.Bundle
import ru.evotor.framework.calculator.MoneyCalculator
import ru.evotor.framework.calculator.PercentCalculator
import ru.evotor.framework.calculator.QuantityCalculator
import java.math.BigDecimal

internal const val DEFAULT_LONG_VALUE = -1L

internal const val DEFAULT_INT_VALUE = -1

internal fun Bundle.optBigDecimal(key: String): BigDecimal? {
    val value = getString(key) ?: return null
    return try {
        BigDecimal(value)
    } catch (e: NumberFormatException) {
        null
    }
}

internal fun Bundle.getBigDecimal(key: String): BigDecimal {
    val value = getString(key)
    return BigDecimal(value)
}

internal fun Bundle.getMoney(key: String): BigDecimal? {
    return optBigDecimal(key)?.let { MoneyCalculator.round(it) }
}

internal fun Bundle.getQuantity(key: String): BigDecimal? {
    return optBigDecimal(key)?.let { QuantityCalculator.round(it) }
}

internal fun Bundle.getPercent(key: String): BigDecimal? {
    return optBigDecimal(key)?.let { PercentCalculator.round(it) }
}

internal fun Bundle.optLong(key: String): Long? =
        this.getLong(key, DEFAULT_LONG_VALUE).let { long ->
            if (long != DEFAULT_LONG_VALUE) long else null
        }

internal fun <T : Enum<*>> Bundle.optEnum(key: String, values: Array<T>): T? =
        this.getInt(key, DEFAULT_INT_VALUE).let { int ->
            try {
                values[int]
            } catch (e: IndexOutOfBoundsException) {
                e.printStackTrace()
                null
            }
        }

internal inline fun <reified T> Bundle.optSerializable(key: String): T? =
        this.getSerializable(key)?.let { value -> if (value is T) value else null }

