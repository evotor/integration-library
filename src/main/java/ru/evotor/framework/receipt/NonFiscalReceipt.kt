package ru.evotor.framework.receipt

import ru.evotor.framework.Document
import ru.evotor.framework.FutureFeature
import ru.evotor.framework.receipt.position.Position
import java.math.BigDecimal
import java.util.*

@FutureFeature("Нефискальный чек (квитанция)")
private data class NonFiscalReceipt(
        override val uuid: UUID
) : Document() {
    enum class Type {
        INVOICE,
        STRING_UTII
    }
}