package ru.evotor.framework.core.action.event.session;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SessionOpenedEvent extends SessionEvent {
    public static final String BROADCAST_ACTION_SESSION_OPENED = "evotor.intent.action.reports.SESSION_OPENED";

    public SessionOpenedEvent() {
        super();
    }

    private SessionOpenedEvent(@NonNull Bundle extra) {
        super(extra);
    }

    @Nullable
    public static SessionOpenedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new SessionOpenedEvent(bundle);
    }
}