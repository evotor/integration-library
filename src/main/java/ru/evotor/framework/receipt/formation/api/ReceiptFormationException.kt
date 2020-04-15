package ru.evotor.framework.receipt.formation.api

class ReceiptFormationException(
        val code: Int,
        message: String
): Exception(message) {
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
         * Неопознанная ошибка
         */
        const val CODE_UNIDENTIFIED_ERROR = -1
    }
}