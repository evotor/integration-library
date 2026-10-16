package ru.evotor.framework.receipt.status.api.resume_receipt_processing

class ResumeReceiptProcessingException(
    val code: Int,
    message: String
) : Throwable(message) {
    companion object {
        /**
         * Неопознанная ошибка (receiptUuid не присутствует в переданном [ru.evotor.framework.receipt.status.event.ResumeReceiptProcessingRequestedEvent])
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

        /**
         * В очереди нет обрабатываемого чека
         */
        const val CODE_NO_RECEIPT_IN_PROCESSING = -5

        /**
         * Чек уже запущен и находиться в активном состоянии,
         * вызов [ru.evotor.framework.receipt.ReceiptApi.resumeReceiptProcessing] не требуется
         */
        const val CODE_RECEIPT_ALREADY_PROCESSING = -6

        /**
         * Чек уже закрыт, продолжение невозможно
         */
        const val CODE_RECEIPT_IS_CLOSED = -7

        /**
         * Произошла ошибка во время выполнения обработчика одного из методов:
         * [ru.evotor.framework.receipt.formation.api.SellApi.moveCurrentReceiptDraftToPaymentStage]
         * [ru.evotor.framework.receipt.formation.api.BuyApi.moveCurrentReceiptDraftToPaymentStage]
         * [ru.evotor.framework.receipt.formation.api.PaybackApi.moveCurrentReceiptDraftToPaymentStage]
         * Попробуйте вызвать метод ещё раз, либо обратитесь в поддержку
         */
        const val CODE_MOVE_CURRENT_RECEIPT_DRAFT_TO_PAYMENT_STAGE_FAILED = -8
    }
}
