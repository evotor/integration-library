package ru.evotor.integrations

import android.net.Uri

/**
 * Created by nixan on 15.03.17.
 */

class ReceiptsApi {

    companion object {

        @JvmField val AUTHORITY = "ru.evotor.receipt"

        @JvmField val BASE_URI = Uri.parse("content://$AUTHORITY")

    }

    object Description {

        @JvmField val PATH_RECEIPT_DESCRIPTION = "information"

        @JvmField val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_DESCRIPTION)

        @JvmField val ROW_ID = "_id"
        @JvmField val ROW_UUID = "uuid"
        @JvmField val ROW_DISCOUNT = "discount"

    }

    object Positions {

        @JvmField val PATH_RECEIPT_POSITIONS = "positions"

        @JvmField val URI_RECEIPT_POSITIONS = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_POSITIONS)

        @JvmField val ROW_ID = "_id"
        @JvmField val ROW_UUID = "uuid"
        @JvmField val ROW_TYPE = "type"
        @JvmField val ROW_CODE = "code"
        @JvmField val ROW_MEASURE = "measure"
        @JvmField val ROW_PRICE = "price"
        @JvmField val ROW_QUANTITY = "quantity"
        @JvmField val ROW_MARK = "mark"
        @JvmField val ROW_NAME = "name"
    }

    object Payments {

        @JvmField val PATH_RECEIPT_PAYMENTS = "payments"

        @JvmField val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_PAYMENTS)

        @JvmField val ROW_ID = "_id"
        @JvmField val ROW_UUID = "uuid"
        @JvmField val ROW_SUM = "sum"
        @JvmField val ROW_TYPE = "type"
        @JvmField val ROW_RRN = "rrn"
        @JvmField val ROW_PIN_PAD_UUID = "pin_pad_uuid"

        object Type {
            @JvmField val TYPE_CASH = 0
            @JvmField val TYPE_CARD = 1
        }

    }
}