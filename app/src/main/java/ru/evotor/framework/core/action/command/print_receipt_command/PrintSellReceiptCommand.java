package ru.evotor.framework.core.action.command.print_receipt_command;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra;
import ru.evotor.framework.core.action.event.receipt.changes.position.SetPrintGroup;


public class PrintSellReceiptCommand implements IBundlable {

    public static final String NAME = "evo.v2.receipt.sell.printReceipt";
    private static final String KEY_CHANGES = "changes";
    private static final String KEY_RECEIPT_EXTRA = "extra";
    private static final String KEY_PRINT_GROUP = "printGroup";

    @Nullable
    public static PrintSellReceiptCommand create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Parcelable[] changesParcelable = bundle.getParcelableArray(KEY_CHANGES);
        return new PrintSellReceiptCommand(
                Utils.filterByClass(
                        ChangesMapper.INSTANCE.create(changesParcelable),
                        PositionAdd.class
                ),
                SetPrintGroup.from(bundle.getBundle(KEY_PRINT_GROUP)),
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA))
        );
    }

    @NonNull
    private final List<PositionAdd> changes;
    @NonNull
    private SetPrintGroup printGroup;
    @Nullable
    private final SetExtra extra;

    public PrintSellReceiptCommand(@Nullable List<PositionAdd> changes, @Nullable SetPrintGroup printGroup, @Nullable SetExtra extra) {
        this.changes = new ArrayList<>();
        if (changes != null) {
            this.changes.addAll(changes);
        }
        if (printGroup != null) {
            this.printGroup = printGroup;
        }
        this.extra = extra;
    }

    public void process(@NonNull final Activity activity, IntegrationManagerCallback callback) {
        Objects.requireNonNull(activity);

        List<ComponentName> componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(NAME, activity.getApplicationContext());
        if (componentNameList == null || componentNameList.isEmpty()) {
            return;
        }
        new IntegrationManagerImpl(activity.getApplicationContext())
                .call(PrintSellReceiptCommand.NAME,
                        componentNameList.get(0),
                        this,
                        activity,
                        callback,
                        new Handler(Looper.getMainLooper())
                );
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
        bundle.putBundle(KEY_PRINT_GROUP, printGroup.toBundle());
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra == null ? null : extra.toBundle());
        return bundle;
    }

    @NonNull
    public List<PositionAdd> getChanges() {
        return changes;
    }

    @NonNull
    public SetPrintGroup getPrintGroup() {
        return printGroup;
    }

    @Nullable
    public SetExtra getExtra() {
        return extra;
    }

}
