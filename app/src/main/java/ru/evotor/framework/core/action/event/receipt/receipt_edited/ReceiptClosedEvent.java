package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;

public class ReceiptClosedEvent extends ReceiptEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT_CLOSED = "evotor.intent.action.receipt.sell.RECEIPT_CLOSED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT_CLOSED = "evotor.intent.action.receipt.payback.RECEIPT_CLOSED";

    public ReceiptClosedEvent(Bundle extras) {
        super(extras);
    }

    public ReceiptClosedEvent(String receiptUuid) {
        super(receiptUuid);
    }
}
