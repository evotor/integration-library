package ru.evotor.framework.core.action.event.session;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class SessionClosedEvent extends SessionEvent {
    public static final String BROADCAST_ACTION_SESSION_CLOSED = "evotor.intent.action.reports.SESSION_CLOSED";

    public SessionClosedEvent() {
        super();
    }

    private SessionClosedEvent(@NonNull Bundle extra) {
        super(extra);
    }

    @Nullable
    public static SessionClosedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new SessionClosedEvent(bundle);
    }
}