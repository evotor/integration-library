package ru.evotor.framework.receipt

import ru.evotor.framework.FutureFeature
import java.util.*

@FutureFeature("Несформированный чек (до момента регистриции в кассе и/или печати)")
private data class ReceiptDraft(val uuid: UUID)