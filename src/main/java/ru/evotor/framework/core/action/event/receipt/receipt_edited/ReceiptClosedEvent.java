package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @deprecated Используйте {@link ru.evotor.framework.receipt.event.ReceiptCompletedEvent}
 */
@Deprecated
public class ReceiptClosedEvent extends ReceiptEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT_CLOSED = "evotor.intent.action.receipt.sell.RECEIPT_CLOSED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT_CLOSED = "evotor.intent.action.receipt.payback.RECEIPT_CLOSED";
    public static final String BROADCAST_ACTION_BUY_RECEIPT_CLOSED = "evotor.intent.action.receipt.buy.RECEIPT_CLOSED";
    public static final String BROADCAST_ACTION_BUYBACK_RECEIPT_CLOSED = "evotor.intent.action.receipt.buyback.RECEIPT_CLOSED";

    public ReceiptClosedEvent(@NonNull String receiptUuid) {
        super(receiptUuid);
    }

    @Nullable
    public static ReceiptClosedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String receiptUuid = getReceiptUuid(bundle);
        if (receiptUuid == null) {
            return null;
        }
        return new ReceiptClosedEvent(receiptUuid);
    }
}
