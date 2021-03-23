package ru.evotor.framework.core.action.event.session;

import android.os.Bundle;

import androidx.annotation.NonNull;
import ru.evotor.IBundlable;

public abstract class SessionEvent implements IBundlable {

    protected SessionEvent() {

    }

    protected SessionEvent(@NonNull Bundle extras) {
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        return new Bundle();
    }
}
