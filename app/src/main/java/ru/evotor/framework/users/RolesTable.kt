package ru.evotor.framework.users

import android.net.Uri

object RolesTable {
    @JvmField val URI = Uri.withAppendedPath(UsersContentProviderContract.BASE_URI, "roles")

    const val ROW_UUID = "UUID"
    const val ROW_TITLE = "TITLE"
}
