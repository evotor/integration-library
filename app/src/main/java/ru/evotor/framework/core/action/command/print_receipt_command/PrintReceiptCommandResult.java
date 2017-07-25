package ru.evotor.framework.core.action.command.print_receipt_command;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;

public class PrintReceiptCommandResult implements IBundlable {

    private static final String KEY_RECEIPT_UUID = "receiptUuid";

    public static final int ERROR_CODE_EMAIL_OR_PHONE_CAN_NOT_BE_NULL = -1;

    @Nullable
    public static PrintReceiptCommandResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID);
        if (receiptUuid == null) {
            return null;
        }
        return new PrintReceiptCommandResult(
                receiptUuid
        );
    }

    @NonNull
    private final String receiptUuid;

    public PrintReceiptCommandResult(
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
}
