package ru.evotor.integrations

import android.net.Uri

/**
 * Created by nixan on 15.03.17.
 */

class ReceiptsApi {

    companion object {

        @JvmStatic val AUTHORITY = "ru.evotor.receipt"

        @JvmStatic val BASE_URI = Uri.parse("content://$AUTHORITY")

    }

    object Description {

        @JvmStatic val PATH_RECEIPT_DESCRIPTION = "information"

        @JvmStatic val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_DESCRIPTION)

        @JvmStatic val ROW_ID = "_id"
        @JvmStatic val ROW_UUID = "uuid"
        @JvmStatic val ROW_DISCOUNT = "discount"

    }

    object Positions {

        @JvmStatic val PATH_RECEIPT_POSITIONS = "positions"

        @JvmStatic val URI_RECEIPT_POSITIONS = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_POSITIONS)

        @JvmStatic val ROW_ID = "_id"
        @JvmStatic val ROW_UUID = "uuid"
        @JvmStatic val ROW_TYPE = "type"
        @JvmStatic val ROW_CODE = "code"
        @JvmStatic val ROW_MEASURE = "measure"
        @JvmStatic val ROW_PRICE = "price"
        @JvmStatic val ROW_QUANTITY = "quantity"
        @JvmStatic val ROW_MARK = "mark"
        @JvmStatic val ROW_NAME = "name"
    }

    object Payments {

        @JvmStatic val PATH_RECEIPT_PAYMENTS = "payments"

        @JvmStatic val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_PAYMENTS)

        @JvmStatic val ROW_ID = "_id"
        @JvmStatic val ROW_UUID = "uuid"
        @JvmStatic val ROW_SUM = "sum"
        @JvmStatic val ROW_TYPE = "type"
        @JvmStatic val ROW_RRN = "rrn"
        @JvmStatic val ROW_PIN_PAD_UUID = "pin_pad_uuid"

        object Type {
            @JvmStatic val TYPE_CASH = 0
            @JvmStatic val TYPE_CARD = 1
        }

    }
}