package ru.evotor.framework.core.action.event.session;

import android.os.Bundle;
import android.support.annotation.NonNull;

import ru.evotor.IBundlable;

public abstract class SessionEvent implements IBundlable {

    public SessionEvent() {
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        return new Bundle();
    }
}
