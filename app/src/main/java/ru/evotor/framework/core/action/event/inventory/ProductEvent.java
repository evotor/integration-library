package ru.evotor.framework.core.action.event.inventory;

import android.os.Bundle;

public abstract class ProductEvent {
    private static final String KEY_PRODUCT_UUID = "productUuid";

    private final String productUuid;

    public ProductEvent(Bundle extras) {
        this(
                extras.getString(KEY_PRODUCT_UUID)
        );
    }

    public ProductEvent(String productUuid) {
        this.productUuid = productUuid;
    }

    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_PRODUCT_UUID, productUuid);
        return result;
    }

    public String getProductUuid() {
        return productUuid;
    }
}
