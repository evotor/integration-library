package ru.evotor.framework.inventory.api

import android.content.Context
import android.net.Uri
import ru.evotor.framework.FutureFeature
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.core.safeGetString
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.mapper.ProductMapper
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.receipt.position.Position
import ru.evotor.framework.receipt.position.mapper.PositionMapper
import ru.evotor.query.Cursor

object InventoryApi {
    @JvmStatic
    fun findProductsByBarcode(context: Context, barcode: String): List<Product>? =
            context.contentResolver.query(
                    InventoryContract.Barcode.getProductsByBarcodeUri(barcode),
                    null,
                    null,
                    null,
                    null
            )?.let { cursor ->
                object : Cursor<Product>(cursor) {
                    override fun getValue() = ProductMapper.read(context, this)
                }.toList()
            }

    @JvmStatic
    fun findBarcodesForProduct(context: Context, productUuid: String): List<String>? =
            context.contentResolver.query(
                    InventoryContract.Barcode.CONTENT_URI,
                    arrayOf(InventoryContract.Barcode.VALUE),
                    "${InventoryContract.Barcode.PRODUCT_UUID} = ?",
                    arrayOf(productUuid),
                    null
            )?.let { cursor ->
                object : Cursor<String>(cursor) {
                    override fun getValue() =
                            this.safeGetString(InventoryContract.Barcode.VALUE)
                                    ?: throw IntegrationLibraryMappingException(String::class.java)
                }.toList()
            }

    @FutureFeature("Создание новых позиций по штрихкоду")
    @JvmStatic
    private fun createPositionsByBarcode(context: Context, barcode: String): List<Position>? =
            context.contentResolver.query(
                    InventoryContract.Barcode.getPositionsByBarcodeUri(barcode),
                    null,
                    null,
                    null,
                    null
            )?.let { cursor ->
                object : Cursor<Position>(cursor) {
                    override fun getValue() = PositionMapper.read(this)
                }.toList()
            }
}