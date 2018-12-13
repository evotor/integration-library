package ru.evotor.framework.inventory.product.mapper

import android.content.Context
import android.database.Cursor
import android.net.Uri
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.Tobacco
import ru.evotor.framework.inventory.product.extension.mapper.ExcisableProductMapper
import ru.evotor.framework.inventory.product.extension.provider.ExcisableProductContract

internal object TobaccoMapper {
    fun read(context: Context, productCursor: Cursor): Tobacco {
        val uuid = ProductMapper.readUuid(productCursor)
                ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::uuid)

        val excisableProductCursor = context.contentResolver.query(
                Uri.withAppendedPath(ExcisableProductContract.URI, uuid.toString()),
                null,
                null,
                null,
                null
        ) ?: throw IntegrationLibraryMappingException(Tobacco::class.java)
        excisableProductCursor.moveToFirst()

        val result = Tobacco(
                uuid = uuid,
                groupUuid = ProductMapper.readGroupUuid(productCursor),
                name = ProductMapper.readName(productCursor)
                        ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::name),
                code = ProductMapper.readCode(productCursor),
                vendorCode = ProductMapper.readVendorCode(productCursor),
                barcodes = ProductMapper.readBarcodes(productCursor),
                mark = ExcisableProductMapper.readMark(excisableProductCursor)
                        ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::mark),
                purchasePrice = ProductMapper.readPurchasePrice(productCursor),
                sellingPrice = ProductMapper.readSellingPrice(productCursor),
                vatRate = ProductMapper.readVatRate(productCursor)
                        ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::vatRate),
                quantity = ProductMapper.readQuantity(productCursor)
                        ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::quantity),
                unitOfMeasurement = UnitOfMeasurementMapper.read(productCursor),
                description = ProductMapper.readDescription(productCursor),
                allowedToSell = ProductMapper.readAllowedToSell(productCursor)
                        ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::allowedToSell)
        )

        excisableProductCursor.close()

        return result
    }
}