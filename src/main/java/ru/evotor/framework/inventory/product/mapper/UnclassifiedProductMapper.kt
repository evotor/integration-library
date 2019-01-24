package ru.evotor.framework.inventory.product.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.UnclassifiedProduct
import ru.evotor.framework.mapper.QuantityMapper

internal object UnclassifiedProductMapper {
    fun read(cursor: Cursor) = UnclassifiedProduct(
            uuid = ProductMapper.readUuid(cursor)
                    ?: throw IntegrationLibraryMappingException(UnclassifiedProduct::class.java, UnclassifiedProduct::uuid),
            groupUuid = ProductMapper.readGroupUuid(cursor),
            name = ProductMapper.readName(cursor)
                    ?: throw IntegrationLibraryMappingException(UnclassifiedProduct::class.java, UnclassifiedProduct::name),
            code = ProductMapper.readCode(cursor),
            vendorCode = ProductMapper.readVendorCode(cursor),
            price = ProductMapper.readPrice(cursor),
            vatRate = ProductMapper.readVatRate(cursor)
                    ?: throw IntegrationLibraryMappingException(UnclassifiedProduct::class.java, UnclassifiedProduct::vatRate),
            quantity = QuantityMapper.read(cursor)
                    ?: throw IntegrationLibraryMappingException(UnclassifiedProduct::class.java, UnclassifiedProduct::quantity),
            description = ProductMapper.readDescription(cursor)
    )
}