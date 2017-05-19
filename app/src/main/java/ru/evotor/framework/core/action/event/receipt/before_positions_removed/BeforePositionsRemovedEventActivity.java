package ru.evotor.framework.core.action.event.receipt.before_positions_removed;

import ru.evotor.framework.core.IntegrationActivity;

/**
 * Created by a.kuznetsov on 10/05/2017.
 */

public class BeforePositionsRemovedEventActivity extends IntegrationActivity {
    public void setIntegrationResult(BeforePositionsRemovedEventResult result) {
        setIntegrationResult(result == null ? null : result.toBundle());
    }

    public BeforePositionsRemovedEvent getEvent() {
        return BeforePositionsRemovedEvent.create(getSourceBundle());
    }
}
