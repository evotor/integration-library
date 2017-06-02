package ru.evotor.framework.core.action.event.receipt.before_positions_edited;

import ru.evotor.framework.core.IntegrationActivity;

/**
 * Created by a.kuznetsov on 10/05/2017.
 */

public class BeforePositionsEditedEventActivity extends IntegrationActivity {
    public void setIntegrationResult(BeforePositionsEditedEventResult result) {
        setIntegrationResult(result == null ? null : result.toBundle());
    }

    public BeforePositionsEditedEvent getEvent() {
        return new BeforePositionsEditedEvent(getSourceBundle());
    }
}
