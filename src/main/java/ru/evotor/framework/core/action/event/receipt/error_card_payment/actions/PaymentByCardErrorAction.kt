package ru.evotor.framework.core.action.event.receipt.error_card_payment.actions

import android.os.Bundle

/**
 * Команда обработки ошибки оплаты по карте.
 * При передаче этой команды будет показан диалог с ошибкой.
 * Если был передан cancelTimeout, то по истечении будет нажата кнопка "ОТМЕНА".
 */
class PaymentByCardErrorAction(
    val cancelTimeout: UInt? = null // таймаут нажатия на кнопку "ОТМЕНА" в секундах
) : IHandleEventResultAction {

    override fun getType(): IHandleEventResultAction.Type {
        return IHandleEventResultAction.Type.SHOW_ERROR_SCREEN_ACTION
    }

    override fun toBundle(): Bundle {
        return Bundle().also {
            if (cancelTimeout != null) {
                it.putLong(KEY_TIMEOUT, cancelTimeout.toLong())
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
                timeout?.toUInt()
            )
        }
    }
}
