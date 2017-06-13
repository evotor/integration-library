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

public class ClearSellReceiptCommand implements IBundlable {

    public static final String NAME = "evo.v2.receipt.sell.clearReceipt";
    private static final String KEY_RECEIPT_UUID = "receiptUuid";

    @Nullable
    public static ClearSellReceiptCommand create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }

        String receiptUuid = bundle.getString(KEY_RECEIPT_UUID);
        if (receiptUuid == null) {
            return null;
        }
        return new ClearSellReceiptCommand(
                receiptUuid
        );
    }

    @NonNull
    private final String receiptUuid;

    public ClearSellReceiptCommand(@NonNull String receiptUuid) {
        Objects.requireNonNull(receiptUuid);
        this.receiptUuid = receiptUuid;
    }

    public void process(@NonNull final Context context, @NonNull final ICanStartActivity activityStarter, IntegrationManagerCallback callback) {
        Objects.requireNonNull(activityStarter);
        Objects.requireNonNull(context);

        List<ComponentName> componentNameList = IntegrationManagerImpl.convertImplicitIntentToExplicitIntent(NAME, context.getApplicationContext());
        if (componentNameList == null || componentNameList.isEmpty()) {
            return;
        }
        new IntegrationManagerImpl(context.getApplicationContext())
                .call(ClearSellReceiptCommand.NAME,
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
