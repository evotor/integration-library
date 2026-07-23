package ru.evotor.framework.core.action.event.receipt.payment.system.event

import java.math.BigDecimal

class PaymentIntentSellCancelRequestedEvent(
    override val receiptUuid: String,
    override val sum: BigDecimal
) : PaymentIntentRequestedEvent(OperationType.SELL_CANCEL, receiptUuid, sum)
