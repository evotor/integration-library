package ru.evotor.framework.core.action.event.receipt.discount_required

import android.os.Bundle
import ru.evotor.IBundlable

/**
 * Событие, которое возникает при переходе на экран выбора типа оплаты.
 *
 * Чтобы приложение получало событие, значение константы [NAME_SELL_RECEIPT], [NAME_PAYBACK_RECEIPT] или [NAME_BUY_RECEIPT]
 * необходимо указать в элементе <code><action></code> intent-фильтра соотвествующей службы
 */
class ReceiptDiscountRequiredEvent: IBundlable {

    override fun toBundle(): Bundle {
        return Bundle()
    }

    companion object {
        /**
         * Разрешение для обработки события запроса сервиса начисления скидки на чек.
         *
         * Указывайте разрешение в манифесте приложения, в элементе `<uses-permission android:name="" />` до элемента `<application>`.
         */
        const val NAME_PERMISSION = "ru.evotor.permission.RECEIPT_DISCOUNT_REQUIRED_EVENT"

        /**
         * Запрос сервиса начисление скидки на чек продажи.
         */
        const val NAME_SELL_RECEIPT = "evo.v2.receipt.sell.receiptDiscountRequiredEvent"

        /**
         * Запрос сервиса начисление скидки на чек возврата.
         */
        const val NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.receiptDiscountRequiredEvent"

        /**
         * Запрос сервиса начисление скидки на чек покупки.
         */
        const val NAME_BUY_RECEIPT = "evo.v2.receipt.buy.receiptDiscountRequiredEvent"

        fun create(bundle: Bundle?): ReceiptDiscountRequiredEvent? {
            bundle ?: return null

            return ReceiptDiscountRequiredEvent()
        }
    }
}
