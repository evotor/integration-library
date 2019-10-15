package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent

/**
 * Событие, с помощью которого, смарт-терминал сообщает прилоежниям о необходимости указать адрес и место расчёта при развозной или разносной торговле.
 *
 * Смарт-терминал рассылает событие только если одновременно выполняются все следующие условия:
 *
 * * При регистрации или перерегистрации устройства пользователь установил флажок "Развозная торговля".
 * * Чек сформирован, указаны все способы оплаты, клиент передал деньги.
 * * Чек ещё не напечатан.
 * * На терминале активна функция "Развозная торговля". Для проверки активности функции используйте метод [ru.evotor.framework.features.FeaturesApi.isDeliveryActive].
 *
 * Метод, с помощью которого необходимо обрабатывать событие, зависит от типа чека:
 *
 * * Если событие возникло при оформлении чека продажи, используйте метод: [ru.evotor.framework.receipt.formation.event.handler.service.SellIntegrationService.handleEvent]
 * * Если событие возникло при оформлении чека возврата, используйте метод: [ru.evotor.framework.receipt.formation.event.handler.service.PaybackIntegrationService.handleEvent]
 * * Если событие возникло при оформлении чека покупки, используйте метод: [ru.evotor.framework.receipt.formation.event.handler.service.BuyIntegrationService.handleEvent]
 * * Если событие возникло при оформлении чека возврата покупки, используйте метод: [ru.evotor.framework.receipt.formation.event.handler.service.BuybackIntegrationService.handleEvent]
 *
 * Чтобы подписать службу на получение события, в манифесте приложения, в элементе `action` intent-фильтра службы, укажите значение `ru.evotor.event.buyback.DELIVERY_REQUISITES`.
 *
 * @see <a href="https://developer.evotor.ru/docs/doc_java_itinerant_trade.html">"Добавление в чек адреса и места расчёта"</a>
 */
data class ReturnDeliveryRequisitesForReceiptRequestedEvent(
        val receiptUuid: String,
        val paymentAddress: String? = null,
        val paymentPlace: String? = null
) : IntegrationEvent() {

    override fun toBundle() = Bundle().apply {
        putString(KEY_RECEIPT_UUID, receiptUuid)
        putString(KEY_EXTRA_ADDRESS, paymentAddress)
        putString(KEY_EXTRA_PLACE, paymentPlace)
    }

    companion object {
        const val KEY_RECEIPT_UUID = "KEY_RECEIPT_UUID"
        const val KEY_EXTRA_ADDRESS = "KEY_EXTRA_ADDRESS"
        const val KEY_EXTRA_PLACE = "KEY_EXTRA_PLACE"

        @JvmStatic
        fun from(bundle: Bundle?) = bundle?.let {
            ReturnDeliveryRequisitesForReceiptRequestedEvent(
                    receiptUuid = it.getString(KEY_RECEIPT_UUID) ?: return null,
                    paymentAddress = it.getString(KEY_EXTRA_ADDRESS),
                    paymentPlace = it.getString(KEY_EXTRA_PLACE)
            )
        }
    }

    /**
     * Результат обработки события, который содержит адрес и место расчёта.
     */
    data class Result(
            val paymentAddress: String,
            val paymentPlace: String
    ) : IntegrationEvent.Result() {

        override fun toBundle() = Bundle().apply {
            putString(KEY_EXTRA_ADDRESS, paymentAddress)
            putString(KEY_EXTRA_PLACE, paymentPlace)
        }

        companion object {

            @JvmStatic
            fun from(bundle: Bundle?) = bundle?.let {
                Result(
                        paymentAddress = it.getString(KEY_EXTRA_ADDRESS)
                                ?: return null,
                        paymentPlace = it.getString(KEY_EXTRA_PLACE)
                                ?: return null)
            }

        }

    }
}