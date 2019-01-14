package ru.evotor.framework.inventory.product.category.entertainment.mapper

import android.content.Context
import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.category.entertainment.WeakAlcohol
import ru.evotor.framework.inventory.product.extension.mapper.AlcoholProductMapper
import ru.evotor.framework.inventory.product.mapper.ProductMapper
import ru.evotor.framework.mapper.QuantityMapper
import java.util.*

internal object WeakAlcoholMapper {
    fun read(context: Context, productCursor: Cursor, needToLoadExtensions: Boolean): WeakAlcohol {
        val uuid = ProductMapper.readUuid(productCursor)
                ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::uuid)

        val alcoholProductCursor =
                if (needToLoadExtensions)
                    AlcoholProductMapper.loadExtension(context, uuid)
                            ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java)
                else
                    null

        alcoholProductCursor?.moveToFirst()

        val result = read(uuid, productCursor, alcoholProductCursor)

        alcoholProductCursor?.close()

        return result
    }

    private fun read(uuid: UUID, productCursor: Cursor, alcoholProductCursor: Cursor?) = WeakAlcohol(
            uuid = uuid,
            groupUuid = ProductMapper.readGroupUuid(productCursor),
            name = ProductMapper.readName(productCursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::name),
            code = ProductMapper.readCode(productCursor),
            fsrarProductKindCode = AlcoholProductMapper.readFsrarProductKindCode(alcoholProductCursor
                    ?: productCursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::fsrarProductKindCode),
            vendorCode = ProductMapper.readVendorCode(productCursor),
            price = ProductMapper.readSellingPrice(productCursor),
            vatRate = ProductMapper.readVatRate(productCursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::vatRate),
            quantity = QuantityMapper.read(productCursor),
            tareVolume = AlcoholProductMapper.readTareVolume(alcoholProductCursor
                    ?: productCursor)
                    ?: throw IntegrationLibraryMappingException(WeakAlcohol::class.java, WeakAlcohol::tareVolume),
            alcoholPercentage = AlcoholProductMapper.readAlcoholPercentage(alcoholProductCursor
                    ?: productCursor),
            description = ProductMapper.readDescription(productCursor)
    )
}