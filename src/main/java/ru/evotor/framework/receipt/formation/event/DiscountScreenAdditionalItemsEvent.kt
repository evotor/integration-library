package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent

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
            DiscountScreenAdditionalItemsEvent(it.getString(KEY_RECEIPT_UUID))
        }
    }

}