package ru.evotor.framework.inventory.product.mapper

import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.WeakAlcohol
import ru.evotor.framework.inventory.product.extension.mapper.AlcoholProductMapper
import ru.evotor.framework.inventory.product.extension.provider.AlcoholProductContract

internal object WeakAlcoholMapper {
    fun read(context: Context, productCursor: Cursor): WeakAlcohol {
        val uuid = ProductMapper.readUuid(productCursor)
                ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::uuid)

        val alcoholProductCursor = context.contentResolver.query(
                Uri.withAppendedPath(AlcoholProductContract.URI, uuid.toString()),
                null,
                null,
                null,
                null
        ) ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java)
        alcoholProductCursor.moveToFirst()

        val result = WeakAlcohol(
                uuid = uuid,
                groupUuid = ProductMapper.readGroupUuid(productCursor),
                name = ProductMapper.readName(productCursor)
                        ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::name),
                code = ProductMapper.readCode(productCursor),
                fsrarProductKindCode = AlcoholProductMapper.readFsrarProductKindCode(alcoholProductCursor)
                        ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::fsrarProductKindCode),
                vendorCode = ProductMapper.readVendorCode(productCursor),
                barcodes = ProductMapper.readBarcodes(productCursor),
                purchasePrice = ProductMapper.readPurchasePrice(productCursor),
                sellingPrice = ProductMapper.readSellingPrice(productCursor),
                vatRate = ProductMapper.readVatRate(productCursor)
                        ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::vatRate),
                quantity = ProductMapper.readQuantity(productCursor)
                        ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::quantity),
                unitOfMeasurement = UnitOfMeasurementMapper.read(productCursor),
                tareVolume = AlcoholProductMapper.readTareVolume(alcoholProductCursor)
                        ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::tareVolume),
                alcoholPercentage = AlcoholProductMapper.readAlcoholPercentage(alcoholProductCursor)
                        ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::alcoholPercentage),
                description = ProductMapper.readDescription(productCursor),
                allowedToSell = ProductMapper.readAllowedToSell(productCursor)
                        ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::allowedToSell)
        )

        alcoholProductCursor.close()

        return result
    }
}