package ru.evotor.framework.inventory.product.mapper

import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.StrongAlcohol
import ru.evotor.framework.inventory.product.extension.mapper.AlcoholProductMapper
import ru.evotor.framework.inventory.product.extension.mapper.ExcisableProductMapper
import ru.evotor.framework.inventory.product.extension.provider.AlcoholProductContract
import ru.evotor.framework.inventory.product.extension.provider.ExcisableProductContract

internal object StrongAlcoholMapper {
    fun read(context: Context, productCursor: Cursor): StrongAlcohol {
        val uuid = ProductMapper.readUuid(productCursor)
                ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::uuid)

        val alcoholProductCursor = context.contentResolver.query(
                Uri.withAppendedPath(AlcoholProductContract.URI, uuid.toString()),
                null,
                null,
                null,
                null
        ) ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java)
        alcoholProductCursor.moveToFirst()

        val excisableProductCursor = context.contentResolver.query(
                Uri.withAppendedPath(ExcisableProductContract.URI, uuid.toString()),
                null,
                null,
                null,
                null
        ) ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java)
        excisableProductCursor.moveToFirst()

        val result = StrongAlcohol(
                uuid = uuid,
                groupUuid = ProductMapper.readGroupUuid(productCursor),
                name = ProductMapper.readName(productCursor)
                        ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::name),
                code = ProductMapper.readCode(productCursor),
                fsrarProductKindCode = AlcoholProductMapper.readFsrarProductKindCode(alcoholProductCursor)
                        ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::fsrarProductKindCode),
                vendorCode = ProductMapper.readVendorCode(productCursor),
                barcodes = ProductMapper.readBarcodes(productCursor),
                mark = ExcisableProductMapper.readMark(excisableProductCursor)
                        ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::mark),
                purchasePrice = ProductMapper.readPurchasePrice(productCursor),
                sellingPrice = ProductMapper.readSellingPrice(productCursor),
                vatRate = ProductMapper.readVatRate(productCursor)
                        ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::vatRate),
                quantity = ProductMapper.readQuantity(productCursor)
                        ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::quantity),
                unitOfMeasurement = UnitOfMeasurementMapper.read(productCursor),
                tareVolume = AlcoholProductMapper.readTareVolume(alcoholProductCursor)
                        ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::tareVolume),
                alcoholPercentage = AlcoholProductMapper.readAlcoholPercentage(alcoholProductCursor)
                        ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::alcoholPercentage),
                description = ProductMapper.readDescription(productCursor),
                allowedToSell = ProductMapper.readAllowedToSell(productCursor)
                        ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::allowedToSell)
        )

        alcoholProductCursor.close()
        excisableProductCursor.close()

        return result
    }
}