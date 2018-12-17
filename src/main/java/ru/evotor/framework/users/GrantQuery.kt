package ru.evotor.framework.users

import android.content.Context
import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder

class GrantQuery(authenticatedUsersOnly: Boolean = false) : FilterBuilder<GrantQuery, GrantQuery.SortOrder, Grant?>(
        if (authenticatedUsersOnly) GrantsTable.URI_GRANTS_OF_AUTHENTICATED_USER else GrantsTable.URI) {
    override val currentQuery: GrantQuery
        get() = this

    @JvmField
    val title = addFieldFilter<String>(GrantsTable.ROW_TITLE)
    @JvmField
    val roleUuid = addFieldFilter<String>(GrantsTable.ROW_ROLE_UUID)

    class SortOrder : FilterBuilder.SortOrder<SortOrder>() {
        override val currentSortOrder: SortOrder
            get() = this

        @JvmField
        val title = addFieldSorter(GrantsTable.ROW_TITLE)
        @JvmField
        val roleUuid = addFieldSorter(GrantsTable.ROW_ROLE_UUID)

    }

    override fun getValue(context: Context, cursor: Cursor<Grant?>): Grant? {
        return UserMapper.createGrant(cursor)
    }

}
