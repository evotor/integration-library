package ru.evotor.framework.core.action.command.print_receipt_command;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.Objects;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.IntegrationManagerCallback;
import ru.evotor.framework.core.IntegrationManagerImpl;
import ru.evotor.framework.core.action.datamapper.ReceiptMapper;
import ru.evotor.framework.core.action.event.receipt.changes.position.SetExtra;
import ru.evotor.framework.core.action.event.receipt.changes.position.SetPrintGroup;
import ru.evotor.framework.receipt.Receipt;


public class PrintPaybackReceiptCommand implements IBundlable {

    public static final String NAME = "evo.v2.receipt.payback.printReceipt";
    private static final String KEY_RECEIPT = "receipt";
    private static final String KEY_RECEIPT_EXTRA = "extra";
    private static final String KEY_PRINT_GROUP = "printGroup";

    @Nullable
    public static PrintPaybackReceiptCommand create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new PrintPaybackReceiptCommand(
                ReceiptMapper.from(bundle.getBundle(KEY_RECEIPT)),
                SetPrintGroup.from(bundle.getBundle(KEY_PRINT_GROUP)),
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA))
        );
    }

    @NonNull
    private final Receipt receipt;
    @NonNull
    private SetPrintGroup printGroup;
    @Nullable
    private final SetExtra extra;

    public PrintPaybackReceiptCommand(@NonNull Receipt receipt, @Nullable SetPrintGroup printGroup, @Nullable SetExtra extra) {
        this.receipt = receipt;
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
                .call(PrintPaybackReceiptCommand.NAME,
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
        bundle.putBundle(KEY_RECEIPT, ReceiptMapper.toBundle(receipt));
        bundle.putBundle(KEY_PRINT_GROUP, printGroup.toBundle());
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra == null ? null : extra.toBundle());
        return bundle;
    }

    @NonNull
    public Receipt getReceipt() {
        return receipt;
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
