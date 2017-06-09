package ru.evotor.framework.core.action.command.open_receipt_command;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class OpenReceiptCommandResult {

    private static final String KEY_RECEIPT_UUID = "receiptUuid";

    public static final int ERROR_CODE_RECEIPT_IS_ALREADY_OPEN = -1;

    @Nullable
    public static OpenReceiptCommandResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new OpenReceiptCommandResult(
                bundle.getString(KEY_RECEIPT_UUID)
        );
    }

    private final String receiptUuid;

    public OpenReceiptCommandResult(
            String receiptUuid
    ) {
        this.receiptUuid = receiptUuid;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RECEIPT_UUID, receiptUuid);
        return bundle;
    }
}
