package ru.evotor.framework.core.action.event.receipt.before_positions_removed;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.PositionMapper;
import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 26/04/2017.
 */
public class BeforePositionsRemovedEventResult {

    private static final String KEY_RESULT = "result";
    private static final String KEY_POSITIONS = "positions";

    public static BeforePositionsRemovedEventResult create(Bundle bundle) {
        String resultName = bundle.getString(KEY_RESULT);
        Parcelable[] positionsParcelable = bundle.getParcelableArray(KEY_POSITIONS);
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < positionsParcelable.length; i++) {
            Position position = PositionMapper.from((Bundle) positionsParcelable[i]);
            if (position != null) {
                positions.add(position);
            }
        }

        return new BeforePositionsRemovedEventResult(Utils.safeValueOf(Result.class, resultName, Result.UNKNOWN), positions);
    }

    private final Result result;
    private final List<Position> positions;

    public BeforePositionsRemovedEventResult(Result result, List<Position> positions) {
        this.result = result;
        this.positions = positions;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT, result.name());
        Parcelable[] positionsParcelable = new Parcelable[positions.size()];
        for (int i = 0; i < positionsParcelable.length; i++) {
            positionsParcelable[i] = PositionMapper.toBundle(positions.get(i));
        }
        bundle.putParcelableArray(KEY_POSITIONS, positionsParcelable);
        return bundle;
    }

    public Result getResult() {
        return result;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public enum Result {
        OK,
        UNKNOWN;
    }
}
