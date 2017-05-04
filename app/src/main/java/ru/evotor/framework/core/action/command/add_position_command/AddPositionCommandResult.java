package ru.evotor.framework.core.action.command.add_position_command;

import android.os.Bundle;

import ru.evotor.framework.Utils;

/**
 * Created by a.kuznetsov on 26/04/2017.
 */

public class AddPositionCommandResult {

    private static final String KEY_RESULT = "result";
    private static final String KEY_POSITION_UUID = "uuid";

    public static AddPositionCommandResult create(Bundle bundle) {
        String resultName = bundle.getString(KEY_RESULT);
        String positionUUID = bundle.getString(KEY_POSITION_UUID);

        return new AddPositionCommandResult(Utils.safeValueOf(Result.class, resultName, Result.UNKNOWN), positionUUID);
    }

    private final Result result;
    private final String positionUuid;

    public AddPositionCommandResult(Result result, String positionUuid) {
        this.result = result;
        this.positionUuid = positionUuid;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT, result.name());
        bundle.putString(KEY_POSITION_UUID, positionUuid);
        return bundle;
    }


    public enum Result {
        ADDED,
        REJECTED,
        UNKNOWN;
    }
}
