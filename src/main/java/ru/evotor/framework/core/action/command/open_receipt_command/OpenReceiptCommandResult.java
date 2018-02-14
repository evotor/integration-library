package ru.evotor.framework.core.action.command.open_receipt_command;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;

public class OpenReceiptCommandResult implements IBundlable {

    private static final String KEY_RECEIPT_UUID = "receiptUuid";

    public static final int ERROR_CODE_RECEIPT_IS_ALREADY_OPEN = -1;

    @Nullable
    public static OpenReceiptCommandResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID);
        if (receiptUuid == null) {
            return null;
        }
        return new OpenReceiptCommandResult(
                receiptUuid
        );
    }

    @NonNull
    private final String receiptUuid;

    public OpenReceiptCommandResult(
            @NonNull String receiptUuid
    ) {
        this.receiptUuid = receiptUuid;
    }

    @Override
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RECEIPT_UUID, receiptUuid);
        return bundle;
    }

    @NonNull
    public String getReceiptUuid() {
        return receiptUuid;
    }
}
