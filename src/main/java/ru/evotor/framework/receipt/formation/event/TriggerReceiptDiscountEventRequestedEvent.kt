package ru.evotor.framework.receipt.formation.event

import android.content.ComponentName
import android.os.Bundle
import ru.evotor.IBundlable

class TriggerReceiptDiscountEventRequestedEvent(
    val componentName: ComponentName
) : IBundlable {

    override fun toBundle(): Bundle {
        return Bundle().also {
            it.putParcelable(KEY_COMPONENT_NAME, componentName)
        }
    }

    companion object {
        /**
         * Разрешение для команды отправки вызова сервиса начисления скидки для текущего открытого чека.
         *
         * Указывайте разрешение в манифесте приложения, в элементе `<uses-permission android:name="" />` до элемента `<application>`.
         */
        const val NAME_PERMISSION = "ru.evotor.permission.TRIGGER_RECEIPT_DISCOUNT_EVENT_COMMAND"

        private const val KEY_COMPONENT_NAME = "KEY_COMPONENT_NAME"

        fun create(bundle: Bundle?): TriggerReceiptDiscountEventRequestedEvent? {
            bundle ?: return null

            val componentName = bundle.getParcelable<ComponentName>(KEY_COMPONENT_NAME)
                ?: throw IllegalStateException("Bundle doesn't contain the necessary data to create TriggerReceiptDiscountEventCommand")

            return TriggerReceiptDiscountEventRequestedEvent(componentName)
        }
    }
}
