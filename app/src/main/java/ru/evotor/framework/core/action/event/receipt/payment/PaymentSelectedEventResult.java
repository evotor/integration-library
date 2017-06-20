package ru.evotor.framework.core.action.event.receipt.payment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import ru.evotor.IBundlable;
import ru.evotor.framework.Utils;
import ru.evotor.framework.core.action.datamapper.ChangesMapper;
import ru.evotor.framework.core.action.datamapper.PrintGroupMapper;
import ru.evotor.framework.core.action.event.receipt.changes.IChange;
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra;
import ru.evotor.framework.core.action.event.receipt.changes.position.SetPrintGroup;

public class PaymentSelectedEventResult implements IBundlable {

    private static final String KEY_RECEIPT_EXTRA = "extra";
    private static final String KEY_PRINT_GROUPS = "printGroups";

    @Nullable
    public static PaymentSelectedEventResult create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new PaymentSelectedEventResult(
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA)),
                Utils.filterByClass(
                        ChangesMapper.INSTANCE.create(bundle.getParcelableArray(KEY_PRINT_GROUPS)),
                        SetPrintGroup.class
                )
        );
    }

    @Nullable
    private final SetExtra extra;
    @NonNull
    private final List<SetPrintGroup> printGroups;

    public PaymentSelectedEventResult(
            @Nullable SetExtra extra,
            @NonNull List<SetPrintGroup> printGroups
    ) {
        this.extra = extra;
        this.printGroups = printGroups;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra == null ? null : extra.toBundle());
        Parcelable[] printGroups = new Parcelable[this.printGroups.size()];
        for (int i = 0; i < printGroups.length; i++) {
            IChange change = this.printGroups.get(i);
            printGroups[i] = ChangesMapper.INSTANCE.toBundle(change);
        }
        bundle.putParcelableArray(KEY_PRINT_GROUPS, printGroups);
        return bundle;
    }

    @Nullable
    public SetExtra getExtra() {
        return extra;
    }

    @NonNull
    public List<SetPrintGroup> getPrintGroups() {
        return printGroups;
    }
}