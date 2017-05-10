package ru.evotor.framework.core.action.event.receipt.before_position_added;

import ru.evotor.framework.core.IntegrationActivity;

/**
 * Created by a.kuznetsov on 10/05/2017.
 */

public class BeforePositionAddedEventActivity extends IntegrationActivity {
    public void setIntegrationResult(BeforePositionAddedEventResult result) {
        setIntegrationResult(result == null ? null : result.toBundle());
    }
}
