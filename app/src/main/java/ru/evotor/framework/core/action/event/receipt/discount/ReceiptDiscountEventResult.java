package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import ru.evotor.IBundlable;
import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.BundleUtils;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra;

public class ReceiptDiscountEventResult implements IBundlable {

    private static final String KEY_DISCOUNT = "discount";
    private static final String KEY_RECEIPT_EXTRA = "extra";
    private static final String KEY_CHANGES = "changes";

    @Nullable
    public static ReceiptDiscountEventResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        BigDecimal discount = BundleUtils.getMoney(bundle, KEY_DISCOUNT);
        if (discount == null) {
            return null;
        }
        return new ReceiptDiscountEventResult(
                discount,
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)),
                Utils.filterByClass(
                        ChangesMapper.INSTANCE.create(bundle.getParcelableArray(KEY_CHANGES)),
                        IPositionChange.class
                )
        );
    }

    @NonNull
    private final BigDecimal discount;
    @Nullable
    private final SetExtra extra;
    @NonNull
    private final List<IPositionChange> changes;

    public ReceiptDiscountEventResult(
            @NonNull BigDecimal discount,
            @Nullable SetExtra extra,
            @NonNull List<IPositionChange> changes
    ) {
        Objects.requireNonNull(discount);

        this.discount = discount;
        this.extra = extra;
        this.changes = changes;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DISCOUNT, discount.toPlainString());
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra == null ? null : extra.toBundle());
        Parcelable[] changesParcelable = new Parcelable[changes.size()];
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

    @Nullable
    public SetExtra getExtra() {
        return extra;
    }

    @NonNull
    public List<IPositionChange> getChanges() {
        return changes;
    }
}