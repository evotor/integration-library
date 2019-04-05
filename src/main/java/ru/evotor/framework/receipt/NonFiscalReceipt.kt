package ru.evotor.framework.receipt

import ru.evotor.framework.common.Document
import ru.evotor.framework.core.FutureFeature
import java.util.*

@FutureFeature("Нефискальный чек (квитанция)")
private data class NonFiscalReceipt(override val uuid: UUID) : Document()