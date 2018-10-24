package ru.evotor.framework.core.action.event.inventory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.evotor.IBundlable;

/**
 * @deprecated Используйте {@link ru.evotor.framework.inventory.event.ProductEvent}
 */
@Deprecated
public abstract class ProductEvent implements IBundlable {
    private static final String KEY_PRODUCT_UUID = "productUuid";

    @Nullable
    private final String productUuid;

    public ProductEvent(@Nullable String productUuid) {
        this.productUuid = productUuid;
    }

    @Nullable
    static String getProductUuid(@Nullable Bundle bundle) {
        return bundle == null ? null : bundle.getString(KEY_PRODUCT_UUID);
    }

    @NonNull
    @Override
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
