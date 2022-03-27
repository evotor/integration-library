package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import ru.evotor.IBundlable;
import ru.evotor.framework.BundleUtils;
import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetExtra;
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetPurchaserContactData;

public class ReceiptDiscountEventResult implements IBundlable {

    private static final String KEY_DISCOUNT = "discount";
    private static final String KEY_RECEIPT_EXTRA = "extra";
    private static final String KEY_CHANGES = "changes";
    private static final String KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA = "setPurchaserContactData";

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
                ),
                SetPurchaserContactData.from(bundle.getBundle(KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA))
        );
    }

    @NonNull
    private final BigDecimal discount;
    @Nullable
    private final SetExtra extra;
    @NonNull
    private final List<IPositionChange> changes;
    @Nullable
    private final SetPurchaserContactData setPurchaserContactData;

    public ReceiptDiscountEventResult(
            @NonNull BigDecimal discount,
            @Nullable SetExtra extra,
            @NonNull List<IPositionChange> changes,
            @Nullable SetPurchaserContactData setPurchaserContactData
    ) {
        Objects.requireNonNull(discount);

        this.discount = discount;
        this.extra = extra;
        this.changes = changes;
        this.setPurchaserContactData = setPurchaserContactData;
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
        bundle.putBundle(
                KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA,
                setPurchaserContactData == null ? null : setPurchaserContactData.toBundle()
        );
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

    @Nullable
    public SetPurchaserContactData getSetPurchaserContactData() {
        return setPurchaserContactData;
    }
}