package ru.evotor.framework.core.action.event.cash_operations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;

public abstract class CashOperationEvent implements IBundlable {
    private static final String KEY_DOCUMENT_UUID = "documentUuid";

    @NonNull
    private final String documentUuid;

    CashOperationEvent(@NonNull String documentUuid) {
        this.documentUuid = documentUuid;
    }

    @Nullable
    static String getDocumentUuid(@Nullable Bundle bundle) {
        return bundle == null ? null : bundle.getString(KEY_DOCUMENT_UUID);
    }

    @Override
    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_DOCUMENT_UUID, documentUuid);
        return result;
    }

    @NonNull
    public String getDocumentUuid() {
        return documentUuid;
    }
}
