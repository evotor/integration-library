package ru.evotor.framework.core.action.event.receipt.before_position_added;

import ru.evotor.framework.core.IntegrationActivity;

/**
 * Created by a.kuznetsov on 10/05/2017.
 */

public class BeforePositionsAddedEventActivity extends IntegrationActivity {
    public void setIntegrationResult(BeforePositionsAddedEventResult result) {
        setIntegrationResult(result == null ? null : result.toBundle());
    }

    public BeforePositionsAddedEvent getEvent() {
        return BeforePositionsAddedEvent.create(getSourceBundle());
    }
}
