package ru.evotor.framework.core.action.command;

import android.os.Bundle;

import ru.evotor.framework.core.action.datamapper.PositionMapper;
import ru.evotor.framework.core.action.event.EvoV2ReceiptBeforeProductAddedEvent;
import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 26/04/2017.
 */

public class AddProductCommand {

    public static final String NAME = "evo.v2.receipt.sell.addProduct";
    private static final String KEY_POSITION = "position";

    public static EvoV2ReceiptBeforeProductAddedEvent create(Bundle bundle) {
        Position position = PositionMapper.from(bundle.getBundle(KEY_POSITION));
        return new EvoV2ReceiptBeforeProductAddedEvent(position);
    }

    private Position position;

    public AddProductCommand(Position position) {
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
