package ru.evotor.framework.core.action.command.clear_receipt_command;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.Objects;

import ru.evotor.IBundlable;
import ru.evotor.framework.core.ICanStartActivity;
import ru.evotor.framework.core.IntegrationManagerCallback;
import ru.evotor.framework.core.IntegrationManagerImpl;

public class ClearPaybackReceiptCommand implements IBundlable {

    public static final String NAME = "evo.v2.receipt.payback.clearReceipt";
    private static final String KEY_RECEIPT_UUID = "receiptUuid";

    @Nullable
    public static ClearPaybackReceiptCommand create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID);
        if (receiptUuid == null) {
            return null;
        }
        return new ClearPaybackReceiptCommand(
                receiptUuid
        );
    }

    @NonNull
    private final String receiptUuid;


    public ClearPaybackReceiptCommand(@NonNull String receiptUuid) {
        this.receiptUuid = receiptUuid;
    }

    public void process(final Context context, final ICanStartActivity activityStarter, IntegrationManagerCallback callback) {
        Objects.requireNonNull(activityStarter);
        Objects.requireNonNull(context);

        List<ComponentName> componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(NAME, context.getApplicationContext());
        if (componentNameList == null || componentNameList.isEmpty()) {
            return;
        }
        new IntegrationManagerImpl(context.getApplicationContext())
                .call(ClearPaybackReceiptCommand.NAME,

                        componentNameList.get(0),
                        this.toBundle(),
                        activityStarter,
                        callback,
                        new Handler(Looper.getMainLooper())
                );
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RECEIPT_UUID, receiptUuid);
        return bundle;
    }

    @NonNull
    public String getReceiptUuid() {
        return receiptUuid;
    }
}