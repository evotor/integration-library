package ru.evotor.framework.receipt.position.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.mapper.MultiVariationEntityMapper
import ru.evotor.framework.receipt.position.SettlementMethod
import ru.evotor.framework.receipt.position.provider.SettlementMethodContract
import ru.evotor.framework.safeGetBigDecimal

internal object SettlementMethodMapper {
    private const val VARIATION_ID_FULL_PREPAYMENT = 0
    private const val VARIATION_ID_PARTIAL_PREPAYMENT = 1
    private const val VARIATION_ID_ADVANCE_PAYMENT = 2
    private const val VARIATION_ID_FULL_SETTLEMENT = 3
    private const val VARIATION_ID_PARTIAL_SETTLEMENT = 4
    private const val VARIATION_ID_LEND = 5
    private const val VARIATION_ID_LOAN_PAYMENT = 6

    fun read(cursor: Cursor) = when (MultiVariationEntityMapper.readVariationId(cursor)) {
        VARIATION_ID_FULL_PREPAYMENT -> SettlementMethod.FullPrepayment()
        VARIATION_ID_PARTIAL_PREPAYMENT -> SettlementMethod.PartialPrepayment()
        VARIATION_ID_ADVANCE_PAYMENT -> SettlementMethod.AdvancePayment()
        VARIATION_ID_FULL_SETTLEMENT -> SettlementMethod.FullSettlement()
        VARIATION_ID_PARTIAL_SETTLEMENT -> SettlementMethod.PartialSettlement(
                cursor.safeGetBigDecimal(SettlementMethodContract.COLUMN_AMOUNT)
                        ?: throw IntegrationLibraryMappingException(SettlementMethod.PartialSettlement::amount.name)
        )
        VARIATION_ID_LEND -> SettlementMethod.Lend()
        VARIATION_ID_LOAN_PAYMENT -> SettlementMethod.LoanPayment()
        else -> throw IntegrationLibraryMappingException(SettlementMethod::class.java.name)
    }
}