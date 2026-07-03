package ru.evotor.framework.core.action.event.receipt.loyalty

import android.os.Bundle
import ru.evotor.IBundlable

/**
 * Событие, которое возникает при прерывании оплаты картой.
 *
 * Чтобы приложение получало событие, значение константы [NAME_SELL_RECEIPT]
 * необходимо указать в элементе <code><action></code> intent-фильтра соотвествующей службы
 *
 * @param paymentSessionId банковский идентификатор оплаты.
 * Будет передан только в случае корректной работы прерывания
 *
 * @param externalLoyaltyCardId Внешний идентификатор карты лояльности.
 * Его наличие говорит о наличии связи между банковской картой и картой лояльности
 */
class LoyaltyCardProcessingRequiredEvent(
    val paymentSessionId: String,
    val externalLoyaltyCardId: String?
) : IBundlable {
    override fun toBundle(): Bundle {
        val result = Bundle()
        result.putString(KEY_PAYMENT_SESSION_ID, paymentSessionId)
        result.putString(KEY_EXTERNAL_LOYALTY_CARD_ID, externalLoyaltyCardId)
        return result
    }

    companion object {
        /**
         * Запрос сервиса начисление скидки только на чек продажи.
         */
        const val NAME_SELL_RECEIPT = "evo.v2.receipt.sell.LoyaltyCardProcessingRequiredEvent"
        private const val KEY_PAYMENT_SESSION_ID = "payment_session_id"
        private const val KEY_EXTERNAL_LOYALTY_CARD_ID = "external_loyalty_card_id"

        fun create(bundle: Bundle?): LoyaltyCardProcessingRequiredEvent? {
            bundle ?: return null
            return LoyaltyCardProcessingRequiredEvent(
                bundle.getString(KEY_PAYMENT_SESSION_ID, ""),
                bundle.getString(KEY_EXTERNAL_LOYALTY_CARD_ID, null)
            )
        }
    }
}
