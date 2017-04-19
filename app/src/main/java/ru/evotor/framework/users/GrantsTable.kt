package ru.evotor.framework.users

import android.net.Uri

object GrantsTable {
    @JvmField val URI = Uri.withAppendedPath(UsersContentProviderContract.BASE_URI, "grants")
    @JvmField val URI_GRANTS_OF_AUTHENTICATED_USER = Uri.withAppendedPath(URI, "authenticated")

    const val ROW_ROLE_UUID = "ROLE_UUID"
    const val ROW_TITLE = "TITLE"
}
