package ru.evotor.framework.core.action.command.clear_receipt_command;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.framework.Utils;

public class ClearReceiptCommandResult {

    private static final String KEY_RESULT = "result";
    private static final String KEY_ERROR_CODE = "errorCode";

    public static final int ERROR_CODE_OK = 0;
    public static final int ERROR_CODE_RECEIPT_PROCESSING_IN_PROGRESS = -1;

    @Nullable
    public static ClearReceiptCommandResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String resultName = bundle.getString(KEY_RESULT);

        return new ClearReceiptCommandResult(
                Utils.safeValueOf(Result.class, resultName, Result.UNKNOWN),
                bundle.getInt(KEY_ERROR_CODE, ERROR_CODE_OK)
        );
    }

    @NonNull
    private final Result result;
    private final int errorCode;

    public ClearReceiptCommandResult(
            @NonNull Result result,
            int errorCode
    ) {
        this.result = result;
        this.errorCode = errorCode;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT, result.name());
        bundle.putInt(KEY_ERROR_CODE, errorCode);
        return bundle;
    }

    @NonNull
    public Result getResult() {
        return result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public enum Result {
        OK,
        ERROR,
        UNKNOWN;
    }
}
