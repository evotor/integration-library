package ru.evotor.integrations.inventory

import android.content.Context
import android.net.Uri
import java.math.BigDecimal

/**
 * Created by nixan on 06.03.17.
 */


object InventoryApi {
    @JvmField val BASE_URI = Uri.parse("content://ru.evotor.evotorpos.inventory")

    fun getProductByUuid(context: Context, uuid: String): Product? {
        context.contentResolver
                .query(Uri.withAppendedPath(ProductTable.URI, uuid), null, null, null, null)
                ?.let { cursor ->
                    try {
                        if (cursor.moveToFirst()) {
                            return Product(
                                    uuid = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_UUID)),
                                    productName = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_NAME)),
                                    description = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_DESCRIPTION)),
                                    price = BigDecimal(cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_PRICE_OUT))).divide(BigDecimal(100)),
                                    quantity = BigDecimal(cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_QUANTITY))).divide(BigDecimal(1000)))
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        cursor.close()
                    }
                }
        return null
    }

}