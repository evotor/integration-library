package ru.evotor.framework.payment.mapper

import android.database.Cursor
import ru.evotor.framework.core.safeGetBigDecimal
import ru.evotor.framework.payment.AmountOfRubles
import java.math.BigDecimal

internal object AmountOfRublesMapper {
    private const val SCALE = 2

    val converter = { it: AmountOfRubles? ->
        it?.multiply(BigDecimal(100))?.setScale(0)?.longValueExact() ?: 0
    }

    fun getInitialValue(value: BigDecimal): BigDecimal = value.setScale(SCALE)

    fun read(cursor: Cursor, column: String): AmountOfRubles? {
        return AmountOfRubles(
                value = cursor.safeGetBigDecimal(column)
                        ?.divide(BigDecimal(100))
                        ?.let { if (it > BigDecimal(0)) it else return null }
                        ?: return null
        )
    }
}