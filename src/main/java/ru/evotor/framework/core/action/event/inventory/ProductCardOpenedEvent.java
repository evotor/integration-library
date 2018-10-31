package ru.evotor.framework.core.action.event.inventory;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @deprecated Используйте {@link ru.evotor.framework.inventory.event.ProductCardOpenedEvent}
 */
@Deprecated
public class ProductCardOpenedEvent extends ProductEvent {
    public static final String BROADCAST_ACTION_PRODUCT_CARD_OPENED = "evotor.intent.action.inventory.CARD_OPEN";

    public ProductCardOpenedEvent(@Nullable String productUuid) {
        super(productUuid);
    }

    @Nullable
    public static ProductCardOpenedEvent create(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new ProductCardOpenedEvent(getProductUuid(bundle));
    }
}