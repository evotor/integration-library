package ru.evotor.framework.receipt.formation.event

import android.os.Bundle
import ru.evotor.framework.common.event.IntegrationEvent

data class ReturnDeliveryRequisitesForReceiptRequestedEvent(
        val receiptUuid: String
) : IntegrationEvent() {

    override fun toBundle() = Bundle().apply {
        putString(KEY_RECEIPT_UUID, receiptUuid)
    }

    companion object {
        const val KEY_RECEIPT_UUID = "KEY_RECEIPT_UUID"

        @JvmStatic
        fun from(bundle: Bundle?) = bundle?.let {
            ReturnDeliveryRequisitesForReceiptRequestedEvent(
                    receiptUuid = it.getString(KEY_RECEIPT_UUID)
                            ?: return null)
        }
    }

    data class Result(
            val paymentAddress: String,
            val paymentPlace: String
    ) : IntegrationEvent.Result() {

        override fun toBundle() = Bundle().apply {
            putString(KEY_EXTRA_ADDRESS, paymentAddress)
            putString(KEY_EXTRA_PLACE, paymentPlace)
        }

        companion object {
            const val KEY_EXTRA_ADDRESS = "KEY_EXTRA_ADDRESS"
            const val KEY_EXTRA_PLACE = "KEY_EXTRA_PLACE"

            @JvmStatic
            fun from(bundle: Bundle?) = bundle?.let {
                Result(
                        paymentAddress = it.getString(KEY_EXTRA_ADDRESS)
                                ?: return null,
                        paymentPlace = it.getString(KEY_EXTRA_PLACE)
                                ?: return null)
            }

        }

    }
}