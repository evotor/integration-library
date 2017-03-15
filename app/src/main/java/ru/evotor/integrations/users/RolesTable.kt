package ru.evotor.integrations.users

import android.net.Uri

object RolesTable {
    @JvmField val URI = Uri.withAppendedPath(UsersContentProviderContract.BASE_URI, "roles")

    @JvmField val ROW_UUID = "UUID"
    @JvmField val ROW_TITLE = "TITLE"
}
