package ru.evotor.framework.core.action.event.reports;

import android.os.Bundle;
import android.support.annotation.NonNull;

import ru.evotor.IBundlable;

public abstract class ReportEvent implements IBundlable {

    public ReportEvent() {
    }

    @NonNull
    @Override
    public Bundle toBundle() {
        return new Bundle();
    }
}
