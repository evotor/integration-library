package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.receipt.position.SettlementMethod
import ru.evotor.framework.receipt.provider.ReceiptContract
import ru.evotor.framework.safeGetBigDecimal
import ru.evotor.framework.safeGetInt

internal object SettlementMethodMapper {
    fun read(cursor: Cursor) = when (cursor.safeGetInt(ReceiptContract.SettlementMethodColumns.VARIATION_ID)) {
        ReceiptContract.SettlementMethodColumns.VARIATION_ID_FULL_PREPAYMENT -> SettlementMethod.FullPrepayment()
        ReceiptContract.SettlementMethodColumns.VARIATION_ID_PARTIAL_PREPAYMENT -> SettlementMethod.PartialPrepayment()
        ReceiptContract.SettlementMethodColumns.VARIATION_ID_ADVANCE_PAYMENT -> SettlementMethod.AdvancePayment()
        ReceiptContract.SettlementMethodColumns.VARIATION_ID_FULL_SETTLEMENT -> SettlementMethod.FullSettlement()
        ReceiptContract.SettlementMethodColumns.VARIATION_ID_PARTIAL_SETTLEMENT -> SettlementMethod.PartialSettlement(
                cursor.safeGetBigDecimal(ReceiptContract.SettlementMethodColumns.AMOUNT)
                        ?: throw IntegrationLibraryMappingException(
                                SettlementMethod.PartialSettlement::class.java,
                                SettlementMethod.PartialSettlement::amount
                        )
        )
        ReceiptContract.SettlementMethodColumns.VARIATION_ID_LEND -> SettlementMethod.Lend()
        ReceiptContract.SettlementMethodColumns.VARIATION_ID_LOAN_PAYMENT -> SettlementMethod.LoanPayment()
        else -> throw IntegrationLibraryMappingException(SettlementMethod::class.java)
    }
}