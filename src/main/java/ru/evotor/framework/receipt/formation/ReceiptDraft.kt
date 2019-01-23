package ru.evotor.framework.receipt.formation

import ru.evotor.framework.FutureFeature
import ru.evotor.framework.receipt.Payment
import ru.evotor.framework.receipt.SettlementType
import ru.evotor.framework.receipt.TaxationSystem
import ru.evotor.framework.receipt.position.Position
import java.math.BigDecimal
import java.util.*

@FutureFeature("Несформированный чек (до момента регистриции в кассе и/или печати)")
private sealed class ReceiptDraft

private class FiscalReceiptDraft : ReceiptDraft()

private class NonFiscalReceiptDraft : ReceiptDraft()