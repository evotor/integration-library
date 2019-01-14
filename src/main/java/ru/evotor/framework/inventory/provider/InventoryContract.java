package ru.evotor.framework.inventory.provider;

import android.net.Uri;

import ru.evotor.framework.provider.IdentifiedEntityColumns;
import ru.evotor.framework.provider.MultiVariationEntityColumns;

public final class InventoryContract {
    private InventoryContract() {
    }

    public static final String AUTHORITY = "ru.evotor.framework.inventory";

    public static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);

    public static Uri getBarcodeUri(String barcode) {
        return Uri.withAppendedPath(BASE_URI, barcode);
    }

    public static final String PATH_PRODUCTS = "products";

    public static final String PATH_POSITIONS = "positions";

    public static final Uri URI_PRODUCTS = Uri.withAppendedPath(BASE_URI, PATH_PRODUCTS);

    public static final String PATH_UNCLASSIFIED_PRODUCTS = "unclassified_products";
    public static final String PATH_UNCLASSIFIED_SERVICES = "unclassified_services";
    public static final String PATH_WEAK_ALCOHOL = "weak_alcohol";
    public static final String PATH_STRONG_ALCOHOL = "strong_alcohol";
    public static final String PATH_TOBACCO = "tobacco";

    public static final String PATH_PRODUCT_EXTENSIONS = "product_extensions";

    public static final Uri URI_PRODUCT_EXTENSIONS = Uri.withAppendedPath(BASE_URI, PATH_PRODUCT_EXTENSIONS);

    public static final String PATH_ALCOHOL_PRODUCTS = "alcohol_products";

    public static final String PATH_PRODUCT_GROUPS = "product_groups";

    public interface ProductColumns extends IdentifiedEntityColumns, MultiVariationEntityColumns {
        String VARIATION_ID_UNCLASSIFIED_PRODUCT = "NORMAL";
        String VARIATION_ID_UNCLASSIFIED_SERVICE = "SERVICE";
        String VARIATION_ID_WEAK_ALCOHOL = "ALCOHOL_NOT_MARKED";
        String VARIATION_ID_STRONG_ALCOHOL = "ALCOHOL_MARKED";
        String VARIATION_ID_TOBACCO = "TOBACCO";

        String GROUP_UUID = "GROUP_UUID";
        String NAME = "NAME";
        String CODE = "CODE";
        String VENDOR_CODE = "VENDOR_CODE";
        String PURCHASE_PRICE = "PRICE";
        String SELLING_PRICE = "SELLING_PRICE";
        String VAT_RATE = "VAT_RATE";
        String DESCRIPTION = "DESCRIPTION";
    }

    public interface AlcoholProductColumns {
        String PRODUCT_UUID = "PRODUCT_UUID";
        String FSRAR_PRODUCT_KIND_CODE = "FSRAR_PRODUCT_KIND_CODE";
        String TARE_VOLUME = "TARE_VOLUME";
        String ALCOHOL_PERCENTAGE = "ALCOHOL_PERCENTAGE";
    }

    public interface ProductGroupColumns extends IdentifiedEntityColumns {
        String PARENT_GROUP_UUID = "PARENT_GROUP_UUID";
        String NAME = "NAME";
    }
}