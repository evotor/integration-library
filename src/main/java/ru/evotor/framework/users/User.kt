package ru.evotor.framework.users

/**
 * Пользователь
 */
data class User(
        val uuid: String,
        val secondName: String?,
        val firstName: String?,
        val phone: String?,
        val pin: String?,
        val roleUuid: String,
        val roleTitle: String
)