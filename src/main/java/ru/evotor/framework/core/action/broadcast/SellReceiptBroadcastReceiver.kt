package ru.evotor.framework.core.action.broadcast

/**
 * Широковещательный приёмник событий чека продажи.
 * @see <a href="https://developer.evotor.ru/docs/tobi_pizda">Использование широковещательного приёмника</a>
 */
open class SellReceiptBroadcastReceiver : ReceiptBroadcastReceiver(
        ACTION_RECEIPT_OPENED,
        ACTION_POSITION_ADDED,
        ACTION_POSITION_EDITED,
        ACTION_POSITION_REMOVED,
        ACTION_RECEIPT_CLEARED,
        ACTION_RECEIPT_CLOSED
) {

    companion object {

        const val ACTION_RECEIPT_OPENED = "evotor.intent.action.receipt.sell.OPENED"

        const val ACTION_POSITION_ADDED = "evotor.intent.action.receipt.sell.POSITION_ADDED"

        const val ACTION_POSITION_EDITED = "evotor.intent.action.receipt.sell.POSITION_EDITED"

        const val ACTION_POSITION_REMOVED = "evotor.intent.action.receipt.sell.POSITION_REMOVED"

        const val ACTION_RECEIPT_CLEARED = "evotor.intent.action.receipt.sell.CLEARED"

        const val ACTION_RECEIPT_CLOSED = "evotor.intent.action.receipt.sell.CLOSED"

    }

}