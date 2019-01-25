package ru.evotor.framework.inventory.product.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.product.UnclassifiedService
import ru.evotor.framework.mapper.QuantityMapper

internal object UnclassifiedServiceMapper {
    fun read(cursor: Cursor) = UnclassifiedService(
            uuid = ProductMapper.readUuid(cursor)
                    ?: throw IntegrationLibraryMappingException(UnclassifiedService::class.java, UnclassifiedService::uuid),
            groupUuid = ProductMapper.readGroupUuid(cursor),
            name = ProductMapper.readName(cursor)
                    ?: throw IntegrationLibraryMappingException(UnclassifiedService::class.java, UnclassifiedService::name),
            code = ProductMapper.readCode(cursor),
            vendorCode = ProductMapper.readVendorCode(cursor),
            price = ProductMapper.readPrice(cursor),
            vatRate = ProductMapper.readVatRate(cursor)
                    ?: throw IntegrationLibraryMappingException(UnclassifiedService::class.java, UnclassifiedService::vatRate),
            quantity = ProductMapper.readQuantity(cursor)
                    ?: throw IntegrationLibraryMappingException(UnclassifiedService::class.java, UnclassifiedService::quantity),
            description = ProductMapper.readDescription(cursor)
    )
}