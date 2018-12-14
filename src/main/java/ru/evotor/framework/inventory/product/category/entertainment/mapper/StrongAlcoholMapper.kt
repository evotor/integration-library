package ru.evotor.framework.inventory.product.category.entertainment.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.category.entertainment.StrongAlcohol
import ru.evotor.framework.inventory.product.extension.mapper.AlcoholProductMapper
import ru.evotor.framework.inventory.product.extension.mapper.ExcisableProductMapper
import ru.evotor.framework.inventory.product.mapper.ProductMapper
import ru.evotor.framework.inventory.product.mapper.UnitOfMeasurementMapper

internal object StrongAlcoholMapper {
    fun read(cursor: Cursor) = StrongAlcohol(
            uuid = ProductMapper.readUuid(cursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::uuid),
            groupUuid = ProductMapper.readGroupUuid(cursor),
            name = ProductMapper.readName(cursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::name),
            code = ProductMapper.readCode(cursor),
            fsrarProductKindCode = AlcoholProductMapper.readFsrarProductKindCode(cursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::fsrarProductKindCode),
            vendorCode = ProductMapper.readVendorCode(cursor),
            barcodes = ProductMapper.readBarcodes(cursor),
            mark = ExcisableProductMapper.readMark(cursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::mark),
            purchasePrice = ProductMapper.readPurchasePrice(cursor),
            sellingPrice = ProductMapper.readSellingPrice(cursor),
            vatRate = ProductMapper.readVatRate(cursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::vatRate),
            quantity = ProductMapper.readQuantity(cursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::quantity),
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor),
            tareVolume = AlcoholProductMapper.readTareVolume(cursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::tareVolume),
            alcoholPercentage = AlcoholProductMapper.readAlcoholPercentage(cursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::alcoholPercentage),
            description = ProductMapper.readDescription(cursor),
            allowedToSell = ProductMapper.readAllowedToSell(cursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::allowedToSell)
    )
}