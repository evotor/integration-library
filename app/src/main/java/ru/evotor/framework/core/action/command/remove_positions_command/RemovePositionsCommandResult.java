package ru.evotor.framework.core.action.command.remove_positions_command;

import android.os.Bundle;

import ru.evotor.framework.Utils;

/**
 * Created by a.kuznetsov on 26/04/2017.
 */

public class RemovePositionsCommandResult {

    private static final String KEY_RESULT = "result";
    private static final String KEY_ERROR_CODE = "errorCode";

    public static final int ERROR_CODE_OK = 0;

    public static RemovePositionsCommandResult create(Bundle bundle) {
        String resultName = bundle.getString(KEY_RESULT);

        return new RemovePositionsCommandResult(
                Utils.safeValueOf(Result.class, resultName, Result.UNKNOWN),
                bundle.getInt(KEY_ERROR_CODE, ERROR_CODE_OK)
        );
    }

    private final Result result;
    private final int errorCode;

    public RemovePositionsCommandResult(
            Result result,
            int errorCode
    ) {
        this.result = result;
        this.errorCode = errorCode;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT, result.name());
        return bundle;
    }

    public Result getResult() {
        return result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public enum Result {
        OK,
        UNKNOWN;
    }
}
