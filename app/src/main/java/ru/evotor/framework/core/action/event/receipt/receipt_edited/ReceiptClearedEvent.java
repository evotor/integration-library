package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;

public class ReceiptClearedEvent extends ReceiptEvent {
    String BROADCAST_ACTION_SELL_RECEIPT_CLEARED = "evotor.intent.action.receipt.sell.CLEARED";
    String BROADCAST_ACTION_PAYBACK_RECEIPT_CLEARED = "evotor.intent.action.receipt.payback.CLEARED";

    public ReceiptClearedEvent(Bundle extras) {
        super(extras);
    }

    public ReceiptClearedEvent(String receiptUuid) {
        super(receiptUuid);
    }
}
