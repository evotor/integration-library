package ru.evotor.framework.core.action.event.session;

import android.support.annotation.Nullable;

public class SessionClosedEvent extends SessionEvent {
    public static final String BROADCAST_ACTION_SESSION_CLOSED = "evotor.intent.action.reports.SESSION_CLOSED";

    public SessionClosedEvent() {
        super();
    }

    @Nullable
    public static SessionClosedEvent create() {
        return new SessionClosedEvent();
    }
}