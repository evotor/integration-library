package ru.evotor.framework.users

import android.provider.BaseColumns

interface GrantsProviderColumns : BaseColumns {
    companion object {
        const val roleUUID = "roleUUID"
        const val grantTitle = "grantTitle"
    }
}
