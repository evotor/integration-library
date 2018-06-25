package ru.evotor.framework.users

import ru.evotor.query.Cursor
import ru.evotor.query.FilterBuilder

class GrantQuery(authenticatedUsersOnly: Boolean = false) : FilterBuilder<GrantQuery, GrantQuery.SortOrder, Grant?>(
        if (authenticatedUsersOnly) GrantsTable.URI_GRANTS_OF_AUTHENTICATED_USER else GrantsTable.URI) {

    @JvmField
    val title = addFieldFilter<String>(GrantsTable.ROW_TITLE)
    @JvmField
    val roleUuid = addFieldFilter<String>(GrantsTable.ROW_ROLE_UUID)

    override val currentQuery: GrantQuery
        get() = this

    class SortOrder : FilterBuilder.SortOrder<SortOrder>() {

        @JvmField
        val title = addFieldSorter(GrantsTable.ROW_TITLE)
        @JvmField
        val roleUuid = addFieldSorter(GrantsTable.ROW_ROLE_UUID)

        override val currentSortOrder: SortOrder
            get() = this

    }

    override fun getValue(cursor: Cursor<Grant?>): Grant? {
        return UserMapper.createGrant(cursor)
    }

}
