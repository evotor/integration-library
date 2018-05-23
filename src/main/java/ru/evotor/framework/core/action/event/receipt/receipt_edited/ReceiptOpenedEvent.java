package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ReceiptOpenedEvent extends ReceiptEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT = "evotor.intent.action.receipt.sell.OPENED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT = "evotor.intent.action.receipt.payback.OPENED";
    public static final String BROADCAST_ACTION_BUY_RECEIPT = "evotor.intent.action.receipt.buy.OPENED";
    public static final String BROADCAST_ACTION_BUYBACK_RECEIPT = "evotor.intent.action.receipt.buyback.OPENED";

    public ReceiptOpenedEvent(@NonNull String receiptUuid) {
        super(receiptUuid);
    }

    @Nullable
    public static ReceiptOpenedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String receiptUuid = getReceiptUuid(bundle);
        if (receiptUuid == null) {
            return null;
        }
        return new ReceiptOpenedEvent(receiptUuid);
    }
}
