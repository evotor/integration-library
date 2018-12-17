package ru.evotor.framework.inventory

import android.content.Context
import android.net.Uri
import ru.evotor.framework.inventory.mapper.ProductGroupMapper
import ru.evotor.framework.inventory.provider.InventoryContract
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder
import java.util.*

data class ProductGroup internal constructor(
        val uuid: UUID,
        val parentGroupUuid: UUID?,
        val name: String
) {
    class Query : FilterBuilder<Query, Query.SortOrder, ProductGroup>(
            Uri.withAppendedPath(InventoryContract.BASE_URI, InventoryContract.PATH_PRODUCT_GROUPS)
    ) {
        override val currentQuery: Query
            get() = this
        val uuid = addFieldFilter<UUID>(InventoryContract.ProductGroupColumns.UUID)
        val parentGroupUuid = addFieldFilter<UUID?>(InventoryContract.ProductGroupColumns.PARENT_GROUP_UUID)
        val name = addFieldFilter<String>(InventoryContract.ProductGroupColumns.NAME)

        class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
            override val currentSortOrder: SortOrder
                get() = this
            val uuid = addFieldSorter(InventoryContract.ProductGroupColumns.UUID)
            val parentGroupUuid = addFieldSorter(InventoryContract.ProductGroupColumns.PARENT_GROUP_UUID)
            val name = addFieldSorter(InventoryContract.ProductGroupColumns.NAME)
        }

        override fun getValue(context: Context, cursor: Cursor<ProductGroup>): ProductGroup =
                ProductGroupMapper.read(cursor)
    }
}