package ru.evotor.framework.inventory.provider

import android.net.Uri

object InventoryContract {
    const val AUTHORITY = "ru.evotor.framework.inventory"

    val BASE_URI: Uri = Uri.parse("content://$AUTHORITY")

    fun getBarcodeUri(barcode: String): Uri = Uri.withAppendedPath(BASE_URI, barcode)

    const val PATH_PRODUCTS = "products"

    const val PATH_POSITIONS = "positions"

    val URI_PRODUCTS: Uri = Uri.withAppendedPath(BASE_URI, PATH_PRODUCTS)

    const val PATH_UNCLASSIFIED_PRODUCTS = "unclassified_products"
    const val PATH_UNCLASSIFIED_PAYABLE_SERVICES = "unclassified_payable_services"
    const val PATH_WEAK_ALCOHOL = "weak_alcohol"
    const val PATH_STRONG_ALCOHOL = "strong_alcohol"
    const val PATH_TOBACCO = "tobacco"

    const val PATH_PRODUCT_EXTENSIONS = "product_extensions"

    val URI_PRODUCT_EXTENSIONS: Uri = Uri.withAppendedPath(BASE_URI, PATH_PRODUCT_EXTENSIONS)

    const val PATH_ALCOHOL_PRODUCTS = "alcohol_products"

    const val PATH_PRODUCT_GROUPS = "product_groups"

    object ProductColumns {
        const val VARIATION_ID = "VARIATION_ID"

        const val VARIATION_ID_UNCLASSIFIED_PRODUCT = "NORMAL"
        const val VARIATION_ID_UNCLASSIFIED_PAYABLE_SERVICE = "SERVICE"
        const val VARIATION_ID_WEAK_ALCOHOL = "ALCOHOL_NOT_MARKED"
        const val VARIATION_ID_STRONG_ALCOHOL = "ALCOHOL_MARKED"
        const val VARIATION_ID_TOBACCO = "TOBACCO"

        const val UUID = "UUID"
        const val GROUP_UUID = "GROUP_UUID"
        const val NAME = "NAME"
        const val CODE = "CODE"
        const val VENDOR_CODE = "VENDOR_CODE"
        const val BARCODES = "BARCODES"
        const val PURCHASE_PRICE = "PURCHASE_PRICE"
        const val SELLING_PRICE = "SELLING_PRICE"
        const val VAT_RATE = "VAT_RATE"
        const val QUANTITY = "QUANTITY"
        const val DESCRIPTION = "DESCRIPTION"
        const val ALLOWED_TO_SELL = "ALLOWED_TO_SELL"
    }

    object UnitOfMeasurementColumns {
        const val VARIATION_ID = "UNIT_OF_MEASUREMENT_VARIATION_ID"

        const val VARIATION_ID_CUSTOM = 0
        const val VARIATION_ID_CONVENTIONAL_UNIT = 1
        const val VARIATION_ID_PIECE = 2
        const val VARIATION_ID_PACKAGING = 3
        const val VARIATION_ID_KIT = 4
        const val VARIATION_ID_KILOGRAM = 5
        const val VARIATION_ID_METER = 6
        const val VARIATION_ID_SQUARE_METER = 7
        const val VARIATION_ID_CUBIC_METER = 8
        const val VARIATION_ID_LITER = 9

        const val TYPE = "UNIT_OF_MEASUREMENT_TYPE"
        const val NAME = "UNIT_OF_MEASUREMENT_NAME"
        const val PRECISION = "UNIT_OF_MEASUREMENT_PRECISION"
    }

    object AlcoholProductColumns {
        const val PRODUCT_UUID = "PRODUCT_UUID"
        const val FSAR_PRODUCT_KIND_CODE = "FSAR_PRODUCT_KIND_CODE"
        const val TARE_VOLUME = "TARE_VOLUME"
        const val ALCOHOL_PERCENTAGE = "ALCOHOL_PERCENTAGE"
    }

    object ProductGroupColumns {
        const val UUID = "UUID"
        const val PARENT_GROUP_UUID = "PARENT_GROUP_UUID"
        const val NAME = "NAME"
    }
}