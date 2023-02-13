package ru.evotor.framework.users

import android.provider.BaseColumns

interface UsersProviderColumns : BaseColumns {
    companion object {
        const val userUUID = "userUUID"
        const val surname = "surname"
        const val name = "name"
        const val inn = "inn"
        const val phone = "phone"
        const val PIN = "PIN"
        const val roleUUID = "roleUUID"
        const val position = "position"
        const val status = "status"
        const val isAuthenticated = "isAuthenticated"
    }
}
