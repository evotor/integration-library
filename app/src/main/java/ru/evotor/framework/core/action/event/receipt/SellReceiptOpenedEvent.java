package ru.evotor.framework.core.action.event.receipt;

/**
 * Created by a.kuznetsov on 18/05/2017.
 */

public interface SellReceiptOpenedEvent {
    String BROADCAST_ACTION = "evotor.intent.action.receipt.sell.OPENED";
    String KEY_UUID = "uuid";
}
