package ru.evotor.framework.core.action.command.payment

import android.os.Bundle
import ru.evotor.IBundlable

class ProcessPaymentIntentCommandResult : IBundlable {
    override fun toBundle(): Bundle =
        Bundle()

    companion object {
        const val ERROR_CODE_SELL_RECEIPT_IS_ALREADY_OPEN = -1
        const val ERROR_CODE_PAYBACK_RECEIPT_IS_ALREADY_OPEN = -2
        const val ERROR_CODE_BUY_RECEIPT_IS_ALREADY_OPEN = -3
        const val ERROR_CODE_BUYBACK_RECEIPT_IS_ALREADY_OPEN = -4
        const val ERROR_CODE_CORRECTION_INCOME_RECEIPT_IS_ALREADY_OPEN = -5
        const val ERROR_CODE_CORRECTION_OUTCOME_RECEIPT_IS_ALREADY_OPEN = -6
        const val ERROR_CODE_CORRECTION_RETURN_INCOME_RECEIPT_IS_ALREADY_OPEN = -7
        const val ERROR_CODE_CORRECTION_RETURN_OUTCOME_RECEIPT_IS_ALREADY_OPEN = -8
        const val ERROR_CODE_PRINT_DOCUMENT_CREATION_FAILED = -9
        const val ERROR_CODE_PAYMENT_BY_INTENT_DOCUMENT_IS_NOT_CLOSED = -10
        const val ERROR_CODE_RECEIPT_IS_ALREADY_PAID = -11
        const val ERROR_CODE_PAYMENT_INTENT_MODE_NOT_USED_IN_RECEIPT = -12
        const val ERROR_CODE_RECEIPT_ALREADY_REFUNDED = -13

        fun create(bundle: Bundle?): ProcessPaymentIntentCommandResult? {
            return if (bundle == null) {
                null
            } else {
                ProcessPaymentIntentCommandResult()
            }
        }
    }
}
