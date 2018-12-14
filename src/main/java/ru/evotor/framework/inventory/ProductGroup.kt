package ru.evotor.framework.inventory

import android.net.Uri
import ru.evotor.framework.inventory.mapper.ProductGroupMapper
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.framework.inventory.provider.ProductGroupContract
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.util.*

data class ProductGroup internal constructor(
        val uuid: UUID,
        val parentGroupUuid: UUID?,
        val name: String
) {
    class Query : FilterBuilder<Query, Query.SortOrder, ProductGroup>(
            Uri.withAppendedPath(InventoryContract.BASE_URI, ProductGroupContract.PATH)
    ) {
        val uuid = addFieldFilter<UUID>(ProductGroupContract.COLUMN_UUID)
        val parentGroupUuid = addFieldFilter<UUID?>(ProductGroupContract.COLUMN_PARENT_GROUP_UUID)
        val name = addFieldFilter<String>(ProductGroupContract.COLUMN_NAME)

        class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
            val uuid = addFieldSorter(ProductGroupContract.COLUMN_UUID)
            val parentGroupUuid = addFieldSorter(ProductGroupContract.COLUMN_PARENT_GROUP_UUID)
            val name = addFieldSorter(ProductGroupContract.COLUMN_NAME)
        }

        override fun getValue(cursor: Cursor<ProductGroup>): ProductGroup =
                ProductGroupMapper.read(cursor)
    }
}