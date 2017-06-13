package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ReceiptOpenedEvent extends ReceiptEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT = "evotor.intent.action.receipt.sell.OPENED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT = "evotor.intent.action.receipt.payback.OPENED";

    public ReceiptOpenedEvent(@Nullable String receiptUuid) {
        super(receiptUuid);
    }

    private ReceiptOpenedEvent(@NonNull Bundle extras) {
        super(extras);
    }

    @Nullable
    public static ReceiptOpenedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new ReceiptOpenedEvent(bundle);
    }
}
