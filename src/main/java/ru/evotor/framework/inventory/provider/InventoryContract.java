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
    public static final String PATH_UNCLASSIFIED_PAYABLE_SERVICES = "unclassified_payable_services";
    public static final String PATH_WEAK_ALCOHOL = "weak_alcohol";
    public static final String PATH_STRONG_ALCOHOL = "strong_alcohol";
    public static final String PATH_TOBACCO = "tobacco";

    public static final String PATH_PRODUCT_EXTENSIONS = "product_extensions";

    public static final Uri URI_PRODUCT_EXTENSIONS = Uri.withAppendedPath(BASE_URI, PATH_PRODUCT_EXTENSIONS);

    public static final String PATH_ALCOHOL_PRODUCTS = "alcohol_products";

    public static final String PATH_PRODUCT_GROUPS = "product_groups";

    public interface ProductColumns extends IdentifiedEntityColumns, MultiVariationEntityColumns {
        String VARIATION_ID_UNCLASSIFIED_PRODUCT = "NORMAL";
        String VARIATION_ID_UNCLASSIFIED_PAYABLE_SERVICE = "SERVICE";
        String VARIATION_ID_WEAK_ALCOHOL = "ALCOHOL_NOT_MARKED";
        String VARIATION_ID_STRONG_ALCOHOL = "ALCOHOL_MARKED";
        String VARIATION_ID_TOBACCO = "TOBACCO";

        String GROUP_UUID = "GROUP_UUID";
        String NAME = "NAME";
        String CODE = "CODE";
        String VENDOR_CODE = "VENDOR_CODE";
        String BARCODES = "BARCODES";
        String PURCHASE_PRICE = "PURCHASE_PRICE";
        String SELLING_PRICE = "SELLING_PRICE";
        String VAT_RATE = "VAT_RATE";
        String QUANTITY = "QUANTITY";
        String DESCRIPTION = "DESCRIPTION";
        String ALLOWED_TO_SELL = "ALLOWED_TO_SELL";
    }

    public interface UnitOfMeasurementColumns extends MultiVariationEntityColumns {
        String VARIATION_ID = "UNIT_OF_MEASUREMENT_VARIATION_ID";

        int VARIATION_ID_CUSTOM = 0;
        int VARIATION_ID_CONVENTIONAL_UNIT = 1;
        int VARIATION_ID_PIECE = 2;
        int VARIATION_ID_PACKAGING = 3;
        int VARIATION_ID_KIT = 4;
        int VARIATION_ID_KILOGRAM = 5;
        int VARIATION_ID_METER = 6;
        int VARIATION_ID_SQUARE_METER = 7;
        int VARIATION_ID_CUBIC_METER = 8;
        int VARIATION_ID_LITER = 9;

        String TYPE = "UNIT_OF_MEASUREMENT_TYPE";
        String NAME = "UNIT_OF_MEASUREMENT_NAME";
        String PRECISION = "UNIT_OF_MEASUREMENT_PRECISION";
    }

    public interface AlcoholProductColumns {
        String PRODUCT_UUID = "PRODUCT_UUID";
        String FSAR_PRODUCT_KIND_CODE = "FSAR_PRODUCT_KIND_CODE";
        String TARE_VOLUME = "TARE_VOLUME";
        String ALCOHOL_PERCENTAGE = "ALCOHOL_PERCENTAGE";
    }

    public interface ProductGroupColumns extends IdentifiedEntityColumns {
        String PARENT_GROUP_UUID = "PARENT_GROUP_UUID";
        String NAME = "NAME";
    }
}