package ru.evotor.framework.core.action.event.users;

import android.os.Bundle;
import android.support.annotation.NonNull;

import ru.evotor.IBundlable;

public abstract class UserEvent implements IBundlable {

    protected UserEvent() {

    }

    protected UserEvent(@NonNull Bundle extras) {
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        return new Bundle();
    }
}
