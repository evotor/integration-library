package ru.evotor.framework.core.action.event.receipt;

/**
 * Created by a.kuznetsov on 18/05/2017.
 */

public interface ReceiptClearedEvent {
    String BROADCAST_ACTION_SELL_RECEIPT = "evotor.intent.action.receipt.sell.CLEARED";
    String BROADCAST_ACTION_PAYBACK_RECEIPT = "evotor.intent.action.receipt.payback.CLEARED";
    String KEY_UUID = "uuid";

}
