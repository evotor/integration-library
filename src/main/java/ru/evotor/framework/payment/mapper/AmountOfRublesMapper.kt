package ru.evotor.framework.payment.mapper

import android.database.Cursor
import ru.evotor.framework.core.safeGetBigDecimal
import ru.evotor.framework.payment.AmountOfRubles
import java.math.BigDecimal

internal object AmountOfRublesMapper {
    fun read(cursor: Cursor, columnName: String): AmountOfRubles? {
        return AmountOfRubles(
                value = cursor.safeGetBigDecimal(columnName)
                        ?.divide(BigDecimal(100))
                        ?.let { if (it > BigDecimal(0)) it else return null }
                        ?: return null
        )
    }
}