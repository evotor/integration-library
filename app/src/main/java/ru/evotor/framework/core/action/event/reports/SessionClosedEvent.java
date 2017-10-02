package ru.evotor.framework.core.action.event.reports;

import android.support.annotation.Nullable;

public class SessionClosedEvent extends ReportEvent {
    public static final String BROADCAST_ACTION_SESSION_CLOSED = "evotor.intent.action.reports.SESSION_CLOSED";

    public SessionClosedEvent() {
        super();
    }

    @Nullable
    public static SessionClosedEvent create() {
        return new SessionClosedEvent();
    }
}