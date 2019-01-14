package ru.evotor.framework.inventory.product.category.entertainment.mapper

import android.content.Context
import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.category.entertainment.StrongAlcohol
import ru.evotor.framework.inventory.product.extension.mapper.AlcoholProductMapper
import ru.evotor.framework.inventory.product.mapper.ProductMapper
import ru.evotor.framework.mapper.QuantityMapper
import java.util.*

internal object StrongAlcoholMapper {
    fun read(context: Context, productCursor: Cursor, needToLoadExtensions: Boolean): StrongAlcohol {
        val uuid = ProductMapper.readUuid(productCursor)
                ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::uuid)

        val alcoholProductCursor =
                if (needToLoadExtensions)
                    AlcoholProductMapper.loadExtension(context, uuid)
                            ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java)
                else
                    null

        alcoholProductCursor?.moveToFirst()

        val result = read(uuid, productCursor, alcoholProductCursor)

        alcoholProductCursor?.close()

        return result
    }

    private fun read(uuid: UUID, productCursor: Cursor, alcoholProductCursor: Cursor?) = StrongAlcohol(
            uuid = uuid,
            groupUuid = ProductMapper.readGroupUuid(productCursor),
            name = ProductMapper.readName(productCursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::name),
            code = ProductMapper.readCode(productCursor),
            fsrarProductKindCode = AlcoholProductMapper.readFsrarProductKindCode(alcoholProductCursor
                    ?: productCursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::fsrarProductKindCode),
            vendorCode = ProductMapper.readVendorCode(productCursor),
            price = ProductMapper.readSellingPrice(productCursor),
            vatRate = ProductMapper.readVatRate(productCursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::vatRate),
            quantity = QuantityMapper.read(productCursor),
            tareVolume = AlcoholProductMapper.readTareVolume(alcoholProductCursor
                    ?: productCursor)
                    ?: throw IntegrationLibraryMappingException(StrongAlcohol::class.java, StrongAlcohol::tareVolume),
            alcoholPercentage = AlcoholProductMapper.readAlcoholPercentage(alcoholProductCursor
                    ?: productCursor),
            description = ProductMapper.readDescription(productCursor)
    )
}