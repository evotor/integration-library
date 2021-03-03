package ru.evotor.framework.users

/**
 * Описывает данные сотрудника, который работает со смарт-терминалом.
 * Пользователь указывает данные сотрудника в Личном кабинете Эвотора.
 */
data class User(
        /**
         * Идентификатор сотрудника в формате `uuid4`.
         */
        val uuid: String,
        /**
         * Фамилия сотрудника.
         * Поле может быть пустым.
         */
        val secondName: String?,
        /**
         * Имя сотрудника.
         */
        val firstName: String?,
        /**
         * ИНН
         * Поле может быть пустым
         */
        val inn: String? = null,
        /**
         * Номер телефона сотрудника.
         * Поле может быть пустым.
         */
        val phone: String?,
        /**
         * Пин код от смарт-терминала.
         * Поле может быть пустым.
         */
        val pin: String?,
        /**
         * Идентификатор роли сотрудника в формате `uuid4`.
         * Роли создаёт пользователь в Личном кабинете Эвотора.
         * С помощью ролей, пользователь предоставляет сотрудникам доступ к различным функциям смарт-терминала.
         */
        val roleUuid: String,
        /**
         * Название роли, например, "Кассир".
         */
        val roleTitle: String,
        /**
         * Должность
         * Поле может быть пустым
         */
        val position: String? = null
) {
    @Deprecated(
            message = "Use constructor with inn and position parameters instead",
            level = DeprecationLevel.WARNING,
            replaceWith = ReplaceWith(
                    expression = "User(uuid: String, secondName: String?, firstName: String?, " +
                            "inn: String?, phone: String?, pin: String?, roleUuid: String, " +
                            "roleTitle: String, position: String?)"
            )
    )
    constructor(uuid: String,
                secondName: String?,
                firstName: String?,
                phone: String?,
                pin: String?,
                roleUuid: String,
                roleTitle: String) : this(
            uuid,
            secondName,
            firstName,
            null,
            phone,
            pin,
            roleUuid,
            roleTitle,
            null
    )

    @Deprecated(
            message = "Use constructor with inn and position parameters instead",
            level = DeprecationLevel.WARNING,
            replaceWith = ReplaceWith(
                    expression = "User(uuid: String, secondName: String?, firstName: String?, " +
                            "inn: String?, phone: String?, pin: String?, roleUuid: String, " +
                            "roleTitle: String, position: String?)"
            )
    )
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
            inn,
            phone,
            pin,
            roleUuid,
            roleTitle,
            null
    )
}