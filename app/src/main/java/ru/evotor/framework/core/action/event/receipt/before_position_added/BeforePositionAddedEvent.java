package ru.evotor.framework.core.action.event.receipt.before_position_added;

import android.os.Bundle;

import ru.evotor.framework.core.action.datamapper.PositionMapper;
import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public class BeforePositionAddedEvent {
    public static final String NAME = "evo.v2.receipt.sell.beforePositionAdded";
    private static final String KEY_POSITION = "position";

    public static BeforePositionAddedEvent create(Bundle bundle) {
        Position position = PositionMapper.from(bundle.getBundle(KEY_POSITION));
        return new BeforePositionAddedEvent(position);
    }

    private Position position;

    public BeforePositionAddedEvent(Position position) {
        this.position = position;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putBundle(KEY_POSITION, PositionMapper.toBundle(position));
        return bundle;
    }

    public Position getPosition() {
        return position;
    }
}
