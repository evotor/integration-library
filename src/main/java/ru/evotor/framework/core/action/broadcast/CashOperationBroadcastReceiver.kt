package ru.evotor.framework.core.action.broadcast

import android.content.Context
import android.os.Bundle
import ru.evotor.framework.core.action.datamapper.CashOperationEventMapper
import java.math.BigDecimal

/**
 * Класс, получающий широковещательные сообщения о денежных операциях.
 */
open class CashOperationBroadcastReceiver : AbstractBroadcastReceiver() {

    /**
     * Обработчик событий внесения наличности.
     */
    protected open fun handleCashInEvent(context: Context, documentUuid: String, total: BigDecimal) = Unit

    /**
     * Обработчик событий изъятия наличности.
     */
    protected open fun handleCashOutEvent(context: Context, documentUuid: String, total: BigDecimal) = Unit

    final override fun onEvent(context: Context, action: String, bundle: Bundle) {
        val mapper = CashOperationEventMapper(bundle)
        val documentUuid = mapper.getDocumentUuid() ?: return
        val total = mapper.getTotal() ?: return
        when (action) {
            ACTION_CASH_IN -> handleCashInEvent(context, documentUuid, total)
            ACTION_CASH_OUT -> handleCashOutEvent(context, documentUuid, total)
        }
    }

    companion object {

        const val ACTION_CASH_IN = "evotor.intent.action.cashOperation.CASH_IN"

        const val ACTION_CASH_OUT = "evotor.intent.action.cashOperation.CASH_OUT"

    }

}