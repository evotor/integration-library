package ru.evotor.framework.inventory.provider;

import android.net.Uri;

import ru.evotor.framework.core.DoNotUseThis;
import ru.evotor.framework.provider.IdentifiedEntityColumns;

@DoNotUseThis()
public final class InventoryContract {
    private InventoryContract() {
    }

    public static final String AUTHORITY = "ru.evotor.framework.inventory";

    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public interface ProductColumns extends IdentifiedEntityColumns {
        String VARIATION_ID = "VARIATION_ID";
        String GROUP_UUID = "GROUP_UUID";
        String NAME = "NAME";
        String CODE = "CODE";
        String VENDOR_CODE = "VENDOR_CODE";
        String PRICE = "PRICE";
        String VAT_RATE = "VAT_RATE";
        String QUANTITY_EXACT_VALUE = "QUANTITY_EXACT_VALUE";
        String QUANTITY_SCALE = "QUANTITY_SCALE";
        String UNIT_OF_MEASUREMENT_VARIATION_ID = "UNIT_OF_MEASUREMENT_VARIATION_ID";
        String UNIT_OF_MEASUREMENT_TYPE = "UNIT_OF_MEASUREMENT_TYPE";
        String UNIT_OF_MEASUREMENT_NAME = "UNIT_OF_MEASUREMENT_NAME";
        String DESCRIPTION = "DESCRIPTION";
    }

    public interface ProductExtensionColumns {
        String PRODUCT_UUID = "PRODUCT_UUID";
    }

    public interface AlcoholProductColumns extends ProductExtensionColumns {
        String FSRAR_PRODUCT_KIND_CODE = "FSRAR_PRODUCT_KIND_CODE";
        String TARE_VOLUME_EXACT_VALUE = "TARE_VOLUME_EXACT_VALUE";
        String TARE_VOLUME_SCALE = "TARE_VOLUME_SCALE";
        String TARE_VOLUME_UNIT_OF_MEASUREMENT_VARIATION_ID = "TARE_VOLUME_UNIT_OF_MEASUREMENT_VARIATION_ID";
        String TARE_VOLUME_UNIT_OF_MEASUREMENT_NAME = "TARE_VOLUME_UNIT_OF_MEASUREMENT_NAME";
        String ALCOHOL_PERCENTAGE = "ALCOHOL_PERCENTAGE";
    }

    public static final class Product implements ProductColumns, AlcoholProductColumns {
        private Product() {

        }

        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "products");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ru.evotor.framework.inventory.product.Product";

        public static final Uri UNCLASSIFIED_PRODUCTS_URI = Uri.withAppendedPath(CONTENT_URI, "unclassified_products");
        public static final Uri UNCLASSIFIED_SERVICES_URI = Uri.withAppendedPath(CONTENT_URI, "unclassified_services");
        public static final Uri WEAK_ALCOHOL_URI = Uri.withAppendedPath(CONTENT_URI, "weak_alcohol");
        public static final Uri STRONG_ALCOHOL_URI = Uri.withAppendedPath(CONTENT_URI, "strong_alcohol");
        public static final Uri TOBACCO_URI = Uri.withAppendedPath(CONTENT_URI, "tobacco");

        public static final String VARIATION_ID_UNCLASSIFIED_PRODUCT = "NORMAL";
        public static final String VARIATION_ID_UNCLASSIFIED_SERVICE = "SERVICE";
        public static final String VARIATION_ID_WEAK_ALCOHOL = "ALCOHOL_NOT_MARKED";
        public static final String VARIATION_ID_STRONG_ALCOHOL = "ALCOHOL_MARKED";
        public static final String VARIATION_ID_TOBACCO = "TOBACCO_MARKED";
    }

    public static final class ProductExtension implements AlcoholProductColumns {
        private ProductExtension() {

        }

        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "product_extensions");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ru.evotor.framework.inventory.product.extension.ProductExtension";

        public static final Uri ALCOHOL_PRODUCTS_URI = Uri.withAppendedPath(CONTENT_URI, "alcohol_products");
    }

    public interface ProductGroupColumns extends IdentifiedEntityColumns {
        String PARENT_GROUP_UUID = "PARENT_GROUP_UUID";
        String NAME = "NAME";
    }

    public static final class ProductGroup implements ProductGroupColumns {
        private ProductGroup() {

        }

        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "product_groups");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ru.evotor.framework.inventory.ProductGroup";
    }

    public interface BarcodeColumns {
        String PRODUCT_UUID = "COMMODITY_UUID";
        String VALUE = "BARCODE";
    }

    public static final class Barcode implements BarcodeColumns {
        private Barcode() {

        }

        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "barcodes");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ru.evotor.framework.inventory.product.Barcode";

        public static Uri getParticularBarcodeUri(String barcode) {
            return Uri.withAppendedPath(CONTENT_URI, barcode);
        }

        public static Uri getProductsByBarcodeUri(String barcode) {
            return Uri.withAppendedPath(getParticularBarcodeUri(barcode), "products");
        }

        public static Uri getPositionsByBarcodeUri(String barcode) {
            return Uri.withAppendedPath(getParticularBarcodeUri(barcode), "positions");
        }
    }
}