package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class ReceiptEvent {
    private static final String KEY_RECEIPT_UUID = "receiptUuid";

    @Nullable
    private final String receiptUuid;

    public ReceiptEvent(Bundle extras) {
        this(
                extras.getString(KEY_RECEIPT_UUID)
        );
    }

    public ReceiptEvent(@Nullable String receiptUuid) {
        this.receiptUuid = receiptUuid;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        return result;
    }

    @Nullable
    public String getReceiptUuid() {
        return receiptUuid;
    }
}
