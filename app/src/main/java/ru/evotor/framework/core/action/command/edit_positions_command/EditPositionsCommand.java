package ru.evotor.framework.core.action.command.edit_positions_command;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.List;

import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;

/**
 * Created by a.kuznetsov on 26/04/2017.
 */

public class EditPositionsCommand {

    public static final String NAME = "evo.v2.receipt.sell.editPositions";
    private static final String KEY_CHANGES = "changes";

    public static EditPositionsCommand create(Bundle bundle) {
        Parcelable[] changesParcelable = bundle.getParcelableArray(KEY_CHANGES);
        return new EditPositionsCommand(Utils.filterByClass(
                ChangesMapper.INSTANCE.create(changesParcelable),
                IPositionChange.class
        ));
    }

    private final List<IPositionChange> changes;

    public EditPositionsCommand(List<IPositionChange> changes) {
        this.changes = changes;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        Parcelable[] changesParcelable = new Parcelable[changes.size()];
        for (int i = 0; i < changesParcelable.length; i++) {
            IChange change = changes.get(i);
            changesParcelable[i] = ChangesMapper.INSTANCE.toBundle(change);
        }
        bundle.putParcelableArray(KEY_CHANGES, changesParcelable);
        return bundle;
    }

    public List<IPositionChange> getChanges() {
        return changes;
    }
}
