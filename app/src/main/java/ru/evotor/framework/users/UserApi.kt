package ru.evotor.framework.users

import android.content.Context
import android.database.Cursor


object UserApi {

    /**
     * Получить данные всех пользователей.
     * @param context контекст приложения
     * @return список пользователей
     */
    @JvmStatic
    fun getAllUsers(context: Context): List<User>? {
        context.contentResolver
                .query(UsersTable.URI, null, null, null, null)
                ?.let { cursor ->
                    val users = ArrayList<User>()
                    try {
                        while (cursor.moveToNext()) {
                            users.add(createUser(cursor))
                        }
                        return users
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        cursor.close()
                    }
                }
        return null
    }

    /**
     * Получить данные авторизованного пользователя.
     * @param context контекст приложения
     * @return авторизованный пользователь или null, если пользователь не авторизован
     */
    @JvmStatic
    fun getAuthenticatedUser(context: Context): User? {
        context.contentResolver
                .query(UsersTable.URI_AUTHENTICATED, null, null, null, null)
                ?.let { cursor ->
                    try {
                        if (cursor.moveToFirst()) {
                            return createUser(cursor)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        cursor.close()
                    }
                }
        return null
    }

    /**
     * Получить список всех доступных прав.
     * @param context контекст приложения
     * @return список всех прав системы
     */
    @JvmStatic
    fun getAllGrants(context: Context): List<Grant>? {
        context.contentResolver
                .query(GrantsTable.URI, null, null, null, null)
                ?.let { cursor ->
                    val grants = ArrayList<Grant>()
                    try {
                        while (cursor.moveToNext()) {
                            grants.add(createGrant(cursor))
                        }
                        return grants
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        cursor.close()
                    }
                }
        return null
    }

    /**
     * Получить список прав авторизованного пользователя.
     * @param context контекст приложения
     * @return список прав авторизованного пользователя
     */
    @JvmStatic
    fun getGrantsOfAuthenticatedUser(context: Context): List<Grant>? {

        context.contentResolver
                .query(GrantsTable.URI_GRANTS_OF_AUTHENTICATED_USER, null, null, null, null)
                ?.let { cursor ->
                    val grants = ArrayList<Grant>()
                    try {
                        while (cursor.moveToNext()) {
                            grants.add(createGrant(cursor))
                        }
                        return grants
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        cursor.close()
                    }
                }
        return null
    }

    private fun createGrant(cursor: Cursor): Grant {
        return Grant(
                title = cursor.getString(cursor.getColumnIndex(GrantsTable.ROW_TITLE)),
                roleUuid = cursor.getString(cursor.getColumnIndex(GrantsTable.ROW_ROLE_UUID))
        )
    }

    private fun createUser(cursor: Cursor): User {
        return User(
                uuid = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_USER_UUID)),
                secondName = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_USER_SECOND_NAME)),
                firstName = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_USER_FIRST_NAME)),
                phone = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_USER_PHONE)),
                pin = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_USER_PIN)),
                roleUuid = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_ROLE_UUID)),
                roleTitle = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_ROLE_TITLE))
        )
    }
}