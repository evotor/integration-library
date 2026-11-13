package ru.evotor.framework.receipt.status.api.ensure_receipt_processing

class EnsureReceiptProcessingException(
    val code: Int,
    message: String
) : Throwable(message) {
    companion object {
        /**
         * Неопознанная ошибка
         */
        const val CODE_UNIDENTIFIED_ERROR = -1

        /**
         * Некорректные входные параметры
         */
        const val CODE_INVALID_INPUT = -2

        /**
         * Чек с переданным receiptUuid не найден
         */
        const val CODE_RECEIPT_NOT_FOUND = -3

        /**
         * Сотрудник не авторизован
         */
        const val CODE_EMPLOYEE_NOT_AUTHORIZED = -4
    }
}
