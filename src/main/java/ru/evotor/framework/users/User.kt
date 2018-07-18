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
) {
    var inn: String? = null

    constructor(uuid: String,
                secondName: String?,
                firstName: String?,
                inn: String?,
                phone: String?,
                pin: String?,
                roleUuid: String,
                roleTitle: String) : this(
            uuid,
            secondName,
            firstName,
            phone,
            pin,
            roleUuid,
            roleTitle
    ) {
        this.inn = inn
    }
}