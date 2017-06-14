package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import ru.evotor.framework.Utils;
import ru.evotor.framework.calculator.MoneyCalculator;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra;

public class ReceiptDiscountEventResult {

    private static final String KEY_RESULT = "result";
    private static final String KEY_DISCOUNT = "discount";
    private static final String KEY_RECEIPT_EXTRA = "extra";
    private static final String KEY_CHANGES = "changes";

    @Nullable
    public static ReceiptDiscountEventResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String resultName = bundle.getString(KEY_RESULT);
        BigDecimal discount = MoneyCalculator.round(new BigDecimal(bundle.getString(KEY_DISCOUNT, "0")));
        return new ReceiptDiscountEventResult(
                Utils.safeValueOf(Result.class, resultName, Result.UNKNOWN),
                discount,
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)),
                Utils.filterByClass(
                        ChangesMapper.INSTANCE.create(bundle.getParcelableArray(KEY_CHANGES)),
                        IPositionChange.class
                )
        );
    }

    @NonNull
    private final Result result;
    @NonNull
    private final BigDecimal discount;
    @Nullable
    private final SetExtra extra;
    @Nullable
    private final List<IPositionChange> changes;

    public ReceiptDiscountEventResult(
            @NonNull Result result,
            @NonNull BigDecimal discount,
            @Nullable SetExtra extra,
            @Nullable List<IPositionChange> changes
    ) {
        Objects.requireNonNull(result);
        Objects.requireNonNull(discount);

        this.result = result;
        this.discount = discount;
        this.extra = extra;
        this.changes = changes;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT, result.name());
        bundle.putString(KEY_DISCOUNT, discount.toPlainString());
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra == null ? null : extra.toBundle());
        Parcelable[] changesParcelable = new Parcelable[changes == null ? 0 : changes.size()];
        for (int i = 0; i < changesParcelable.length; i++) {
            IChange change = changes.get(i);
            changesParcelable[i] = ChangesMapper.INSTANCE.toBundle(change);
        }
        bundle.putParcelableArray(KEY_CHANGES, changesParcelable);
        return bundle;
    }

    @NonNull
    public BigDecimal getDiscount() {
        return discount;
    }

    @NonNull
    public Result getResult() {
        return result;
    }

    @Nullable
    public SetExtra getExtra() {
        return extra;
    }

    @Nullable
    public List<IPositionChange> getChanges() {
        return changes;
    }

    public enum Result {
        OK,
        UNKNOWN;
    }
}
