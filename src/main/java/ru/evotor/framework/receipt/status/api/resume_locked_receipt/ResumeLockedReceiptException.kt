package ru.evotor.framework.receipt.status.api.resume_locked_receipt

class ResumeLockedReceiptException(
    val code: Int,
    message: String
) : Throwable(message) {
    companion object {
        /**
         * Неопознанная ошибка
         */
        const val CODE_UNIDENTIFIED_ERROR = -1

        /**
         * Сотрудник не авторизован
         */
        const val CODE_EMPLOYEE_NOT_AUTHORIZED = -2

        /**
         * В очереди нет заблокированного чека
         */
        const val CODE_NO_LOCKED_RECEIPT = -3

        /**
         * Чек уже запущен и находиться в активном состоянии, вызов resumeLockedReceipt не требуется
         */
        const val CODE_RECEIPT_ALREADY_RESUMED = -4

        /**
         * Произошла ошибка во время выполнения обработчика одного из методов:
         * [ru.evotor.framework.receipt.formation.api.SellApi.moveCurrentReceiptDraftToPaymentStage]
         * [ru.evotor.framework.receipt.formation.api.BuyApi.moveCurrentReceiptDraftToPaymentStage]
         * [ru.evotor.framework.receipt.formation.api.PaybackApi.moveCurrentReceiptDraftToPaymentStage]
         * Попробуйте вызвать метод ещё раз, либо обратитесь в поддержку
         */
        const val CODE_MOVE_CURRENT_RECEIPT_DRAFT_TO_PAYMENT_STAGE_FAILED = -5
    }
}
