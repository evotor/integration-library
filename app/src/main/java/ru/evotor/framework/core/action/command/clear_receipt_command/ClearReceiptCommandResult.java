package ru.evotor.framework.core.action.command.clear_receipt_command;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ClearReceiptCommandResult {
    public static final int ERROR_CODE_RECEIPT_PROCESSING_IN_PROGRESS = -1;

    @Nullable
    public static ClearReceiptCommandResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        return new ClearReceiptCommandResult();
    }

    public ClearReceiptCommandResult(
    ) {
    }

    @NonNull
    public Bundle toBundle() {
        return new Bundle();
    }
}
