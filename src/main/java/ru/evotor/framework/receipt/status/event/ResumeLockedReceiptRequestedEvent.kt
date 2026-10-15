package ru.evotor.framework.receipt.status.event

import ru.evotor.framework.receipt.event.ReceiptEvent

class ResumeLockedReceiptRequestedEvent(receiptUuid: String) : ReceiptEvent(receiptUuid)
