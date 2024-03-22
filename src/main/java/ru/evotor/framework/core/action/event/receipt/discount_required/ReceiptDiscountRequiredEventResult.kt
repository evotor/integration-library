package ru.evotor.framework.core.action.event.receipt.discount_required

import android.content.ComponentName
import android.os.Bundle
import ru.evotor.IBundlable

class ReceiptDiscountRequiredEventResult(
    val componentName: ComponentName
) : IBundlable {
    override fun toBundle(): Bundle {
        return Bundle().also {
            it.putParcelable(KEY_COMPONENT_NAME, componentName)
        }
    }

    companion object {
        private const val KEY_COMPONENT_NAME = "KEY_COMPONENT_NAME"

        fun create(bundle: Bundle?): ReceiptDiscountRequiredEventResult? {
            bundle ?: return null

            val componentName = bundle.getParcelable<ComponentName>(KEY_COMPONENT_NAME)
                ?: throw IllegalStateException("Bundle doesn't contain the necessary data to create ReceiptDiscountRequiredEventResult")

            return ReceiptDiscountRequiredEventResult(componentName)
        }
    }

}
