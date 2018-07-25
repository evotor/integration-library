package ru.evotor.framework.core.action.broadcast

/**
 * Класс, получающий широковещательные сообщения о событиях чека покупки.
 */
open class BuyReceiptBroadcastReceiver : ReceiptBroadcastReceiver(
        ACTION_RECEIPT_OPENED,
        ACTION_POSITION_ADDED,
        ACTION_POSITION_EDITED,
        ACTION_POSITION_REMOVED,
        ACTION_RECEIPT_CLEARED,
        ACTION_RECEIPT_CLOSED
) {

    companion object {

        const val ACTION_RECEIPT_OPENED = "evotor.intent.action.receipt.buy.OPENED"

        const val ACTION_POSITION_ADDED = "evotor.intent.action.receipt.buy.POSITION_ADDED"

        const val ACTION_POSITION_EDITED = "evotor.intent.action.receipt.buy.POSITION_EDITED"

        const val ACTION_POSITION_REMOVED = "evotor.intent.action.receipt.buy.POSITION_REMOVED"

        const val ACTION_RECEIPT_CLEARED = "evotor.intent.action.receipt.buy.CLEARED"

        const val ACTION_RECEIPT_CLOSED = "evotor.intent.action.receipt.buy.CLOSED"

    }

}