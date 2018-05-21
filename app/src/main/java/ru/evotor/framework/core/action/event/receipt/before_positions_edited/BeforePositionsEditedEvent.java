package ru.evotor.framework.core.action.event.receipt.before_positions_edited;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import ru.evotor.IBundlable;
import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;

public class BeforePositionsEditedEvent implements IBundlable {
    private static final String TAG = "PositionsEditedEvent";

    public static final String NAME_SELL_RECEIPT = "evo.v2.receipt.sell.beforePositionsEdited";
    public static final String NAME_PAYBACK_RECEIPT = "evo.v2.receipt.payback.beforePositionsEdited";
    public static final String NAME_BUY_RECEIPT = "evo.v2.receipt.buy.beforePositionsEdited";
    public static final String NAME_BUYBACK_RECEIPT = "evo.v2.receipt.buyback.beforePositionsEdited";

    private static final String KEY_RECEIPT_UUID = "receiptUuid";
    private static final String KEY_CHANGES = "changes";

    @NonNull
    private final String receiptUuid;
    @NonNull
    private final List<IPositionChange> changes;

    public BeforePositionsEditedEvent(
            @NonNull String receiptUuid,
            @NonNull List<IPositionChange> changes
    ) {
        this.receiptUuid = receiptUuid;
        this.changes = changes;
    }

    @Nullable
    public static BeforePositionsEditedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID, null);

        return new BeforePositionsEditedEvent(
                receiptUuid,
                Utils.filterByClass(
                        ChangesMapper.INSTANCE.create(bundle.getParcelableArray(KEY_CHANGES)),
                        IPositionChange.class
                )
        );
    }

    @Override
    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_RECEIPT_UUID, receiptUuid);
        Parcelable[] changesParcelable = new Parcelable[changes.size()];
        for (int i = 0; i < changesParcelable.length; i++) {
            IChange change = changes.get(i);
            changesParcelable[i] = ChangesMapper.INSTANCE.toBundle(change);
        }
        result.putParcelableArray(KEY_CHANGES, changesParcelable);
        return result;
    }

    @NonNull
    public String getReceiptUuid() {
        return receiptUuid;
    }

    @NonNull
    public List<IPositionChange> getChanges() {
        return changes;
    }
}
