package ru.evotor.framework.core.action.event.cash_operations;

import android.os.Bundle;

public abstract class CashOperationEvent {
    private static final String KEY_DOCUMENT_UUID = "documentUuid";

    private final String documentUuid;

    public CashOperationEvent(Bundle extras) {
        this(
                extras.getString(KEY_DOCUMENT_UUID)
        );
    }

    public CashOperationEvent(String documentUuid) {
        this.documentUuid = documentUuid;
    }

    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_DOCUMENT_UUID, documentUuid);
        return result;
    }

    public String getDocumentUuid() {
        return documentUuid;
    }
}
