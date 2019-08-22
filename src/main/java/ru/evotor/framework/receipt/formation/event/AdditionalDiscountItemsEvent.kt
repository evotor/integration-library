package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent

data class AdditionalDiscountItemsEvent(
        val receiptUuid: String,
        val action: String
) : IntegrationEvent() {

    override fun toBundle() = Bundle().apply {
        putString(KEY_RECEIPT_UUID, receiptUuid)
        putString(KEY_ACTION, action)
    }

    companion object {
        const val KEY_RECEIPT_UUID = "KEY_RECEIPT_UUID"
        const val KEY_ACTION = "KEY_ACTION"

        @JvmStatic
        fun from(bundle: Bundle?) = bundle?.let {
            AdditionalDiscountItemsEvent(it.getString(KEY_RECEIPT_UUID), it.getString(KEY_ACTION))
        }
    }

}