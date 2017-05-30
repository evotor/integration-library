package ru.evotor.framework.core.action.event.receipt.discount;

import ru.evotor.framework.core.IntegrationActivity;

public class ReceiptDiscountEventActivity extends IntegrationActivity {
    public void setIntegrationResult(ReceiptDiscountEventResult result) {
        setIntegrationResult(result == null ? null : result.toBundle());
    }

    public ReceiptDiscountEvent getEvent() {
        return ReceiptDiscountEvent.create(getSourceBundle());
    }
}
