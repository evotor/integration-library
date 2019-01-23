package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.receipt.position.SettlementMethod
import ru.evotor.framework.receipt.provider.ReceiptContract
import ru.evotor.framework.core.safeGetBigDecimal
import ru.evotor.framework.core.safeGetInt

internal object SettlementMethodMapper {
    fun read(cursor: Cursor) = when (cursor.safeGetInt(ReceiptContract.Position.SETTLEMENT_METHOD_VARIATION_ID)) {
        ReceiptContract.Position.SETTLEMENT_METHOD_VARIATION_ID_FULL_PREPAYMENT -> SettlementMethod.FullPrepayment()
        ReceiptContract.Position.SETTLEMENT_METHOD_VARIATION_ID_PARTIAL_PREPAYMENT -> SettlementMethod.PartialPrepayment()
        ReceiptContract.Position.SETTLEMENT_METHOD_VARIATION_ID_ADVANCE_PAYMENT -> SettlementMethod.AdvancePayment()
        ReceiptContract.Position.SETTLEMENT_METHOD_VARIATION_ID_FULL_SETTLEMENT -> SettlementMethod.FullSettlement()
        ReceiptContract.Position.SETTLEMENT_METHOD_VARIATION_ID_PARTIAL_SETTLEMENT -> SettlementMethod.PartialSettlement(
                cursor.safeGetBigDecimal(ReceiptContract.Position.SETTLEMENT_METHOD_AMOUNT)
                        ?: throw IntegrationLibraryMappingException(
                                SettlementMethod.PartialSettlement::class.java,
                                SettlementMethod.PartialSettlement::amount
                        )
        )
        ReceiptContract.Position.SETTLEMENT_METHOD_VARIATION_ID_LEND -> SettlementMethod.Lend()
        ReceiptContract.Position.SETTLEMENT_METHOD_VARIATION_ID_LOAN_PAYMENT -> SettlementMethod.LoanPayment()
        else -> throw IntegrationLibraryMappingException(SettlementMethod::class.java)
    }
}