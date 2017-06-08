package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ReceiptClosedEvent extends ReceiptEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT_CLOSED = "evotor.intent.action.receipt.sell.RECEIPT_CLOSED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT_CLOSED = "evotor.intent.action.receipt.payback.RECEIPT_CLOSED";

    public ReceiptClosedEvent(@Nullable String receiptUuid) {
        super(receiptUuid);
    }

    private ReceiptClosedEvent(@NonNull Bundle extras) {
        super(extras);
    }

    @Nullable
    public static ReceiptClosedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new ReceiptClosedEvent(bundle);
    }
}
