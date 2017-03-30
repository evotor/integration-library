package ru.evotor.integrations.inventory

import android.content.Context
import android.database.Cursor
import android.net.Uri
import org.json.JSONObject
import ru.evotor.integrations.inventory.field.DictionaryField
import ru.evotor.integrations.inventory.field.Field
import ru.evotor.integrations.inventory.field.FieldTable
import ru.evotor.integrations.inventory.field.TextField
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

    fun getProductExtras(context: Context, productUuid: String): List<ProductExtra> {
        val result = ArrayList<ProductExtra>()
        context.contentResolver
                .query(ProductExtraTable.URI, null, "${ProductExtraTable.ROW_PRODUCT_UUID} = ?", arrayOf(productUuid), null)
                ?.let { cursor ->
                    try {
                        if (cursor.moveToFirst()) {
                            do {
                                result.add(createProductExtra(cursor))
                            } while (cursor.moveToNext());
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        cursor.close()
                    }
                }
        return result
    }

    private fun createProductExtra(cursor: Cursor): ProductExtra {
        return ProductExtra(
                uuid = cursor.getString(cursor.getColumnIndex(ProductExtraTable.ROW_UUID)),
                name = cursor.getString(cursor.getColumnIndex(ProductExtraTable.ROW_NAME)),
                commodityUUID = cursor.getString(cursor.getColumnIndex(ProductExtraTable.ROW_PRODUCT_UUID)),
                fieldUUID = cursor.getString(cursor.getColumnIndex(ProductExtraTable.ROW_FIELD_UUID)),
                fieldValue = cursor.getString(cursor.getColumnIndex(ProductExtraTable.ROW_FIELD_VALUE)),
                data = cursor.getString(cursor.getColumnIndex(ProductExtraTable.ROW_DATA))
        )
    }

    fun getField(context: Context, fieldUuid: String): Field? {
        context.contentResolver
                .query(FieldTable.URI, null, "${FieldTable.ROW_FIELD_UUID} = ?", arrayOf(fieldUuid), null)
                ?.let { cursor ->
                    try {
                        if (cursor.moveToFirst()) {
                            return createField(cursor)
                        } else {
                            return null
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        cursor.close()
                    }
                }

        return null
    }

    private fun createField(cursor: Cursor): Field? {
        val name = cursor.getString(cursor.getColumnIndex(FieldTable.ROW_NAME))
        val fieldUUID = cursor.getString(cursor.getColumnIndex(FieldTable.ROW_FIELD_UUID))
        val title = cursor.getString(cursor.getColumnIndex(FieldTable.ROW_TITLE))
        val specificData = JSONObject(cursor.getString(cursor.getColumnIndex(FieldTable.ROW_SPECIFIC_DATA)))


        when (cursor.getInt(cursor.getColumnIndex(FieldTable.ROW_TYPE))) {
            FieldTable.TYPE_DICTIONARY -> {
                val jsonItems = specificData.optJSONArray("items")
                val items = (0 until jsonItems.length())
                        .map { jsonItems.getJSONObject(it) }
                        .map {
                            DictionaryField.Item(
                                    title = it.optString("title"),
                                    value = it.opt("value"),
                                    data = it.opt("data")
                            )
                        }

                return DictionaryField(
                        name = name,
                        fieldUUID = fieldUUID,
                        title = title,
                        items = items.toTypedArray(),
                        multiple = specificData.optBoolean("multiple")

                )
            }
            FieldTable.TYPE_TEXT_FIELD -> {
                return TextField(
                        name = name,
                        fieldUUID = fieldUUID,
                        title = title,
                        data = specificData.optString("data")
                )
            }
            else -> return null
        }
    }

}