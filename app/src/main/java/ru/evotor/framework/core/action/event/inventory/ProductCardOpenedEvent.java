package ru.evotor.framework.core.action.event.inventory;

import android.os.Bundle;

import java.math.BigDecimal;

import ru.evotor.framework.core.action.event.cash_operations.CashOperationEvent;

public class ProductCardOpenedEvent extends ProductEvent {
    public static final String BROADCAST_ACTION_PRODUCT_CARD_OPENED = "evotor.intent.action.inventory.CARD_OPEN";

    public ProductCardOpenedEvent(Bundle extras) {
        super(extras);
    }

    public ProductCardOpenedEvent(String productUuid) {
        super(productUuid);
    }
}