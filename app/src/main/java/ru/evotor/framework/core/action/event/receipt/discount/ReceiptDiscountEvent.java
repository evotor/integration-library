package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.List;

import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;

public class ReceiptDiscountEvent {
    private static final String TAG = "ReceiptDiscountEvent";

    public static final String NAME = "evo.v2.receipt.sell.receiptDiscount";
    private static final String KEY_DISCOUNT = "discount";
    private static final String KEY_CHANGES = "changes";

    public static ReceiptDiscountEvent create(Bundle bundle) {
        BigDecimal discount = new BigDecimal(bundle.getLong(KEY_DISCOUNT, 0)).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
        Parcelable[] changesParcelable = bundle.getParcelableArray(KEY_CHANGES);
        return new ReceiptDiscountEvent(
                discount,
                Utils.filterByClass(
                        ChangesMapper.INSTANCE.create(changesParcelable),
                        IPositionChange.class
                )
        );
    }

    private BigDecimal discount;
    private List<IPositionChange> changes;

    public ReceiptDiscountEvent(BigDecimal discount, List<IPositionChange> changes) {
        this.discount = discount;
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
        result.putLong(KEY_DISCOUNT, discount.multiply(new BigDecimal(100)).longValue());
        return result;
    }

    public List<IPositionChange> getChanges() {
        return changes;
    }
}
