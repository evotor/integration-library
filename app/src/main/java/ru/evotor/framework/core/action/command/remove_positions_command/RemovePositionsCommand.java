package ru.evotor.framework.core.action.command.remove_positions_command;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import ru.evotor.framework.core.action.datamapper.PositionMapper;
import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 26/04/2017.
 */

public class RemovePositionsCommand {

    public static final String NAME = "evo.v2.receipt.sell.removePositions";
    private static final String KEY_POSITIONS = "positions";

    public static RemovePositionsCommand create(Bundle bundle) {
        Parcelable[] positionsParcelable = bundle.getParcelableArray(KEY_POSITIONS);
        List<Position> positions = new ArrayList<>();
        for (Parcelable positionParcelable : positionsParcelable) {
            positions.add(PositionMapper.from((Bundle) positionParcelable));
        }
        return new RemovePositionsCommand(positions);
    }

    private final List<Position> positions;

    public RemovePositionsCommand(List<Position> positions) {
        this.positions = positions;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        Parcelable[] positionsParcelable = new Parcelable[positions.size()];
        for (int i = 0; i < positionsParcelable.length; i++) {
            positionsParcelable[i] = PositionMapper.toBundle(positions.get(i));
        }
        bundle.putParcelableArray(KEY_POSITIONS, positionsParcelable);
        return bundle;
    }

    public List<Position> getPositions() {
        return positions;
    }
}
