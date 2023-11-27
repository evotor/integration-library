package ru.evotor.framework.core.action.event.receipt.error_card_payment.actions

import ru.evotor.IBundlable

interface IHandleEventResultAction : IBundlable {

    fun getType(): Type

    enum class Type {
        /**
         * Показ диалога ошибки с таймаутом
         */
        SHOW_ERROR_SCREEN_ACTION
    }
}
