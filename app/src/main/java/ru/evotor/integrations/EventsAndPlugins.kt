package ru.evotor.integrations

import android.os.Bundle
import android.os.Message

/**
 * Created by nixan on 01.03.17.
 */

sealed class Event(val eventId: Int) {

    open fun toMessage() = Message.obtain().apply {
        what = eventId
        data = Bundle()
    }

    sealed class Receipt(eventId: Int) : Event(eventId) {

        class Opened(val receiptId: Long) : Receipt(EVENT_RECEIPT_OPENED) {
            override fun toMessage() = super.toMessage().apply { data.putLong("receiptId", receiptId) }
        }

        class ProductAdded(val receiptId: Long, val productUuid: String) : Receipt(EVENT_RECEIPT_PRODUCT_ADDED) {
            override fun toMessage() = super.toMessage().apply {
                data.putLong("receiptId", receiptId)
                data.putString("productUuid", productUuid)
            }
        }

        class ProductRemoved(val receiptId: Long, val productUuid: String) : Receipt(EVENT_RECEIPT_PRODUCT_REMOVED) {
            override fun toMessage() = super.toMessage().apply {
                data.putLong("receiptId", receiptId)
                data.putString("productUuid", productUuid)
            }
        }

        class Closed(val isSellOperation: Boolean) : Receipt(EVENT_RECEIPT_CLOSED) {
            override fun toMessage() = super.toMessage().apply { data.putBoolean("isSellOperation", isSellOperation) }
        }

        class Deleted(val receiptId: Long) : Receipt(EVENT_RECEIPT_DELETED) {
            override fun toMessage() = super.toMessage().apply { data.putLong("receiptId", receiptId) }
        }

    }

    companion object {

        const val EVENT_RECEIPT_OPENED = 100
        const val EVENT_RECEIPT_CLOSED = 101
        const val EVENT_RECEIPT_DELETED = 102
        const val EVENT_RECEIPT_PRODUCT_ADDED = 103
        const val EVENT_RECEIPT_PRODUCT_REMOVED = 104

        fun fromMessage(message: Message) = when (message.what) {
            EVENT_RECEIPT_OPENED -> Receipt.Opened(message.data.getLong("receiptId", 0))
            EVENT_RECEIPT_PRODUCT_ADDED -> Receipt.ProductAdded(message.data.getLong("receiptId", 0), message.data.getString("productUuid"))
            EVENT_RECEIPT_PRODUCT_REMOVED -> Receipt.ProductRemoved(message.data.getLong("receiptId", 0), message.data.getString("productUuid"))
            EVENT_RECEIPT_CLOSED -> Receipt.Closed(message.data.getBoolean("isSellOperation", false))
            EVENT_RECEIPT_DELETED -> Receipt.Deleted(message.data.getLong("receiptId", 0))
            else -> throw UnknownEventException("${message.what} - ${message.data} is unknown")
        }
    }

}

sealed class PluginEvent(val eventId: Int) {

    open fun toMessage() = Message.obtain().apply {
        what = eventId
        data = Bundle()
    }

    sealed class Payment(eventId: Int) : PluginEvent(eventId) {

        class Start : Payment(EVENT_PAYMENT_START)
        class ReadyToPrint : Payment(EVENT_PAYMENT_READY_TO_PRINT)

    }

    companion object {

        const val EVENT_PAYMENT_START = 100
        const val EVENT_PAYMENT_READY_TO_PRINT = 101

        fun fromMessage(message: Message) = when (message.what) {
            EVENT_PAYMENT_START -> Payment.Start()
            EVENT_PAYMENT_READY_TO_PRINT -> Payment.ReadyToPrint()
            else -> throw UnknownEventException("${message.what} - ${message.data} is unknown")
        }
    }

}

//class PluginEventWrapper(val pluginEvent: PluginEvent, val messenger: Messenger) {
//    fun toIntent() = pluginEvent.toIntent().apply { putExtra("callback", messenger) }
//
//    companion object {
//        fun fromIntent(intent: Intent) = PluginEventWrapper(PluginEvent.fromIntent(intent), intent.getParcelableExtra("callback"))
//    }
//}

class UnknownEventException(error: String) : Exception(error)