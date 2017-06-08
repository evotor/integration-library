package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;

public class ReceiptOpenedEvent extends ReceiptEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT = "evotor.intent.action.receipt.sell.OPENED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT = "evotor.intent.action.receipt.payback.OPENED";

    public ReceiptOpenedEvent(Bundle extras) {
        super(extras);
    }

    public ReceiptOpenedEvent(String receiptUuid) {
        super(receiptUuid);
    }
}
