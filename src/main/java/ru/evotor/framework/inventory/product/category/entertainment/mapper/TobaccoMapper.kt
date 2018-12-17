package ru.evotor.framework.inventory.product.category.entertainment.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.category.entertainment.Tobacco
import ru.evotor.framework.inventory.product.mapper.ProductMapper
import ru.evotor.framework.inventory.product.mapper.UnitOfMeasurementMapper

internal object TobaccoMapper {
    fun read(cursor: Cursor) = Tobacco(
            uuid = ProductMapper.readUuid(cursor)
                    ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::uuid),
            groupUuid = ProductMapper.readGroupUuid(cursor),
            name = ProductMapper.readName(cursor)
                    ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::name),
            code = ProductMapper.readCode(cursor),
            vendorCode = ProductMapper.readVendorCode(cursor),
            barcodes = ProductMapper.readBarcodes(cursor),
            purchasePrice = ProductMapper.readPurchasePrice(cursor),
            sellingPrice = ProductMapper.readSellingPrice(cursor),
            vatRate = ProductMapper.readVatRate(cursor)
                    ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::vatRate),
            quantity = ProductMapper.readQuantity(cursor)
                    ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::quantity),
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor),
            description = ProductMapper.readDescription(cursor),
            allowedToSell = ProductMapper.readAllowedToSell(cursor)
                    ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::allowedToSell)
    )
}