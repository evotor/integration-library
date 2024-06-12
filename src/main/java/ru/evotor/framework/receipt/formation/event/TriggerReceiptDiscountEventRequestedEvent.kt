package ru.evotor.framework.receipt.formation.event

import android.content.ComponentName
import android.os.Bundle
import ru.evotor.IBundlable
import ru.evotor.framework.receipt.Receipt

class TriggerReceiptDiscountEventRequestedEvent(
    val componentName: ComponentName,
    val receiptType: Receipt.Type
) : IBundlable {

    override fun toBundle(): Bundle {
        return Bundle().also {
            it.putParcelable(KEY_COMPONENT_NAME, componentName)
            it.putString(KEY_RECEIPT_TYPE, receiptType.name)
        }
    }

    companion object {
        /**
         * Разрешение для команды отправки вызова сервиса начисления скидки для текущего открытого чека.
         *
         * Указывайте разрешение в манифесте приложения, в элементе `<uses-permission android:name="" />` до элемента `<application>`.
         */
        const val NAME_PERMISSION = "ru.evotor.permission.TRIGGER_RECEIPT_DISCOUNT_EVENT"

        private const val KEY_COMPONENT_NAME = "KEY_COMPONENT_NAME"
        private const val KEY_RECEIPT_TYPE = "KEY_RECEIPT_TYPE"

        fun create(bundle: Bundle?): TriggerReceiptDiscountEventRequestedEvent? {
            bundle ?: return null

            val componentName = bundle.getParcelable<ComponentName>(KEY_COMPONENT_NAME)
                ?: throw IllegalStateException("Bundle doesn't contain the necessary data to create TriggerReceiptDiscountEventCommand")
            val receiptType = bundle.getString(KEY_RECEIPT_TYPE)?.let{Receipt.Type.valueOf(it) }
                ?: throw IllegalStateException("Bundle doesn't contain the necessary data to create TriggerReceiptDiscountEventCommand")

            return TriggerReceiptDiscountEventRequestedEvent(componentName, receiptType)
        }
    }
}
