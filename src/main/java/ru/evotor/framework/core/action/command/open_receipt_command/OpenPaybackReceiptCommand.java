package ru.evotor.framework.core.action.command.open_receipt_command;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.evotor.IBundlable;
import ru.evotor.framework.Utils;
import ru.evotor.framework.core.IntegrationManagerCallback;
import ru.evotor.framework.core.IntegrationManagerImpl;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.PositionAdd;
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetExtra;
import ru.evotor.framework.core.action.event.receipt.changes.receipt.SetPurchaserContactData;

/**
 * Команда открытия чека возврата.
 */
public class OpenPaybackReceiptCommand implements IBundlable {

    public static final String NAME = "evo.v2.receipt.payback.openReceipt";
    private static final String KEY_CHANGES = "changes";
    private static final String KEY_RECEIPT_EXTRA = "extra";
    private static final String KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA = "setPurchaserContactData";
    private static final String KEY_SELL_RECEIPT_UUID = "sellReceiptUuid";

    @Nullable
    public static OpenPaybackReceiptCommand create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        Parcelable[] changesParcelable = bundle.getParcelableArray(KEY_CHANGES);
        return new OpenPaybackReceiptCommand(
                Utils.filterByClass(
                        ChangesMapper.INSTANCE.create(changesParcelable),
                        PositionAdd.class
                ),
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)),
                SetPurchaserContactData.from(bundle.getBundle(KEY_RECEIPT_SET_PURCHASER_CONTACT_DATA)),
                bundle.getString(KEY_SELL_RECEIPT_UUID, null)
        );
    }

    @NonNull
    private final List<PositionAdd> changes;
    @Nullable
    private final SetExtra extra;
    @Nullable
    private final SetPurchaserContactData setPurchaserContactData;
    @Nullable
    private final String sellReceiptUuid;

    /**
     * Используйте конструктор с setPurchaserContactData
     *
     * @param changes
     * @param extraChange
     */
    @Deprecated
    public OpenPaybackReceiptCommand(
            @Nullable List<PositionAdd> changes,
            @Nullable SetExtra extraChange
    ) {
        this(changes, extraChange, null, null);
    }

    public OpenPaybackReceiptCommand(
            @Nullable List<PositionAdd> changes,
            @Nullable SetExtra extraChange,
            @Nullable SetPurchaserContactData setPurchaserContactData
    ) {
        this(changes, extraChange, setPurchaserContactData, null);
    }

    public OpenPaybackReceiptCommand(
            @Nullable List<PositionAdd> changes,
            @Nullable SetExtra extraChange,
            @Nullable SetPurchaserContactData setPurchaserContactData,
            @Nullable String sellReceiptUuid
    ) {
        this.changes = new ArrayList<>();
        if (changes != null) {
            this.changes.addAll(changes);
        }
        this.extra = extraChange;
        this.setPurchaserContactData = setPurchaserContactData;
        this.sellReceiptUuid = sellReceiptUuid;
    }

    public void process(@NonNull final Activity activity, IntegrationManagerCallback callback) {
        Objects.requireNonNull(activity);

        List<ComponentName> componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(NAME, activity.getApplicationContext());
        if (componentNameList == null || componentNameList.isEmpty()) {
            return;
        }
        new IntegrationManagerImpl(activity.getApplicationContext())
                .call(OpenPaybackReceiptCommand.NAME,

                        componentNameList.get(0),
                        this,
                        activity,
                        callback,
                        new Handler(Looper.getMainLooper())
                );
    }

    @NonNull
    @Override
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
        bundle.putString(KEY_SELL_RECEIPT_UUID, sellReceiptUuid);

        return bundle;
    }

    @NonNull
    public List<PositionAdd> getChanges() {
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

    @Nullable
    public String getSellReceiptUuid() {
        return sellReceiptUuid;
    }
}
