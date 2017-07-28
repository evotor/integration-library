package ru.evotor.framework.core.action.command.print_receipt_command;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;

public class PrintReceiptCommandResult implements IBundlable {

    private static final String KEY_RECEIPT_UUID = "receiptUuid";
    private static final String KEY_RECEIPT_NUMBER = "receiptNumber";

    public static final int ERROR_CODE_DATETIME_SYNC_REQUIRED = -1;
    public static final int ERROR_CODE_SESSION_TIME_EXPIRED = -2;
    public static final int ERROR_CODE_EMAIL_AND_PHONE_ARE_NULL = -3;
    public static final int ERROR_CODE_KKM_IS_BUSY = -4;
    public static final int ERROR_CODE_NO_AUTHENTICATED_USER = -5;
    public static final int ERROR_CODE_PRINT_DOCUMENT_CREATION_FAILED = -6;

    @Nullable
    public static PrintReceiptCommandResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID);
        String receiptNumber = bundle.getString(KEY_RECEIPT_NUMBER);
        if (receiptUuid == null || receiptNumber == null) {
            return null;
        }
        return new PrintReceiptCommandResult(
                receiptUuid,
                receiptNumber
        );
    }

    @NonNull
    private final String receiptUuid;
    @NonNull
    private final String receiptNumber;

    public PrintReceiptCommandResult(
            @NonNull String receiptUuid,
            @NonNull String receiptNumber
    ) {
        this.receiptUuid = receiptUuid;
        this.receiptNumber = receiptNumber;
    }

    @Override
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RECEIPT_UUID, receiptUuid);
        bundle.putString(KEY_RECEIPT_NUMBER, receiptNumber);
        return bundle;
    }
}
