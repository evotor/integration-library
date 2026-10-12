package ru.evotor.framework.receipt.event

import android.os.Bundle

/**
 * Событие отмены оплат чека.
 *
 * Происходит при создании нового чека в системе смарт-терминала,
 * например, при открытии приложения "Продажа".
 *
 * Обрабатывать это событие можно с помощью следующих широковещательных приёмников:
 * [ru.evotor.framework.receipt.event.handler.receiver.SellReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.PaybackReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuyReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.BuybackReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.CorrectionIncomeReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.CorrectionOutcomeReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.CorrectionReturnIncomeReceiptBroadcastReceiver]
 * [ru.evotor.framework.receipt.event.handler.receiver.CorrectionReturnOutcomeReceiptBroadcastReceiver]
 *
 * @param receiptUuid uuid чека
 */
class ReceiptPaymentsCanceledEvent(receiptUuid: String) : ReceiptEvent(receiptUuid) {
    companion object {
        fun from(bundle: Bundle?): ReceiptPaymentsCanceledEvent? = bundle?.let {
            ReceiptPaymentsCanceledEvent(getReceiptUuid(it) ?: return null)
        }
    }
}
