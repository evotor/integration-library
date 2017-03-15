package ru.evotor.integrations.users

import android.net.Uri

object UsersTable {
    val URI = Uri.withAppendedPath(UsersContentProviderContract.BASE_URI, "users")
    val URI_AUTHENTICATED = Uri.withAppendedPath(URI, "authenticated")

    val ROW_USER_UUID = "USER_UUID"
    val ROW_USER_SECOND_NAME = "USER_SECOND_NAME"
    val ROW_USER_FIRST_NAME = "USER_FIRST_NAME"
    val ROW_USER_PHONE = "USER_PHONE"
    val ROW_USER_PIN = "USER_PIN"
    val ROW_ROLE_UUID = "ROLE_UUID"
    val ROW_ROLE_TITLE = "ROLE_TITLE"
}
