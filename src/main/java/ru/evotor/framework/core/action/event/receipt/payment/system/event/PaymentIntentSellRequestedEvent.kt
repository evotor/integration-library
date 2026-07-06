package ru.evotor.framework.core.action.event.receipt.payment.system.event

import java.math.BigDecimal

class PaymentIntentSellRequestedEvent(
    override val receiptUuid: String,
    override val sum: BigDecimal
) : PaymentIntentRequestedEvent(OperationType.SELL, receiptUuid, sum)
