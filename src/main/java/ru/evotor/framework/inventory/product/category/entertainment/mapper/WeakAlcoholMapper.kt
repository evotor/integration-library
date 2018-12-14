package ru.evotor.framework.inventory.product.category.entertainment.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.category.entertainment.WeakAlcohol
import ru.evotor.framework.inventory.product.extension.mapper.AlcoholProductMapper
import ru.evotor.framework.inventory.product.mapper.ProductMapper
import ru.evotor.framework.inventory.product.mapper.UnitOfMeasurementMapper

internal object WeakAlcoholMapper {
    fun read(cursor: Cursor) = WeakAlcohol(
            uuid = ProductMapper.readUuid(cursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::uuid),
            groupUuid = ProductMapper.readGroupUuid(cursor),
            name = ProductMapper.readName(cursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::name),
            code = ProductMapper.readCode(cursor),
            fsrarProductKindCode = AlcoholProductMapper.readFsrarProductKindCode(cursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::fsrarProductKindCode),
            vendorCode = ProductMapper.readVendorCode(cursor),
            barcodes = ProductMapper.readBarcodes(cursor),
            purchasePrice = ProductMapper.readPurchasePrice(cursor),
            sellingPrice = ProductMapper.readSellingPrice(cursor),
            vatRate = ProductMapper.readVatRate(cursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::vatRate),
            quantity = ProductMapper.readQuantity(cursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::quantity),
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor),
            tareVolume = AlcoholProductMapper.readTareVolume(cursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::tareVolume),
            alcoholPercentage = AlcoholProductMapper.readAlcoholPercentage(cursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::alcoholPercentage),
            description = ProductMapper.readDescription(cursor),
            allowedToSell = ProductMapper.readAllowedToSell(cursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::allowedToSell)
    )
}