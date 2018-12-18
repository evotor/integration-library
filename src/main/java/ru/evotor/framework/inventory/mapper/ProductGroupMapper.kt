package ru.evotor.framework.inventory.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.ProductGroup
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.core.safeGetString
import java.util.*

internal object ProductGroupMapper {
    fun read(cursor: Cursor) = ProductGroup(
            uuid = cursor.safeGetString(InventoryContract.ProductGroupColumns.UUID)?.let { UUID.fromString(it) }
                    ?: throw IntegrationLibraryMappingException(ProductGroup::class.java, ProductGroup::uuid),
            parentGroupUuid = cursor.safeGetString(InventoryContract.ProductGroupColumns.PARENT_GROUP_UUID)?.let { UUID.fromString(it) },
            name = cursor.safeGetString(InventoryContract.ProductGroupColumns.NAME)
                    ?: throw IntegrationLibraryMappingException(ProductGroup::class.java, ProductGroup::name)
    )
}