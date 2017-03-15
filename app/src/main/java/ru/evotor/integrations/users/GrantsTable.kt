package ru.evotor.integrations.users

import android.net.Uri

object GrantsTable {
    val URI = Uri.withAppendedPath(UsersContentProviderContract.BASE_URI, "grants")
    val URI_GRANTS_OF_AUTHENTICATED_USER = Uri.withAppendedPath(URI, "authenticated")

    val ROW_ROLE_UUID = "ROLE_UUID"
    val ROW_TITLE = "TITLE"
}
