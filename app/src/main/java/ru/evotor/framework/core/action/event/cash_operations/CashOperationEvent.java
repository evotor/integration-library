package ru.evotor.framework.core.action.event.cash_operations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class CashOperationEvent {
    private static final String KEY_DOCUMENT_UUID = "documentUuid";

    @Nullable
    private final String documentUuid;

    public CashOperationEvent(@NonNull Bundle extras) {
        this(
                extras.getString(KEY_DOCUMENT_UUID)
        );
    }

    public CashOperationEvent(@Nullable String documentUuid) {
        this.documentUuid = documentUuid;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_DOCUMENT_UUID, documentUuid);
        return result;
    }

    @Nullable
    public String getDocumentUuid() {
        return documentUuid;
    }
}
