package ru.evotor.framework.core.action.event.receipt.receipt_edited;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;

/**
 * @deprecated Используйте {@link ru.evotor.framework.receipt.event.ReceiptEvent}
 */
@Deprecated
public abstract class ReceiptEvent implements IBundlable {
    private static final String KEY_RECEIPT_UUID = "receiptUuid";

    @NonNull
    private final String receiptUuid;

    @Nullable
    static String getReceiptUuid(@NonNull Bundle bundle) {
        return bundle.getString(KEY_RECEIPT_UUID, null);
    }

    ReceiptEvent(@NonNull String receiptUuid) {
        this.receiptUuid = receiptUuid;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        return result;
    }

    @NonNull
    public String getReceiptUuid() {
        return receiptUuid;
    }
}
