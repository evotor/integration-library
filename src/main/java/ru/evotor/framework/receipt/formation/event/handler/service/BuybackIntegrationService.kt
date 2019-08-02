package ru.evotor.framework.receipt.formation.event.handler.service

import android.os.Bundle
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.formation.event.ReturnPurchaserRequisitesForPrintGroupRequestedEvent

/**
 * Служба для работы с чеком возврата покупки.
 */
abstract class BuybackIntegrationService : IntegrationServiceV2() {

    final override fun onEvent(action: String, bundle: Bundle) = when (action) {
        ACTION_PURCHASER_REQUISITES -> ReturnPurchaserRequisitesForPrintGroupRequestedEvent.from(bundle)?.let { handleEvent(it) }
        else -> null
    }

    /**
     * Возвращает смарт-терминалу массив печатных групп с соответствующими реквизитами покупателя.
     *
     * @param event Событие, с помощью которого смарт-терминал запрашивает у установленных приложений реквизиты покупателя.
     * @return Результат обработки события, содержащий массив печатных групп и соответствующих им объектов с реквизитами покупателя.
     * @see <a href="https://developer.evotor.ru/docs/doc_java_purchase_requisites_event_processing.html">Добавление реквизитов покупателя в чек</a>
     */
    @RequiresIntentAction(ACTION_PURCHASER_REQUISITES)
    open fun handleEvent(event: ReturnPurchaserRequisitesForPrintGroupRequestedEvent): ReturnPurchaserRequisitesForPrintGroupRequestedEvent.Result? = null

    companion object {

        /**
         * Запрос [реквизитов покупателя][ru.evotor.framework.receipt.Purchaser] для добавления в чек возврата покупки.
         *
         * Чтобы подписать службу на получение запроса, в манифесте приложения, в элементе `action` intent-фильтра службы, укажите значение `ru.evotor.event.sell.PURCHASER_REQUISITES`.
         */
        const val ACTION_PURCHASER_REQUISITES = "ru.evotor.event.buyback.PURCHASER_REQUISITES"

        /**
         * Разрешение необходимое приложению для работы со службой [ru.evotor.framework.receipt.formation.event.handler.service.BuybackIntegrationServicebackIntegrationService].
         *
         * Чтобы выдать разрешение, в элементе `uses-permission` манифеста приложения, укажите значение `ru.evotor.permission.BUYBACK_INTEGRATION_SERVICE`.
         */
        const val PERMISSION = "ru.evotor.permission.BUYBACK_INTEGRATION_SERVICE"
    }
}