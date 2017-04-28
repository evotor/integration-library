package ru.evotor.framework.core.action.command.add_position_command;

import android.os.Bundle;

import ru.evotor.framework.core.action.datamapper.PositionMapper;
import ru.evotor.framework.receipt.Position;

/**
 * Created by a.kuznetsov on 26/04/2017.
 */

public class AddPositionCommand {

    public static final String NAME = "evo.v2.receipt.sell.addProduct";
    private static final String KEY_POSITION = "position";

    public static AddPositionCommand create(Bundle bundle) {
        Position position = PositionMapper.from(bundle.getBundle(KEY_POSITION));
        return new AddPositionCommand(position);
    }

    private final Position position;

    public AddPositionCommand(Position position) {
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
