package ru.evotor.framework.core.action.event.receipt;

/**
 * Created by a.kuznetsov on 18/05/2017.
 */

public interface ReceiptOpenedEvent {
    String BROADCAST_ACTION_SELL_RECEIPT = "evotor.intent.action.receipt.sell.OPENED";
    String BROADCAST_ACTION_PAYBACK_RECEIPT = "evotor.intent.action.receipt.payback.OPENED";
    String KEY_UUID = "uuid";
}
