package ru.evotor.framework.receipt

import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.evotor.framework.inventory.ProductType
import java.math.BigDecimal

object ReceiptApi {
    const val AUTHORITY = "ru.evotor.evotorpos.receipt"

    @JvmField val BASE_URI = Uri.parse("content://$AUTHORITY")

    @JvmStatic
    fun getPositionsByBarcode(context: Context, barcode: String): List<Position> {
        val positionsList = ArrayList<Position>()

        val cursor: Cursor? = context.contentResolver.query(
                Uri.withAppendedPath(PositionTable.URI, barcode),
                null, null, null, null)

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    val position: Position = Position(
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_POSITION_UUID)),
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_PRODUCT_UUID)),
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_PRODUCT_CODE)),
                            ProductType.valueOf(cursor.getString(cursor.getColumnIndex(PositionTable.ROW_PRODUCT_TYPE))),
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_NAME)),
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_MEASURE_NAME)),
                            cursor.getInt(cursor.getColumnIndex(PositionTable.ROW_MEASURE_PRECISION)),
                            BigDecimal(cursor.getLong(cursor.getColumnIndex(PositionTable.ROW_PRICE))).divide(BigDecimal(100)),
                            BigDecimal(cursor.getLong(cursor.getColumnIndex(PositionTable.ROW_PRICE))).divide(BigDecimal(100)),
                            BigDecimal(cursor.getLong(cursor.getColumnIndex(PositionTable.ROW_QUANTITY))).divide(BigDecimal(1000)),
                            cursor.getString(cursor.getColumnIndex(PositionTable.ROW_BARCODE)),
                            null,
                            BigDecimal(cursor.getLong(cursor.getColumnIndex(PositionTable.ROW_ALCOHOL_BY_VOLUME))).divide(BigDecimal(1000)),
                            cursor.getLong(cursor.getColumnIndex(PositionTable.ROW_ALCOHOL_PRODUCT_KIND_CODE)),
                            BigDecimal(cursor.getString(cursor.getColumnIndex(PositionTable.ROW_TARE_VOLUME))).divide(BigDecimal(1000)),
                            null,
                            null
                    )
                    positionsList.add(position)
                }
            } finally {
                cursor.close()
            }
        }

        return positionsList
    }

    object Description {

        const val PATH_RECEIPT_DESCRIPTION = "information"

        @JvmField val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_DESCRIPTION)

        const val ROW_ID = "_id"
        const val ROW_UUID = "uuid"
        const val ROW_DISCOUNT = "discount"

    }

    object Positions {

        const val PATH_RECEIPT_POSITIONS = "positions"

        @JvmField val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_POSITIONS)

        const val ROW_UUID = "uuid"
        const val ROW_PRODUCT_UUID = "productUuid"
        const val ROW_TYPE = "type"
        const val ROW_CODE = "code"
        const val ROW_MEASURE_NAME = "measureName"
        const val ROW_MEASURE_PRECISION = "measurePrecision"
        const val ROW_PRICE = "price"
        const val ROW_QUANTITY = "quantity"
        const val ROW_MARK = "mark"
        const val ROW_NAME = "name"
    }

    object Payments {

        const val PATH_RECEIPT_PAYMENTS = "payments"

        @JvmField val URI = Uri.withAppendedPath(BASE_URI, PATH_RECEIPT_PAYMENTS)

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