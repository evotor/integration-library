package ru.evotor.integrations

import android.net.Uri

/**
 * Created by nixan on 15.03.17.
 */

class ReceiptsApi {

    companion object {

        const val AUTHORITY = "ru.evotor.receipt"

        val BASE_URI = Uri.parse("content://$AUTHORITY")

    }

    object Description {

        const val PATH_RECEIPT_DESCRIPTION = "information"

        val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_DESCRIPTION)

        const val ROW_ID = "_id"
        const val ROW_UUID = "uuid"
        const val ROW_DISCOUNT = "discount"

    }

    object Positions {

        const val PATH_RECEIPT_POSITIONS = "positions"

        val URI_RECEIPT_POSITIONS = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_POSITIONS)

        const val ROW_ID = "_id"
        const val ROW_UUID = "uuid"
        const val ROW_TYPE = "type"
        const val ROW_CODE = "code"
        const val ROW_MEASURE = "measure"
        const val ROW_PRICE = "price"
        const val ROW_QUANTITY = "quantity"
        const val ROW_MARK = "mark"
        const val ROW_NAME = "name"
    }

    object Payments {

        const val PATH_RECEIPT_PAYMENTS = "payments"

        val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_PAYMENTS)

        const val ROW_ID = "_id"
        const val ROW_UUID = "uuid"
        const val ROW_SUM = "sum"
        const val ROW_TYPE = "type"
        const val ROW_RRN = "rrn"
        const val ROW_PIN_PAD_UUID = "pin_pad_uuid"

        object Type {
            const val TYPE_CASH = 0
            const val TYPE_CARD = 1
        }

    }
}