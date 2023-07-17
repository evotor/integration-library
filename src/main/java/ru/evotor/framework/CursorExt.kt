package ru.evotor.framework

import android.database.Cursor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.math.BigDecimal

internal fun Cursor.optBoolean(columnName: String): Boolean? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optBoolean(index)
}

internal fun Cursor.optBoolean(columnIndex: Int): Boolean? {
    if (isNull(columnIndex)) {
        return null
    }

    return getInt(columnIndex).let { int ->
        when (int) {
            0 -> false
            1 -> true
            else -> null
        }
    }
}

/**
 * Возвращает целочисленное значение из указанного столбца по его имени.
 * Так же выполняется проверка наличия столбца.
 *
 * @param columnName Имя столбца, из которого необходимо получить значение.
 * @return Целочисленное значение из столбца или null, если столбец не существует.
 */
internal fun Cursor.optInt(columnName: String): Int? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optInt(index)
}

/**
 * Возвращает целочисленное значение из указанного столбца по его индексу.
 * Если столбца не существует [android.database.Cursor.isNull] выдаст ошибку CursorIndexOutOfBoundsException: Requested column: -1
 *
 * @param columnIndex Индекс столбца, из которого необходимо получить значение.
 * @return Целочисленное значение из столбца или null, если значение является null.
 */
internal fun Cursor.optInt(columnIndex: Int): Int? {
    if (isNull(columnIndex)) {
        return null
    }

    return getInt(columnIndex)
}

internal fun Cursor.optLong(columnName: String): Long? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optLong(index)
}

internal fun Cursor.optLong(columnIndex: Int): Long? {
    if (isNull(columnIndex)) {
        return null
    }

    return getLong(columnIndex)
}

internal fun Cursor.optList(columnName: String): List<String>? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optList(index)
}

internal fun Cursor.optList(columnIndex: Int): List<String>? {
    if (isNull(columnIndex)) {
        return null
    }

    return getString(columnIndex)?.let { Gson().fromJson(it, object : TypeToken<List<String>>() {}.type) }

}

internal fun <T : Enum<*>> Cursor.optEnum(columnName: String, values: Array<T>): T? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optEnum(index, values)
}

internal fun <T : Enum<*>> Cursor.optEnum(columnIndex: Int, values: Array<T>): T? {
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

internal fun Cursor.optString(columnName: String): String? {
    val index = getColumnIndex(columnName)
    if (index == -1) {
        return null
    }

    return optString(index)
}

internal fun Cursor.optString(columnIndex: Int): String? {
    if (isNull(columnIndex)) {
        return null
    }

    return getString(columnIndex)
}

internal fun Cursor.getQuantity(columnName: String): BigDecimal {
    val index = getColumnIndex(columnName)
    return formatQuantity(getLong(index))
}

internal fun Cursor.optQuantity(columnName: String): BigDecimal? {
    val quantityValueLong = this.optLong(columnName) ?: return null
    return formatQuantity(quantityValueLong)
}

internal fun Cursor.getVolume(columnName: String): BigDecimal {
    val index = getColumnIndex(columnName)
    return formatVolume(getLong(index))
}

internal fun Cursor.optVolume(columnName: String): BigDecimal? {
    val volumeValueLong = this.optLong(columnName) ?: return null
    return formatVolume(volumeValueLong)
}

internal fun Cursor.getMoney(columnName: String): BigDecimal {
    val index = getColumnIndex(columnName)
    return formatMoney(getLong(index))
}

internal fun Cursor.optMoney(columnName: String): BigDecimal? {
    val moneyValueLong = this.optLong(columnName) ?: return null
    return formatMoney(moneyValueLong)
}

private fun formatQuantity(quantity: Long): BigDecimal {
    return BigDecimal(quantity).divide(BigDecimal(1000))
}

private fun formatVolume(volume: Long): BigDecimal {
    return BigDecimal(volume).divide(BigDecimal(1000))
}

private fun formatMoney(money: Long): BigDecimal {
    return BigDecimal(money).divide(BigDecimal(100))
}
