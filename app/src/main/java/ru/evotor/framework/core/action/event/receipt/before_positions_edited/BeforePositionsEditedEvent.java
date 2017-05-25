package ru.evotor.framework.core.action.event.receipt.before_positions_edited;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.List;

import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;

/**
 * Created by a.kuznetsov on 19/04/2017.
 */

public class BeforePositionsEditedEvent {
    private static final String TAG = "PositionsEditedEvent";

    public static final String NAME = "evo.v2.receipt.sell.beforePositionsEdited";
    private static final String KEY_CHANGES = "changes";

    public static BeforePositionsEditedEvent create(Bundle bundle) {
        Parcelable[] changesParcelable = bundle.getParcelableArray(KEY_CHANGES);
        return new BeforePositionsEditedEvent(
                Utils.filterByClass(
                        ChangesMapper.INSTANCE.create(changesParcelable),
                        IPositionChange.class
                )
        );
    }

    private List<IPositionChange> changes;

    public BeforePositionsEditedEvent(List<IPositionChange> changes) {
        this.changes = changes;
    }

    public Bundle toBundle() {
        Bundle result = new Bundle();
        Parcelable[] changesParcelable = new Parcelable[changes.size()];
        for (int i = 0; i < changesParcelable.length; i++) {
            IChange change = changes.get(i);
            changesParcelable[i] = ChangesMapper.INSTANCE.toBundle(change);
        }
        result.putParcelableArray(KEY_CHANGES, changesParcelable);
        return result;
    }

    public List<IPositionChange> getChanges() {
        return changes;
    }
}
