package ru.evotor.framework.core.action.event.users;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserChangedEvent extends UserEvent {
    public static final String BROADCAST_ACTION_USER_CHANGED = "evotor.intent.event.user.CHANGED";

    public UserChangedEvent() {
        super();
    }

    private UserChangedEvent(@NonNull Bundle extra) {
        super(extra);
    }

    @Nullable
    public static UserChangedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new UserChangedEvent(bundle);
    }
}