package ru.evotor.integrations.users

import android.net.Uri

object UsersTable {
    @JvmField val URI = Uri.withAppendedPath(UsersContentProviderContract.BASE_URI, "users")
    @JvmField val URI_AUTHENTICATED = Uri.withAppendedPath(URI, "authenticated")

    @JvmField val ROW_USER_UUID = "USER_UUID"
    @JvmField val ROW_USER_SECOND_NAME = "USER_SECOND_NAME"
    @JvmField val ROW_USER_FIRST_NAME = "USER_FIRST_NAME"
    @JvmField val ROW_USER_PHONE = "USER_PHONE"
    @JvmField val ROW_USER_PIN = "USER_PIN"
    @JvmField val ROW_ROLE_UUID = "ROLE_UUID"
    @JvmField val ROW_ROLE_TITLE = "ROLE_TITLE"
}
