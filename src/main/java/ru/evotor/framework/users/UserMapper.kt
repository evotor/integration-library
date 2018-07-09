package ru.evotor.framework.users

import android.database.Cursor
import ru.evotor.framework.optString

internal object UserMapper {
    fun createGrant(cursor: Cursor): Grant {
        return Grant(
                title = cursor.getString(cursor.getColumnIndex(GrantsTable.ROW_TITLE)),
                roleUuid = cursor.getString(cursor.getColumnIndex(GrantsTable.ROW_ROLE_UUID))
        )
    }

    fun createUser(cursor: Cursor): User {
        return User(
                uuid = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_USER_UUID)),
                secondName = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_USER_SECOND_NAME)),
                firstName = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_USER_FIRST_NAME)),
                inn = cursor.optString(cursor.getColumnIndex(UsersTable.ROW_USER_INN)),
                phone = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_USER_PHONE)),
                pin = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_USER_PIN)),
                roleUuid = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_ROLE_UUID)),
                roleTitle = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_ROLE_TITLE))
        )
    }
}