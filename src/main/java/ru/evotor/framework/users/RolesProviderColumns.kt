package ru.evotor.framework.users

import android.provider.BaseColumns

interface RolesProviderColumns : BaseColumns {
    companion object {
        const val UUID = "UUID"
        const val roleTitle = "roleTitle"
        const val status = "status"
    }
}
