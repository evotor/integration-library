package ru.evotor.framework.core.action.event.receipt.before_positions_edited;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.evotor.IBundlable;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetExtra;
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetPurchaserContactData;

/**
 * Результат обработки события изменения чека.
 */
public class BeforePositionsEditedEventResult implements IBundlable {

    private static final String KEY_CHANGES = "changes";
    private static final String KEY_RECEIPT_EXTRA = "extra";
    private static final String KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA = "setPurchaserContactData";

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
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)),
                SetPurchaserContactData.from(bundle.getBundle(KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA))
        );
    }

    /**
     * Список сделанных изменений.
     */
    @NonNull
    private final List<IPositionChange> changes;
    @Nullable
    private final SetExtra extra;
    @Nullable
    private final SetPurchaserContactData setPurchaserContactData;

    public BeforePositionsEditedEventResult(
            @Nullable List<IPositionChange> changes,
            @Nullable SetExtra extra,
            @Nullable SetPurchaserContactData setPurchaserContactData
    ) {
        this.changes = new ArrayList<>();
        if (changes != null) {
            this.changes.addAll(changes);
        }
        this.extra = extra;
        this.setPurchaserContactData = setPurchaserContactData;
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
        bundle.putBundle(
                KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA,
                setPurchaserContactData == null ? null : setPurchaserContactData.toBundle()
        );
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

    @Nullable
    public SetPurchaserContactData getSetPurchaserContactData() {
        return setPurchaserContactData;
    }
}
