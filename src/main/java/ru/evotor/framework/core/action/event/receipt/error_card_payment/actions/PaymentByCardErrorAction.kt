package ru.evotor.framework.core.action.event.receipt.error_card_payment.actions

import android.os.Bundle

class PaymentByCardErrorAction(
    val cancelTimeout: Long? = null
) : IHandleEventResultAction {

    override fun getType(): IHandleEventResultAction.Type {
        return IHandleEventResultAction.Type.SHOW_ERROR_SCREEN_ACTION
    }

    override fun toBundle(): Bundle {
        return Bundle().also {
            if (cancelTimeout != null) {
                it.putLong(KEY_TIMEOUT, cancelTimeout)
            }
        }
    }

    companion object {

        private const val KEY_TIMEOUT = "timeout"

        fun from(bundle: Bundle): PaymentByCardErrorAction {
            var timeout: Long? = bundle.getLong(KEY_TIMEOUT, -1L)
            if (timeout == -1L) {
                timeout = null
            }
            return PaymentByCardErrorAction(
                timeout
            )
        }
    }
}
