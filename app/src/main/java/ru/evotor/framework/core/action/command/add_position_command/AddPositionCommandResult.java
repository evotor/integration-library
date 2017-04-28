package ru.evotor.framework.core.action.command.add_position_command;

import android.os.Bundle;

/**
 * Created by a.kuznetsov on 26/04/2017.
 */

public class AddPositionCommandResult {

    private static final String KEY_RESULT = "RESULT";
    private static final String KEY_POSITION_UUID = "UUID";

    public static AddPositionCommandResult create(Bundle bundle) {
        String resultName = bundle.getString(KEY_RESULT);
        String positionUUID = bundle.getString(KEY_POSITION_UUID);

        return new AddPositionCommandResult(Result.getByName(resultName), positionUUID);
    }

    private final Result result;
    private final String positionUUID;

    public AddPositionCommandResult(Result result, String positionUUID) {
        this.result = result;
        this.positionUUID = positionUUID;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT, result.name());
        bundle.putString(KEY_POSITION_UUID, positionUUID);
        return bundle;
    }


    public enum Result {
        ADDED,
        REJECTED,
        UNKNOWN;

        public static Result getByName(String name) {
            try {
                return Result.valueOf(name);
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }
}
