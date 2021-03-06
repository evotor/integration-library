package ru.evotor.framework.users

import android.content.Context
import androidx.annotation.WorkerThread

/**
 * Интерфейс для получения данных сотрудников, работающих со смарт-терминалом.
 */
@WorkerThread
object UserApi {

    /**
     * Возвращает данные всех сотрудников, которые могут работать со смарт-терминалом.
     * @param context контекст приложения.
     * @return список [сотрудников][ru.evotor.framework.users.User]
     */
    @JvmStatic
    fun getAllUsers(context: Context): List<User>? {
        context.contentResolver
                .query(UsersTable.URI, null, null, null, null)
                ?.use { cursor ->
                    try {
                        val users = ArrayList<User>()
                        while (cursor.moveToNext()) {
                            users.add(UserMapper.createUser(cursor))
                        }
                        return users
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        return null
    }

    /**
     * Возвращает данные авторизованного сотрудника.
     * @param context контекст приложения.
     * @return данные авторизованного [сотрудника][ru.evotor.framework.users.User] или `null`,
     * если сотрудник не авторизован.
     */
    @JvmStatic
    fun getAuthenticatedUser(context: Context): User? {
        context.contentResolver
                .query(UsersTable.URI_AUTHENTICATED, null, null, null, null)
                ?.use { cursor ->
                    try {
                        if (cursor.moveToFirst()) {
                            return UserMapper.createUser(cursor)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        return null
    }

    /**
     * Возвращает список всех доступкных прав с идентификаторами соответствующих ролей.
     * @param context контекст приложения.
     * @return список всех [прав][Grant] смарт-терминала.
     */
    @JvmStatic
    fun getAllGrants(context: Context): List<Grant>? {
        context.contentResolver
                .query(GrantsTable.URI, null, null, null, null)
                ?.use { cursor ->
                    try {
                        val grants = ArrayList<Grant>()
                        while (cursor.moveToNext()) {
                            grants.add(UserMapper.createGrant(cursor))
                        }
                        return grants
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        return null
    }

    /**
     * Возвращает список прав авторизованного пользователя.
     * @param context контекст приложения.
     * @return список [прав][Grant] авторизованного пользователя.
     */
    @JvmStatic
    fun getGrantsOfAuthenticatedUser(context: Context): List<Grant>? {
        context.contentResolver
                .query(GrantsTable.URI_GRANTS_OF_AUTHENTICATED_USER, null, null, null, null)
                ?.use { cursor ->
                    try {
                        val grants = ArrayList<Grant>()
                        while (cursor.moveToNext()) {
                            grants.add(UserMapper.createGrant(cursor))
                        }
                        return grants
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        return null
    }
}