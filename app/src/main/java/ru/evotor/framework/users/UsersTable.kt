package ru.evotor.framework.users

import android.net.Uri

object UsersTable {
    @JvmField val URI = Uri.withAppendedPath(UsersContentProviderContract.BASE_URI, "users")
    @JvmField val URI_AUTHENTICATED = Uri.withAppendedPath(URI, "authenticated")

    const val ROW_USER_UUID = "USER_UUID"
    const val ROW_USER_SECOND_NAME = "USER_SECOND_NAME"
    const val ROW_USER_FIRST_NAME = "USER_FIRST_NAME"
    const val ROW_USER_PHONE = "USER_PHONE"
    const val ROW_USER_PIN = "USER_PIN"
    const val ROW_ROLE_UUID = "ROLE_UUID"
    const val ROW_ROLE_TITLE = "ROLE_TITLE"
}
