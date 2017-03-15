package ru.evotor.integrations.users

import android.net.Uri

object RolesTable {
    val URI = Uri.withAppendedPath(UsersContentProviderContract.BASE_URI, "roles")

    val ROW_UUID = "UUID"
    val ROW_TITLE = "TITLE"
}
