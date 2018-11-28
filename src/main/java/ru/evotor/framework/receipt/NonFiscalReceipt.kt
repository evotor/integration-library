package ru.evotor.framework.receipt

import ru.evotor.framework.Document
import ru.evotor.framework.FutureFeature
import java.util.*

@FutureFeature("Нефискальный чек (квитанция)")
private data class NonFiscalReceipt(override val uuid: UUID) : Document()