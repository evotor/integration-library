package ru.evotor.integrations.users

import android.net.Uri

object GrantsTable {
    @JvmField val URI = Uri.withAppendedPath(UsersContentProviderContract.BASE_URI, "grants")
    @JvmField val URI_GRANTS_OF_AUTHENTICATED_USER = Uri.withAppendedPath(URI, "authenticated")

    @JvmField val ROW_ROLE_UUID = "ROLE_UUID"
    @JvmField val ROW_TITLE = "TITLE"
}
