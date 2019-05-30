package ru.evotor.framework.core.action.event.receipt.before_positions_edited;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra;

/**
 * Результат обработки события изменения чека.
 */
public class BeforePositionsEditedEventResult implements IBundlable {

    private static final String KEY_CHANGES = "changes";
    private static final String KEY_RECEIPT_EXTRA = "extra";

    @Nullable
    public static BeforePositionsEditedEventResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Parcelable[] changesParcelable = bundle.getParcelableArray(KEY_CHANGES);
        List<IChange> changes = ChangesMapper.INSTANCE.create(changesParcelable);
        List<IPositionChange> positionChanges = new ArrayList<>();
        for (IChange change : changes) {
            if (change instanceof IPositionChange) {
                positionChanges.add((IPositionChange) change);
            }
        }
        return new BeforePositionsEditedEventResult(
                positionChanges,
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA))
        );
    }

    /**
     * Список сделанных изменений.
     */
    @NonNull
    private final List<IPositionChange> changes;
    @Nullable
    private final SetExtra extra;

    public BeforePositionsEditedEventResult(
            @Nullable List<IPositionChange> changes,
            @Nullable SetExtra extra
    ) {
        this.changes = new ArrayList<>();
        if (changes != null) {
            this.changes.addAll(changes);
        }
        this.extra = extra;
    }

    @Override
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        Parcelable[] changesParcelable = new Parcelable[changes.size()];
        for (int i = 0; i < changesParcelable.length; i++) {
            IChange change = changes.get(i);
            changesParcelable[i] = ChangesMapper.INSTANCE.toBundle(change);
        }
        bundle.putParcelableArray(KEY_CHANGES, changesParcelable);
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra == null ? null : extra.toBundle());
        return bundle;
    }

    @NonNull
    public List<IPositionChange> getChanges() {
        return changes;
    }

    @Nullable
    public SetExtra getExtra() {
        return extra;
    }
}
