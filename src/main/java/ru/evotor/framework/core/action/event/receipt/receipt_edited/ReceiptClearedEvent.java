package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ReceiptClearedEvent extends ReceiptEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT_CLEARED = "evotor.intent.action.receipt.sell.CLEARED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT_CLEARED = "evotor.intent.action.receipt.payback.CLEARED";
    public static final String BROADCAST_ACTION_BUY_RECEIPT_CLEARED = "evotor.intent.action.receipt.buy.CLEARED";
    public static final String BROADCAST_ACTION_BUYBACK_RECEIPT_CLEARED = "evotor.intent.action.receipt.buyback.CLEARED";

    public ReceiptClearedEvent(@NonNull String receiptUuid) {
        super(receiptUuid);
    }

    @Nullable
    public static ReceiptClearedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String receiptUuid = getReceiptUuid(bundle);
        if (receiptUuid == null) {
            return null;
        }
        return new ReceiptClearedEvent(receiptUuid);
    }
}
