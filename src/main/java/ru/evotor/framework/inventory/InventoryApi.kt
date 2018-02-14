package ru.evotor.framework.inventory

import android.content.Context
import android.database.Cursor
import android.net.Uri
import org.json.JSONObject
import ru.evotor.framework.Utils
import ru.evotor.framework.inventory.field.DictionaryField
import ru.evotor.framework.inventory.field.Field
import ru.evotor.framework.inventory.field.FieldTable
import ru.evotor.framework.inventory.field.TextField
import ru.evotor.framework.receipt.TaxNumber
import java.math.BigDecimal

/**
 * Created by nixan on 06.03.17.
 */


object InventoryApi {
    @JvmField val BASE_URI = Uri.parse("content://ru.evotor.evotorpos.inventory")

    const val BROADCAST_ACTION_PRODUCTS_UPDATED = "evotor.intent.action.inventory.PRODUCTS_UPDATED"

    @JvmStatic
    fun getAllBarcodesForProduct(context: Context, productUuid: String): List<String> {
        val barcodesList = ArrayList<String>()

        val cursor: Cursor? = context.contentResolver.query(
                Uri.withAppendedPath(BarcodeTable.URI, productUuid),
                null, null, null, null)
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    val barcode: String = cursor.getString(cursor.getColumnIndex(BarcodeTable.ROW_BARCODE))
                    barcodesList.add(barcode)
                }
            } finally {
                cursor.close()
            }
        }

        return barcodesList
    }

    @JvmStatic
    fun getProductByUuid(context: Context, uuid: String): ProductItem? {
        if (uuid.isBlank()) return null
        context.contentResolver
                .query(Uri.withAppendedPath(ProductTable.URI, uuid), null, null, null, null)
                ?.let { cursor ->
                    try {
                        if (cursor.moveToFirst()) {
                            if (cursor.getInt(cursor.getColumnIndex(ProductTable.ROW_IS_GROUP)) > 0) {
                                return ProductItem.ProductGroup(
                                        uuid = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_UUID)),
                                        parentUuid = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_PARENT_UUID)),
                                        code = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_CODE)),
                                        name = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_NAME)),
                                        taxNumber = Utils.safeValueOf(TaxNumber::class.java, cursor.getString(cursor.getColumnIndex(ProductTable.ROW_TAX_NUMBER)), TaxNumber.NO_VAT)
                                )
                            } else {
                                return ProductItem.Product(
                                        uuid = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_UUID)),
                                        parentUuid = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_PARENT_UUID)),
                                        code = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_CODE)),
                                        type = Utils.safeValueOf(ProductType::class.java, cursor.getString(cursor.getColumnIndex(ProductTable.ROW_TYPE)), ProductType.NORMAL),
                                        name = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_NAME)),
                                        description = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_DESCRIPTION)),
                                        price = BigDecimal(cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_PRICE_OUT))).divide(BigDecimal(100)),
                                        quantity = BigDecimal(cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_QUANTITY))).divide(BigDecimal(1000)),
                                        measureName = cursor.getString(cursor.getColumnIndex(ProductTable.ROW_MEASURE_NAME)),
                                        measurePrecision = cursor.getInt(cursor.getColumnIndex(ProductTable.ROW_MEASURE_PRECISION)),
                                        alcoholByVolume = cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_ALCOHOL_BY_VOLUME)).let { BigDecimal(it).divide(BigDecimal(1000)) },
                                        alcoholProductKindCode = cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_ALCOHOL_PRODUCT_KIND_CODE)),
                                        tareVolume = cursor.getLong(cursor.getColumnIndex(ProductTable.ROW_TARE_VOLUME)).let { BigDecimal(it).divide(BigDecimal(1000)) },
                                        taxNumber = Utils.safeValueOf(TaxNumber::class.java, cursor.getString(cursor.getColumnIndex(ProductTable.ROW_TAX_NUMBER)), TaxNumber.NO_VAT)
                                )
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        cursor.close()
                    }
                }
        return null
    }

    @JvmStatic
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

    @JvmStatic
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