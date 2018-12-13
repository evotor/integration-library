package ru.evotor.framework.inventory.product.mapper

import android.database.Cursor
import ru.evotor.framework.core.OutdatedLibraryException
import ru.evotor.framework.inventory.product.Product
import ru.evotor.framework.inventory.product.StrongAlcohol
import ru.evotor.framework.inventory.product.extension.AlcoholProduct
import ru.evotor.framework.inventory.product.extension.ExcisableProduct
import ru.evotor.framework.inventory.product.extension.mapper.AlcoholProductMapper
import ru.evotor.framework.inventory.product.extension.mapper.ExcisableProductMapper

internal object StrongAlcoholMapper {
    fun read(cursor: Cursor) = StrongAlcohol(
            uuid = ProductMapper.readUuid(cursor)
                    ?: throw OutdatedLibraryException(Product::uuid.name),
            groupUuid = ProductMapper.readGroupUuid(cursor),
            name = ProductMapper.readName(cursor)
                    ?: throw OutdatedLibraryException(Product::name.name),
            code = ProductMapper.readCode(cursor),
            fsrarProductKindCode = AlcoholProductMapper.readFsrarProductKindCode(cursor)
                    ?: throw OutdatedLibraryException(AlcoholProduct::fsrarProductKindCode.name),
            vendorCode = ProductMapper.readVendorCode(cursor),
            barcodes = ProductMapper.readBarcodes(cursor),
            mark = ExcisableProductMapper.readMark(cursor)
                    ?: throw OutdatedLibraryException(ExcisableProduct::mark.name),
            purchasePrice = ProductMapper.readPurchasePrice(cursor),
            sellingPrice = ProductMapper.readSellingPrice(cursor),
            vatRate = ProductMapper.readVatRate(cursor)
                    ?: throw OutdatedLibraryException(Product::vatRate.name),
            quantity = ProductMapper.readQuantity(cursor)
                    ?: throw OutdatedLibraryException(Product::quantity.name),
            unitOfMeasurement = UnitOfMeasurementMapper.read(cursor),
            tareVolume = AlcoholProductMapper.readTareVolume(cursor)
                    ?: throw OutdatedLibraryException(AlcoholProduct::tareVolume.name),
            alcoholPercentage = AlcoholProductMapper.readAlcoholPercentage(cursor)
                    ?: throw OutdatedLibraryException(AlcoholProduct::alcoholPercentage.name),
            description = ProductMapper.readDescription(cursor),
            allowedToSell = ProductMapper.readAllowedToSell(cursor)
                    ?: throw OutdatedLibraryException(Product::allowedToSell.name)
    )
}