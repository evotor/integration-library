package ru.evotor.framework.core.action.broadcast

/**
 * Широковещательный приёмник событий чека возврата покупки.
 * @see <a href="https://developer.evotor.ru/docs/tobi_pizda">Использование широковещательного приёмника</a>
 */
open class BuybackReceiptBroadcastReceiver : ReceiptBroadcastReceiver(
        ACTION_RECEIPT_OPENED,
        ACTION_POSITION_ADDED,
        ACTION_POSITION_EDITED,
        ACTION_POSITION_REMOVED,
        ACTION_RECEIPT_CLEARED,
        ACTION_RECEIPT_CLOSED
) {

    companion object {

        const val ACTION_RECEIPT_OPENED = "evotor.intent.action.receipt.buyback.OPENED"

        const val ACTION_POSITION_ADDED = "evotor.intent.action.receipt.buyback.POSITION_ADDED"

        const val ACTION_POSITION_EDITED = "evotor.intent.action.receipt.buyback.POSITION_EDITED"

        const val ACTION_POSITION_REMOVED = "evotor.intent.action.receipt.buyback.POSITION_REMOVED"

        const val ACTION_RECEIPT_CLEARED = "evotor.intent.action.receipt.buyback.CLEARED"

        const val ACTION_RECEIPT_CLOSED = "evotor.intent.action.receipt.buyback.CLOSED"

    }

}