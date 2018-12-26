package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.optString
import ru.evotor.framework.receipt.PositionTable
import ru.evotor.framework.receipt.position.SettlementMethod
import ru.evotor.framework.safeGetInt
import java.math.BigDecimal

internal object SettlementMethodMapper {

    private const val KEY_FullPrepayment = 0
    private const val KEY_PartialPrepayment = 1
    private const val KEY_AdvancePayment = 2
    private const val KEY_FullSettlement = 3
    private const val KEY_PartialSettlement = 4
    private const val KEY_Lend = 5
    private const val KEY_LoanPayment = 6

    internal fun fromCursor(cursor: Cursor): SettlementMethod {
        val typeOrdinal = cursor.safeGetInt(PositionTable.COLUMN_SETTLEMENT_METHOD)
                ?: return SettlementMethod.FullSettlement()
        val amountValue = cursor.optString(PositionTable.COLUMN_SETTLEMENT_METHOD_AMOUNT)
        val amount = if (amountValue == null) null else BigDecimal(amountValue)
        return SettlementMethodMapper.fromInt(typeOrdinal, amount)
    }

    private fun fromInt(type: Int, amount: BigDecimal? = null): SettlementMethod =
            when (type) {
                KEY_FullPrepayment -> SettlementMethod.FullPrepayment()
                KEY_PartialPrepayment -> SettlementMethod.PartialPrepayment()
                KEY_AdvancePayment -> SettlementMethod.AdvancePayment()
                KEY_FullSettlement -> SettlementMethod.FullSettlement()
                KEY_PartialSettlement -> SettlementMethod.PartialSettlement(amount!!)
                KEY_Lend -> SettlementMethod.Lend()
                KEY_LoanPayment -> SettlementMethod.LoanPayment()
                else -> SettlementMethod.FullSettlement()
            }

}