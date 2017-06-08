package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;

public abstract class ReceiptEvent {
    private static final String KEY_RECEIPT_UUID = "receiptUuid";

    private final String receiptUuid;

    public ReceiptEvent(Bundle extras) {
        this(
                extras.getString(KEY_RECEIPT_UUID, null)
        );
    }

    public ReceiptEvent(String receiptUuid) {
        this.receiptUuid = receiptUuid;
    }

    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        return result;
    }

    public String getReceiptUuid() {
        return receiptUuid;
    }
}
