package ru.evotor.framework.inventory.api

import android.content.Context
import android.net.Uri
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.mapper.ProductMapper
import ru.evotor.framework.inventory.product.provider.ProductContract
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.receipt.position.Position
import ru.evotor.framework.receipt.position.mapper.PositionMapper
import ru.evotor.framework.receipt.position.provider.PositionContract
import ru.evotor.query.Cursor

object InventoryApi {
    fun findProductsByBarcode(context: Context, barcode: String): List<Product> =
            context.contentResolver.query(
                    Uri.withAppendedPath(
                            InventoryContract.getBarcodeUri(barcode),
                            ProductContract.PATH
                    ),
                    null,
                    null,
                    null,
                    null
            ).let { cursor ->
                object : Cursor<Product>(cursor) {
                    override fun getValue() = ProductMapper.read(context, this)
                }.toList()
            }

    fun createPositionsByBarcode(context: Context, barcode: String): List<Position> =
            context.contentResolver.query(
                    Uri.withAppendedPath(
                            InventoryContract.getBarcodeUri(barcode),
                            PositionContract.PATH
                    ),
                    null,
                    null,
                    null,
                    null
            ).let { cursor ->
                object : Cursor<Position>(cursor) {
                    override fun getValue() = PositionMapper.read(this)
                }.toList()
            }
}