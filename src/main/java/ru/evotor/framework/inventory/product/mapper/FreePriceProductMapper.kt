package ru.evotor.framework.inventory.product.mapper

import android.database.Cursor
import ru.evotor.framework.core.OutdatedLibraryException
import ru.evotor.framework.inventory.product.FreePriceProduct
import ru.evotor.framework.inventory.product.Product

internal object FreePriceProductMapper {
    fun read(cursor: Cursor) = FreePriceProduct(
            uuid = ProductMapper.readUuid(cursor)
                    ?: throw OutdatedLibraryException(Product::uuid.name),
            groupUuid = ProductMapper.readGroupUuid(cursor),
            name = ProductMapper.readName(cursor)
                    ?: throw OutdatedLibraryException(Product::name.name),
            code = ProductMapper.readCode(cursor),
            vendorCode = ProductMapper.readVendorCode(cursor),
            barcodes = ProductMapper.readBarcodes(cursor),
            purchasePrice = ProductMapper.readPurchasePrice(cursor),
            vatRate = ProductMapper.readVatRate(cursor)
                    ?: throw OutdatedLibraryException(Product::vatRate.name),
            quantity = ProductMapper.readQuantity(cursor)
                    ?: throw OutdatedLibraryException(Product::quantity.name),
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor),
            description = ProductMapper.readDescription(cursor),
            allowedToSell = ProductMapper.readAllowedToSell(cursor)
                    ?: throw OutdatedLibraryException(Product::allowedToSell.name)
    )
}