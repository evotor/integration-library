package ru.evotor.framework.receipt

import android.net.Uri

object PositionTable {
    @JvmField val URI = Uri.withAppendedPath(ReceiptApi.BASE_URI, "position")
    const val COLUMN_POSITION_UUID = "POSITION_UUID"
    const val COLUMN_PARENT_POSITION_UUID = "PARENT_POSITION_UUID"
    const val COLUMN_PRODUCT_UUID = "PRODUCT_UUID"
    const val COLUMN_PRODUCT_CODE = "PRODUCT_CODE"
    const val COLUMN_PRODUCT_TYPE = "PRODUCT_TYPE"
    const val COLUMN_NAME = "NAME"
    const val COLUMN_MEASURE_NAME = "MEASURE_NAME"
    const val COLUMN_MEASURE_PRECISION = "MEASURE_PRECISION"
    const val COLUMN_TAX_NUMBER = "TAX_NUMBER"
    const val COLUMN_PRICE = "PRICE"
    const val COLUMN_PRICE_WITH_DISCOUNT_POSITION = "PRICE_WITH_DISCOUNT_POSITION"
    const val COLUMN_BARCODE = "BARCODE"
    const val COLUMN_QUANTITY = "QUANTITY"
    const val COLUMN_ALCOHOL_BY_VOLUME = "ALCOHOL_BY_VOLUME"
    const val COLUMN_ALCOHOL_PRODUCT_KIND_CODE = "ALCOHOL_PRODUCT_KIND_CODE"
    const val COLUMN_TARE_VOLUME = "TARE_VOLUME"
    const val COLUMN_MARK = "MARK"
    const val COLUMN_EXTRA_KEYS = "EXTRA_KEYS"
    const val COLUMN_SETTLEMENT_METHOD = "SETTLEMENT_METHOD"
    const val COLUMN_SETTLEMENT_METHOD_AMOUNT = "SETTLEMENT_METHOD_AMOUNT"
    const val COLUMN_ATTRIBUTES = "ATTRIBUTES"

    object ExtraKeyJSONKeys {
        const val KEY_IDENTITY = "identity"
        const val KEY_APP_ID = "appId"
        const val KEY_DESCRIPTION = "description"
    }

    object AttributeJSONKeys {
        const val DICTIONARY_UUID = "DICTIONARY_UUID"
        const val DICTIONARY_NAME = "DICTIONARY_NAME"
        const val UUID = "UUID"
        const val NAME = "NAME"
    }
}
