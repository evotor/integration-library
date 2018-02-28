package ru.evotor.framework.core.action.datamapper

import android.os.Bundle
import ru.evotor.framework.calculator.MoneyCalculator
import ru.evotor.framework.calculator.PercentCalculator
import ru.evotor.framework.calculator.QuantityCalculator
import java.math.BigDecimal

fun Bundle.getBigDecimal(key: String, default: BigDecimal? = null): BigDecimal? {
    val value = getString(key) ?: return default
    try {
        return BigDecimal(value)
    } catch (e: NumberFormatException) {
        return default
    }
}

fun Bundle.getMoney(key: String, default: BigDecimal? = null) = getBigDecimal(key, default)?.let { MoneyCalculator.round(it) }
fun Bundle.getQuantity(key: String, default: BigDecimal? = null) = getBigDecimal(key, default)?.let { QuantityCalculator.round(it) }

object BundleUtils {
    @JvmStatic
    fun getBigDecimal(bundle: Bundle, key: String, default: BigDecimal? = null) = bundle.getBigDecimal(key, default)

    @JvmStatic
    fun getMoney(bundle: Bundle, key: String) = getMoney(bundle, key, null)

    @JvmStatic
    fun getMoney(bundle: Bundle, key: String, default: BigDecimal? = null)
            = bundle.getBigDecimal(key, default)?.let { MoneyCalculator.round(it) }

    @JvmStatic
    fun getQuantity(bundle: Bundle, key: String) = getQuantity(bundle, key, null)

    @JvmStatic
    fun getQuantity(bundle: Bundle, key: String, default: BigDecimal? = null)
            = bundle.getBigDecimal(key, default)?.let { QuantityCalculator.round(it) }

    @JvmStatic
    fun getPercent(bundle: Bundle, key: String) = getPercent(bundle, key, null)

    @JvmStatic
    fun getPercent(bundle: Bundle, key: String, default: BigDecimal? = null)
            = bundle.getBigDecimal(key, default)?.let { PercentCalculator.round(it) }
}