package ru.evotor.framework.core.action.command.pay_and_print_receipt_command

import android.app.Activity
import android.os.Bundle
import ru.evotor.framework.component.PaymentDelegator
import ru.evotor.framework.component.PaymentPerformer
import ru.evotor.framework.core.IntegrationManagerCallback

class PayAndPrintSellReceiptCommand(
        receiptUuid: String,
        paymentPerformer: PaymentPerformer?,
        paymentDelegator: PaymentDelegator?
) : PayAndPrintReceiptCommand(
        receiptUuid,
        paymentPerformer,
        paymentDelegator
) {
    fun process(activity: Activity, callback: IntegrationManagerCallback) {
        process(activity, callback, NAME)
    }

    companion object {
        const val NAME = "evo.v2.receipt.sell.payAndPrintReceipt"
        fun create(bundle: Bundle?): PayAndPrintSellReceiptCommand? {
            if (bundle == null) {
                return null
            }
            return PayAndPrintSellReceiptCommand(
                    getReceiptUuid(bundle) ?: return null,
                    getPaymentPerformer(bundle),
                    getPaymentDelegator(bundle)
            )
        }
    }
}