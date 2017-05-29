package ru.evotor.framework.receipt

import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.evotor.framework.inventory.ProductType
import java.math.BigDecimal

object ReceiptApi {
    @JvmField val BASE_URI = Uri.parse("content://ru.evotor.evotorpos.receipt")

    @JvmStatic
    fun getPositionsByBarcode(context: Context, barcode: String): List<Position> {
        val positionsList = ArrayList<Position>()

        val cursor: Cursor? = context.contentResolver.query(
                Uri.withAppendedPath(PositionTable.URI, barcode),
                null, null, null, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val position: Position = Position(
                            null,
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
                } while (cursor.moveToNext())
            }
        }

        return positionsList
    }

}