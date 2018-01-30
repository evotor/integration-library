package ru.evotor.framework.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class Error {
    private final int code;
    @NonNull
    private final String message;
    @Nullable
    private final Bundle data;

    public Error(int code, @NonNull String message, @Nullable Bundle data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    @Nullable
    public Bundle getData() {
        return data;
    }
}
