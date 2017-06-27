package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ReceiptPaymentPartsEditedEvent extends ReceiptEvent {
    public static final String BROADCAST_ACTION_SELL_RECEIPT = "evotor.intent.action.receipt.sell.paymentParts.EDITED";
    public static final String BROADCAST_ACTION_PAYBACK_RECEIPT = "evotor.intent.action.receipt.payback.paymentParts.EDITED";

    public ReceiptPaymentPartsEditedEvent(@NonNull String receiptUuid) {
        super(receiptUuid);
    }

    @Nullable
    public static ReceiptPaymentPartsEditedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String receiptUuid = getReceiptUuid(bundle);
        if (receiptUuid == null) {
            return null;
        }
        return new ReceiptPaymentPartsEditedEvent(receiptUuid);
    }
}
