package ru.evotor.framework.core.action.event.receipt.discount;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.evotor.IBundlable;
import ru.evotor.framework.BundleUtils;
import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.IPositionChange;
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetExtra;
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetPurchaserContactData;
import ru.evotor.framework.receipt.AppliedLoyaltyData;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReceiptDiscountEventResult implements IBundlable {

    private static final String KEY_DISCOUNT = "discount";
    private static final String KEY_RECEIPT_EXTRA = "extra";
    private static final String KEY_CHANGES = "changes";
    private static final String KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA = "setPurchaserContactData";

    private static final String KEY_POSITION_UUID_TO_DISCOUNT_MAP = "positionUuidToDiscountMap";

    private static final String KEY_RECEIPT_LOYALTY_APP_DATA = "appliedLoyaltyData";

    @Nullable
    public static ReceiptDiscountEventResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        BigDecimal discount = BundleUtils.getMoney(bundle, KEY_DISCOUNT);
        if (discount == null) {
            return null;
        }
        Map<String, BigDecimal> positionUuidToDiscountMap = null;
        if(bundle.containsKey(KEY_POSITION_UUID_TO_DISCOUNT_MAP)) {
            positionUuidToDiscountMap = (Map<String, BigDecimal>) bundle.getSerializable(KEY_POSITION_UUID_TO_DISCOUNT_MAP);
        }
        return new ReceiptDiscountEventResult(
                discount,
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)),
                Utils.filterByClass(
                        ChangesMapper.INSTANCE.create(bundle.getParcelableArray(KEY_CHANGES)),
                        IPositionChange.class
                ),
                SetPurchaserContactData.from(bundle.getBundle(KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA)),
                positionUuidToDiscountMap,
                AppliedLoyaltyData.from(bundle.getBundle(KEY_RECEIPT_LOYALTY_APP_DATA))
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

    @Nullable
    private final Map<String, BigDecimal> positionUuidToDiscountMap;

    @Nullable
    private final AppliedLoyaltyData appliedLoyaltyData;

    public ReceiptDiscountEventResult(
            @NonNull BigDecimal discount,
            @Nullable SetExtra extra,
            @NonNull List<IPositionChange> changes,
            @Nullable SetPurchaserContactData setPurchaserContactData
    ) {
        this(discount,extra,changes,setPurchaserContactData,null,null);
    }

    public ReceiptDiscountEventResult(
            @NonNull BigDecimal discount,
            @Nullable SetExtra extra,
            @NonNull List<IPositionChange> changes,
            @Nullable SetPurchaserContactData setPurchaserContactData,
            @Nullable Map<String, BigDecimal> positionUuidToDiscountMap
    ) {
        this(discount,extra,changes,setPurchaserContactData,positionUuidToDiscountMap,null);
    }

    public ReceiptDiscountEventResult(
            @NonNull BigDecimal discount,
            @Nullable SetExtra extra,
            @NonNull List<IPositionChange> changes,
            @Nullable SetPurchaserContactData setPurchaserContactData,
            @Nullable Map<String, BigDecimal> positionUuidToDiscountMap,
            @Nullable AppliedLoyaltyData appliedLoyaltyData
    ) {
        Objects.requireNonNull(discount);

        this.discount = discount;
        this.extra = extra;
        this.changes = changes;
        this.setPurchaserContactData = setPurchaserContactData;
        this.positionUuidToDiscountMap = positionUuidToDiscountMap;
        this.appliedLoyaltyData = appliedLoyaltyData;
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
        if (positionUuidToDiscountMap != null) {
            bundle.putSerializable(KEY_POSITION_UUID_TO_DISCOUNT_MAP, (Serializable) positionUuidToDiscountMap);
        }
        bundle.putBundle(
                KEY_RECEIPT_LOYALTY_APP_DATA,
                appliedLoyaltyData == null ? null : appliedLoyaltyData.toBundle()
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

    @Nullable
    public Map<String, BigDecimal> getPositionUuidToDiscountMap() {
        return positionUuidToDiscountMap;
    }

    @Nullable
    public AppliedLoyaltyData getAppliedLoyaltyData(){
        return appliedLoyaltyData;
    }
}
