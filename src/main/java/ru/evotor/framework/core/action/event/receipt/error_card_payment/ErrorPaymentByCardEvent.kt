package ru.evotor.framework.core.action.event.receipt.error_card_payment

import android.os.Bundle
import ru.evotor.IBundlable

class ErrorPaymentByCardEvent : IBundlable {

    override fun toBundle(): Bundle {
        return Bundle()
    }

    companion object {

        /**
         * Разрешение для обработки события ошибки безналичной платежной системы
         *
         * Указывайте разрешение в манифесте приложения, в элементе `<uses-permission android:name="" />` до элемента `<application>`.
         */
        const val NAME_PERMISSION = "ru.evotor.permission.handle.errorPaymentByCard"
        const val NAME = "evo.v2.receipt.errorPaymentByCard"

        fun create(bundle: Bundle?): ErrorPaymentByCardEvent? {
            if(bundle == null) {
                return null
            }
            return ErrorPaymentByCardEvent()
        }
    }
}
