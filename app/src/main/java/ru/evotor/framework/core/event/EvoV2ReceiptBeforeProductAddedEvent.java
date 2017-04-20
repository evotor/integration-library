package ru.evotor.framework.core.event;

import android.os.Bundle;

import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public class EvoV2ReceiptBeforeProductAddedEvent {
    public static final String NAME = "evo.v2.receipt.beforeProductAdded";
    private static final String KEY_POSITION = "position";

    public static EvoV2ReceiptBeforeProductAddedEvent create(Bundle bundle) {
        Position position = PositionMapper.from(bundle.getBundle(KEY_POSITION));
        return new EvoV2ReceiptBeforeProductAddedEvent(position);
    }

    private Position position;

    public EvoV2ReceiptBeforeProductAddedEvent(Position position) {
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
