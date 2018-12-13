package ru.evotor.framework.inventory.product.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.Tobacco
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
import ru.evotor.framework.inventory.product.extension.mapper.ExcisableProductMapper

internal object TobaccoMapper {
    fun read(cursor: Cursor) = Tobacco(
            uuid = ProductMapper.readUuid(cursor)
                    ?: throw IntegrationLibraryMappingException(Product::uuid.name),
            groupUuid = ProductMapper.readGroupUuid(cursor),
            name = ProductMapper.readName(cursor)
                    ?: throw IntegrationLibraryMappingException(Product::name.name),
            code = ProductMapper.readCode(cursor),
            vendorCode = ProductMapper.readVendorCode(cursor),
            barcodes = ProductMapper.readBarcodes(cursor),
            mark = ExcisableProductMapper.readMark(cursor)
                    ?: throw IntegrationLibraryMappingException(ExcisableProduct::mark.name),
            purchasePrice = ProductMapper.readPurchasePrice(cursor),
            sellingPrice = ProductMapper.readSellingPrice(cursor),
            vatRate = ProductMapper.readVatRate(cursor)
                    ?: throw IntegrationLibraryMappingException(Product::vatRate.name),
            quantity = ProductMapper.readQuantity(cursor)
                    ?: throw IntegrationLibraryMappingException(Product::quantity.name),
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor),
            description = ProductMapper.readDescription(cursor),
            allowedToSell = ProductMapper.readAllowedToSell(cursor)
                    ?: throw IntegrationLibraryMappingException(Product::allowedToSell.name)
    )
}