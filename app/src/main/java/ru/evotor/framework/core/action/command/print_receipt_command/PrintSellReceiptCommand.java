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
import ru.evotor.framework.receipt.Receipt;


public class PrintSellReceiptCommand implements IBundlable {

    public static final String NAME = "evo.v2.receipt.sell.printReceipt";
    private static final String KEY_RECEIPT = "receipt";
    private static final String KEY_RECEIPT_EXTRA = "extra";

    @Nullable
    public static PrintSellReceiptCommand create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new PrintSellReceiptCommand(
                ReceiptMapper.from(bundle.getBundle(KEY_RECEIPT)),
                SetExtra.from(bundle.getBundle(KEY_RECEIPT_EXTRA))
        );
    }

    @NonNull
    private final Receipt receipt;
    @Nullable
    private final SetExtra extra;

    public PrintSellReceiptCommand(@NonNull Receipt receipt, @Nullable SetExtra extra) {
        this.receipt = receipt;
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
        bundle.putBundle(KEY_RECEIPT, ReceiptMapper.toBundle(receipt));
        bundle.putBundle(KEY_RECEIPT_EXTRA, extra == null ? null : extra.toBundle());
        return bundle;
    }

    @NonNull
    public Receipt getReceipt() {
        return receipt;
    }

    @Nullable
    public SetExtra getExtra() {
        return extra;
    }

}
