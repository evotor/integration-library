package ru.evotor.framework.core.action.event.receipt.before_positions_added;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import ru.evotor.framework.core.action.datamapper.PositionMapper;
import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public class BeforePositionsAddedEvent {
    public static final String NAME = "evo.v2.receipt.sell.beforePositionsAdded";
    private static final String KEY_POSITIONS = "positions";

    public static BeforePositionsAddedEvent create(Bundle bundle) {
        Parcelable[] positionsParcelable = bundle.getParcelableArray(KEY_POSITIONS);
        List<Position> positions = new ArrayList<>();
        for (Parcelable positionParcelable : positionsParcelable) {
            positions.add(PositionMapper.from((Bundle) positionParcelable));
        }
        return new BeforePositionsAddedEvent(positions);
    }

    private List<Position> positions;

    public BeforePositionsAddedEvent(List<Position> positions) {
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
