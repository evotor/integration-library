package ru.evotor.framework.core.action.event.receipt.loyalty

import android.content.ComponentName
import android.os.Bundle
import ru.evotor.IBundlable

class LoyaltyCardProcessingRequiredEventResult(
    val componentName: ComponentName,
    val loyaltyCardId: String?
) : IBundlable {
    override fun toBundle(): Bundle {
        return Bundle().also {
            it.putParcelable(KEY_COMPONENT_NAME, componentName)
            it.putString(KEY_LOYALTY_CARD_ID, loyaltyCardId)
        }
    }

    companion object {
        private const val KEY_COMPONENT_NAME = "KEY_COMPONENT_NAME"
        private const val KEY_LOYALTY_CARD_ID = "KEY_LOYALTY_CARD_ID"

        fun create(bundle: Bundle?): LoyaltyCardProcessingRequiredEventResult? {
            bundle ?: return null

            val componentName = bundle.getParcelable<ComponentName>(KEY_COMPONENT_NAME)
                ?: throw IllegalStateException("Bundle doesn't contain the necessary data to create LoyaltyCardProcessingRequiredEventResult")

            val loyaltyCardId = bundle.getString(KEY_LOYALTY_CARD_ID)

            return LoyaltyCardProcessingRequiredEventResult(componentName, loyaltyCardId)
        }
    }
}
