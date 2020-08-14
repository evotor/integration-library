package ru.evotor.framework.receipt.formation.event.handler.service

import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.formation.event.DiscountScreenAdditionalItemsEvent
import ru.evotor.framework.receipt.formation.event.ReturnDeliveryRequisitesForReceiptRequestedEvent
import ru.evotor.framework.receipt.formation.event.ReturnMedicineAttributeEvent
import ru.evotor.framework.receipt.formation.event.ReturnPurchaserRequisitesForPrintGroupRequestedEvent

/**
 * Служба для работы с чеком возврата.
 */
abstract class PaybackIntegrationService : IntegrationServiceV2() {

    final override fun onEvent(action: String, bundle: Bundle): IBundlable? = when (action) {
        ACTION_PURCHASER_REQUISITES -> ReturnPurchaserRequisitesForPrintGroupRequestedEvent.from(bundle)?.let { handleEvent(it) }
        ACTION_DISCOUNT_SCREEN_ADDITIONAL_ITEMS -> DiscountScreenAdditionalItemsEvent.from(bundle)?.let { handleEvent(it) }
        ACTION_DELIVERY_REQUISITES -> ReturnDeliveryRequisitesForReceiptRequestedEvent.from(bundle)?.let { handleEvent(it) }
        ACTION_MEDICINE_ATTRIBUTES -> ReturnMedicineAttributeEvent.from(bundle)?.let { handleEvent(it) }
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

    /**
     * Запускает приложение по нажатию кнопки на экране оплаты чека.
     */
    @RequiresIntentAction(ACTION_DISCOUNT_SCREEN_ADDITIONAL_ITEMS)
    open fun handleEvent(event: DiscountScreenAdditionalItemsEvent): Nothing? = null

    /**
     * Возвращает смарт-терминалу данные адреса и места расчёта при разносной и развозной торговле.
     *
     * @param event Событие, с помощью которого, смарт-терминал сообщает приложениям о необходимости указать адрес и место расчёта при развозной или разносной торговле.
     * @return [ReturnDeliveryRequisitesForReceiptRequestedEvent.Result]
     *
     * @see <a href="https://developer.evotor.ru/docs/doc_java_itinerant_trade.html">"Добавление в чек адреса и места расчёта"</a>
     */
    @RequiresIntentAction(ACTION_DELIVERY_REQUISITES)
    open fun handleEvent(event: ReturnDeliveryRequisitesForReceiptRequestedEvent): ReturnDeliveryRequisitesForReceiptRequestedEvent.Result? = null

    /**
     * Возвращает смарт-терминалу дополнительные реквизиты пользователя, при торговле лекарственными препаратами
     *
     * @param event Событие, с помощью которого, смарт-терминал сообщает приложениям о необходимости внести  дополнительные реквизиты пользователя, при торговле лекарственными препаратами
     * @return [ReturnMedicineAttributeEvent.Result]
     */
    @RequiresIntentAction(ACTION_MEDICINE_ATTRIBUTES)
    open fun handleEvent(event: ReturnMedicineAttributeEvent): ReturnMedicineAttributeEvent.Result? = null

    companion object {

        /**
         * Запрос [реквизитов покупателя][ru.evotor.framework.receipt.Purchaser] для добавления в чек возврата.
         *
         * Чтобы подписать службу на получение запроса, в манифесте приложения, в элементе `action` intent-фильтра службы, укажите значение `ru.evotor.event.payback.PURCHASER_REQUISITES`.
         */
        const val ACTION_PURCHASER_REQUISITES = "ru.evotor.event.payback.PURCHASER_REQUISITES"
        const val ACTION_DISCOUNT_SCREEN_ADDITIONAL_ITEMS = "ru.evotor.event.payback.DISCOUNT_SCREEN_ADDITIONAL_ITEMS"

        /**
         * Запрос адреса и места расчёта для добавления в чек.
         *
         * Чтобы подписать службу на получение запроса, в манифесте приложения, в элементе `action` intent-фильтра службы, укажите значение `ru.evotor.event.payback.DELIVERY_REQUISITES`.
         */
        const val ACTION_DELIVERY_REQUISITES = "ru.evotor.event.payback.DELIVERY_REQUISITES"

        /**
         * Запрос дополнительных реквизитов пользователя, необходимых для добавления в чек при возврате лекарственных препаратов
         *
         * Чтобы подписать службу на получение запроса, в манифесте приложения, в элементе `action` intent-фильтра службы, укажите значение `ru.evotor.event.payback.MEDICINE_ATTRIBUTES`.
         */
        const val ACTION_MEDICINE_ATTRIBUTES = "ru.evotor.event.payback.MEDICINE_ATTRIBUTES"


        /**
         * Разрешение необходимое приложению для работы со службой [ru.evotor.framework.receipt.formation.event.handler.service.PaybackIntegrationService].
         *
         * Чтобы выдать разрешение, в элементе `uses-permission` манифеста приложения, укажите значение `ru.evotor.permission.PAYBACK_INTEGRATION_SERVICE`.
         */
        const val PERMISSION = "ru.evotor.permission.PAYBACK_INTEGRATION_SERVICE"
    }
}