package ru.evotor.framework.inventory.mapper

import android.database.Cursor
import ru.evotor.framework.core.IntegrationLibraryMappingException
import ru.evotor.framework.inventory.ProductGroup
import ru.evotor.framework.inventory.provider.ProductGroupContract
import ru.evotor.framework.safeGetString
import java.util.*

internal object ProductGroupMapper {
    fun read(cursor: Cursor) = ProductGroup(
            cursor.safeGetString(ProductGroupContract.COLUMN_UUID)?.let { UUID.fromString(it) }
                    ?: throw IntegrationLibraryMappingException(ProductGroup::class.java, ProductGroup::uuid),
            cursor.safeGetString(ProductGroupContract.COLUMN_PARENT_GROUP_UUID)?.let { UUID.fromString(it) },
            cursor.safeGetString(ProductGroupContract.COLUMN_NAME)
                    ?: throw IntegrationLibraryMappingException(ProductGroup::class.java, ProductGroup::name)
    )
}