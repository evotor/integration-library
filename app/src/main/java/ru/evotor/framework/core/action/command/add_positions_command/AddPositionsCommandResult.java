package ru.evotor.framework.core.action.command.add_positions_command;

import android.os.Bundle;

import ru.evotor.framework.Utils;

/**
 * Created by a.kuznetsov on 26/04/2017.
 */

public class AddPositionsCommandResult {

    private static final String KEY_RESULT = "result";

    public static AddPositionsCommandResult create(Bundle bundle) {
        String resultName = bundle.getString(KEY_RESULT);

        return new AddPositionsCommandResult(Utils.safeValueOf(Result.class, resultName, Result.UNKNOWN));
    }

    private final Result result;

    public AddPositionsCommandResult(Result result) {
        this.result = result;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT, result.name());
        return bundle;
    }


    public enum Result {
        OK,
        UNKNOWN;
    }
}
