package ru.evotor.framework.kkt.api

import java.lang.Exception

/**
 * Исключение, возникающее при попытке зарегистрировать документ в кассе
 */
class DocumentRegistrationException(
        val code: Int,
        message: String
) : Exception(message) {
    companion object {
        /**
         * Некорректные входные данные
         */
        const val CODE_INVALID_INPUT_DATA = -2

        /**
         * Сотрудник не авторизован
         */
        const val CODE_EMPLOYEE_NOT_AUTHORIZED = -3

        /**
         * Касса выполняет другую операцию
         */
        const val CODE_KKT_IS_BUSY = -4

        /**
         * Кассовая смена превысила 24 часа. Необходимо закрыть смену.
         */
        const val CODE_KKT_SESSION_TIME_EXPIRED = -3822

        /**
         * Исчерпан ресурс КС (криптопроцессора) ФН. Необходимо заменить ФН.
         */
        const val CODE_FISCAL_STORAGE_KS_RESOURCE_EXHAUSTED = -3978

        /**
         * ФН переполнен. Необходимо отправить документы в ОФД.
         */
        const val CODE_FISCAL_STORAGE_IS_FULL = -3979

        /**
         * Архив ФН переполнен. Необходимо заменить ФН.
         */
        const val CODE_FISCAL_STORAGE_ARCHIVE_IS_FULL = -3916

        /**
         * Время в кассе сильно разнится со временем в планшете. Необходима синхронизация времени.
         */
        const val CODE_KKT_DATETIME_IS_DIFFERENT_FROM_TABLET_DATETIME = -5

        /**
         * Неопознанная ошибка
         */
        const val CODE_UNIDENTIFIED_ERROR = -1
    }
}