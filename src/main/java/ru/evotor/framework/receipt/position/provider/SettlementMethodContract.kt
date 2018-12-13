package ru.evotor.framework.receipt.position.provider

internal object SettlementMethodContract {
    const val COLUMN_VARIATION_ID = "SETTLEMENT_METHOD_VARIATION_ID"
    const val COLUMN_AMOUNT = "SETTLEMENT_METHOD_AMOUNT"

    const val VARIATION_ID_FULL_PREPAYMENT = 0
    const val VARIATION_ID_PARTIAL_PREPAYMENT = 1
    const val VARIATION_ID_ADVANCE_PAYMENT = 2
    const val VARIATION_ID_FULL_SETTLEMENT = 3
    const val VARIATION_ID_PARTIAL_SETTLEMENT = 4
    const val VARIATION_ID_LEND = 5
    const val VARIATION_ID_LOAN_PAYMENT = 6
}