package ru.evotor.framework.receipt.formation

import ru.evotor.framework.FutureFeature
import ru.evotor.framework.receipt.Payment
import ru.evotor.framework.receipt.SettlementType
import ru.evotor.framework.receipt.TaxationSystem
import ru.evotor.framework.receipt.position.Position
import java.math.BigDecimal
import java.util.*

@FutureFeature("Несформированный чек (до момента регистриции в кассе и/или печати)")
private sealed class ReceiptDraft {
    abstract val uuid: UUID
    abstract val settlementType: SettlementType
    abstract val taxationSystem: TaxationSystem
    abstract val positions: List<Position>
    abstract val payments: Map<Payment, BigDecimal>
}

private data class FiscalReceiptDraft(
        override val uuid: UUID,
        override val settlementType: SettlementType,
        override val taxationSystem: TaxationSystem,
        val customerPhoneOrEmail: String? = null,
        override val positions: List<Position>,
        override val payments: Map<Payment, BigDecimal>,
        val needToPrint: Boolean = true
) : ReceiptDraft()

private data class NonFiscalReceiptDraft(
        override val uuid: UUID,
        override val settlementType: SettlementType,
        val userName: String,
        val userInn: String,
        val settlementsAddress: String,
        val settlementsPlace: String,
        override val taxationSystem: TaxationSystem,
        override val positions: List<Position>,
        override val payments: Map<Payment, BigDecimal>
) : ReceiptDraft()