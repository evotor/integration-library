package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;

public class ReceiptDiscountEventResult {

    private static final String KEY_RESULT = "result";
    private static final String KEY_CHANGES = "changes";
    private static final String KEY_DISCOUNT = "discount";

    public static ReceiptDiscountEventResult create(Bundle bundle) {
        String resultName = bundle.getString(KEY_RESULT);
        BigDecimal discount = new BigDecimal(bundle.getLong(KEY_DISCOUNT, 0)).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
        Parcelable[] changesParcelable = bundle.getParcelableArray(KEY_CHANGES);
        List<IChange> changes = ChangesMapper.INSTANCE.create(changesParcelable);
        List<IPositionChange> positionChanges = new ArrayList<>();
        for (IChange change : changes) {
            if (change instanceof IPositionChange) {
                positionChanges.add((IPositionChange) change);
            }
        }
        return new ReceiptDiscountEventResult(
                Utils.safeValueOf(Result.class, resultName, Result.UNKNOWN),
                discount,
                positionChanges
        );
    }

    private final Result result;
    private final BigDecimal discount;
    private final List<IPositionChange> changes;

    public ReceiptDiscountEventResult(Result result, BigDecimal discount, List<IPositionChange> changes) {
        this.result = result;
        this.discount = discount;
        this.changes = changes;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT, result.name());
        bundle.putLong(KEY_DISCOUNT, discount.multiply(new BigDecimal(100)).longValue());
        Parcelable[] changesParcelable = new Parcelable[changes.size()];
        for (int i = 0; i < changesParcelable.length; i++) {
            IChange change = changes.get(i);
            changesParcelable[i] = ChangesMapper.INSTANCE.toBundle(change);
        }
        bundle.putParcelableArray(KEY_CHANGES, changesParcelable);
        return bundle;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public Result getResult() {
        return result;
    }

    public List<IPositionChange> getChanges() {
        return changes;
    }

    public enum Result {
        OK,
        UNKNOWN;
    }
}
