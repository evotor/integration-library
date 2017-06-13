package ru.evotor.framework.core.action.event.inventory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class ProductEvent {
    private static final String KEY_PRODUCT_UUID = "productUuid";

    @Nullable
    private final String productUuid;

    public ProductEvent(Bundle extras) {
        this(
                extras.getString(KEY_PRODUCT_UUID)
        );
    }

    public ProductEvent(@Nullable String productUuid) {
        this.productUuid = productUuid;
    }

    @NonNull
    public Bundle toBundle() {
        Bundle result = new Bundle();
        result.putString(KEY_PRODUCT_UUID, productUuid);
        return result;
    }

    @Nullable
    public String getProductUuid() {
        return productUuid;
    }
}
