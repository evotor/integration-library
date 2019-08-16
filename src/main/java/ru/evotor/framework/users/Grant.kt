package ru.evotor.framework.users

/**
 * Описывает права доступа к функциям смарт-терминала.
 *
 * @see ["Права приложения"](https://developer.evotor.ru/docs/doc_app_grants.html)
 */
data class Grant(
        /**
         * Название права.
         */
        val title: String,
        /**
         * Идентификатор роли, которой соответствует право.
         */
        val roleUuid: String
)