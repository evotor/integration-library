package ru.evotor.framework.core.action.command.open_receipt_command;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.evotor.IBundlable;

public class OpenReceiptCommandResult implements IBundlable {

    private static final String KEY_RECEIPT_UUID = "receiptUuid";

    public static final int ERROR_CODE_RECEIPT_IS_ALREADY_OPEN = -1;
    public static final int ERROR_CODE_SELL_RECEIPT_IS_ALREADY_OPEN = -2;
    public static final int ERROR_CODE_PAYBACK_RECEIPT_IS_ALREADY_OPEN = -3;
    public static final int ERROR_CODE_BUY_RECEIPT_IS_ALREADY_OPEN = -4;
    public static final int ERROR_CODE_BUYBACK_RECEIPT_IS_ALREADY_OPEN = -5;
    public static final int ERROR_CODE_CORRECTION_INCOME_RECEIPT_IS_ALREADY_OPEN = -6;
    public static final int ERROR_CODE_CORRECTION_OUTCOME_RECEIPT_IS_ALREADY_OPEN = -7;
    public static final int ERROR_CODE_CORRECTION_RETURN_INCOME_RECEIPT_IS_ALREADY_OPEN = -8;
    public static final int ERROR_CODE_CORRECTION_RETURN_OUTCOME_RECEIPT_IS_ALREADY_OPEN = -9;

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
