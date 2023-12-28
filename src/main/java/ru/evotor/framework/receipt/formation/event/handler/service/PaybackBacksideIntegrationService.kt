package ru.evotor.framework.receipt.formation.event.handler.service

class PaybackBacksideIntegrationService {
    companion object {
        const val ACTION_MOVE_CURRENT_PAYBACK_RECEIPT_DRAFT_TO_PAYMENT_STAGE = "evotor.intent.action.MOVE_CURRENT_PAYBACK_RECEIPT_DRAFT_TO_PAYMENT_STAGE"
        const val ACTION_TRIGGER_RECEIPT_DISCOUNT_EVENT = "evotor.intent.action.ACTION_TRIGGER_PAYBACK_RECEIPT_DISCOUNT_EVENT"
    }
}
