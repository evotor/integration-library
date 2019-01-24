package ru.evotor.framework.inventory.product.category.entertainment.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.category.entertainment.Tobacco
import ru.evotor.framework.inventory.product.mapper.ProductMapper
import ru.evotor.framework.mapper.QuantityMapper

internal object TobaccoMapper {
    fun read(cursor: Cursor) = Tobacco(
            uuid = ProductMapper.readUuid(cursor)
                    ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::uuid),
            groupUuid = ProductMapper.readGroupUuid(cursor),
            name = ProductMapper.readName(cursor)
                    ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::name),
            code = ProductMapper.readCode(cursor),
            vendorCode = ProductMapper.readVendorCode(cursor),
            price = ProductMapper.readPrice(cursor),
            vatRate = ProductMapper.readVatRate(cursor)
                    ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::vatRate),
            quantity = QuantityMapper.read(cursor)
                    ?: throw IntegrationLibraryMappingException(Tobacco::class.java, Tobacco::quantity),
            description = ProductMapper.readDescription(cursor)
    )
}