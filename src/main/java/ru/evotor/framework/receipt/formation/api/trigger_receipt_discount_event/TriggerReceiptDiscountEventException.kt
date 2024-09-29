package ru.evotor.framework.receipt.formation.api.trigger_receipt_discount_event

class TriggerReceiptDiscountEventException(
    val code: Int,
    message: String
) : Exception(message) {
    companion object {
        /**
         * ККМ в данный момент выполняет другую операцию
         */
        const val ERROR_CODE_KKM_IS_BUSY = -1

        /**
         * Нет авторизованного пользователя на терминале
         */
        const val ERROR_CODE_NO_AUTHENTICATED_USER = -2

        /**
         * У приложения нет необходимого разрешения (permission)
         */
        const val ERROR_CODE_NO_PERMISSION = -3

        /**
         *  Сервис с заданным componentName не может обработать событие ReceiptDiscountEvent или отсутствует
         */
        const val ERROR_CODE_WRONG_COMPONENT_NAME = -4

        /**
         * Нет открытого чека чека для вызова сервиса скидок
         */
        const val ERROR_CODE_NO_OPENED_RECEIPT = -5

        /**
         * Сервис вернул ошибку в ответ на ReceiptDiscountEvent
         */
        const val ERROR_CODE_SERVICE_RETURNED_ERROR = -6

        /**
         * Для текущего чека недоступен вызов события ReceiptDiscountEvent
         */
        const val ERROR_CODE_DISCOUNT_APP_UNAVAILABLE_FOR_RECEIPT = -7

        /**
         * В данный момент для текущего чека вызов события ReceiptDiscountEvent запрещен
         */
        const val ERROR_CODE_DISCOUNT_APP_FORBIDDEN_FOR_RECEIPT = -8
    }
}
