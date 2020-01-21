package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent

/**
 * Событие, которое смарт-терминал рассылает при нажатии на иконку приложения на экране оплаты чеков продажи или возврата.
 *
 * Подпишитесь на это событие чтобы запускать операции или службы своего приложения.
 *
 * Обрабатывайте событие с помощью соответствующих методов служб [ru.evotor.framework.receipt.formation.event.handler.service.SellIntegrationService] и [ru.evotor.framework.receipt.formation.event.handler.service.PaybackIntegrationService].
 */
data class DiscountScreenAdditionalItemsEvent(
        val receiptUuid: String
) : IntegrationEvent() {

    override fun toBundle() = Bundle().apply {
        putString(KEY_RECEIPT_UUID, receiptUuid)
    }

    companion object {
        const val KEY_RECEIPT_UUID = "KEY_RECEIPT_UUID"

        @JvmStatic
        fun from(bundle: Bundle?) = bundle?.let {
            DiscountScreenAdditionalItemsEvent(
                    receiptUuid = it.getString(KEY_RECEIPT_UUID)
                            ?: return null
            )
        }
    }

}