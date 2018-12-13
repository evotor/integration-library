package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.receipt.position.SettlementMethod
import ru.evotor.framework.receipt.position.provider.SettlementMethodContract
import ru.evotor.framework.safeGetBigDecimal
import ru.evotor.framework.safeGetInt

internal object SettlementMethodMapper {
    fun read(cursor: Cursor) = when (cursor.safeGetInt(SettlementMethodContract.COLUMN_VARIATION_ID)) {
        SettlementMethodContract.VARIATION_ID_FULL_PREPAYMENT -> SettlementMethod.FullPrepayment()
        SettlementMethodContract.VARIATION_ID_PARTIAL_PREPAYMENT -> SettlementMethod.PartialPrepayment()
        SettlementMethodContract.VARIATION_ID_ADVANCE_PAYMENT -> SettlementMethod.AdvancePayment()
        SettlementMethodContract.VARIATION_ID_FULL_SETTLEMENT -> SettlementMethod.FullSettlement()
        SettlementMethodContract.VARIATION_ID_PARTIAL_SETTLEMENT -> SettlementMethod.PartialSettlement(
                cursor.safeGetBigDecimal(SettlementMethodContract.COLUMN_AMOUNT)
                        ?: throw IntegrationLibraryMappingException(
                                SettlementMethod.PartialSettlement::class.java,
                                SettlementMethod.PartialSettlement::amount
                        )
        )
        SettlementMethodContract.VARIATION_ID_LEND -> SettlementMethod.Lend()
        SettlementMethodContract.VARIATION_ID_LOAN_PAYMENT -> SettlementMethod.LoanPayment()
        else -> throw IntegrationLibraryMappingException(SettlementMethod::class.java)
    }
}