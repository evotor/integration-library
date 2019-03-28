package ru.evotor.framework.core.action.command.pay_and_print_receipt_command

import android.os.Bundle
import ru.evotor.IBundlable

class PayAndPrintSellReceiptCommandResult(
        val receiptUuid: String
) : IBundlable {
    override fun toBundle(): Bundle {
        return Bundle().apply { putString(KEY_RECEIPT_UUID, receiptUuid) }
    }

    companion object {
        private val KEY_RECEIPT_UUID = "receiptUuid"

        /**
         * У приложения нет необходимого разрешения (permission)
         */
        const val ERROR_CODE_NO_PERMISSION = -1
        /**
         * ККМ в данный момент выполняет другую операцию
         */
        const val ERROR_CODE_KKM_IS_BUSY = -2
        /**
         * Нет авторизованного пользователя на терминале
         */
        const val ERROR_CODE_NO_AUTHENTICATED_USER = -3
        /**
         * Уникальный идентификатор чека, переданный в команде, не соответсвует индентификатору
         * текущего чека
         */
        const val ERROR_INCORRECT_RECEIPT_UUID_IN_COMMAND = -4
        /**
         * В переданной коменте отсутсвуют данные и платежного компонента, и комбооплаты
         */
        const val ERROR_NO_PERFORMER_OR_DELEGATOR = -5
        /**
         * Некорректные данные платежного компонента, переданные в команде
         */
        const val ERROR_INVALID_DATA_IN_PERFORMER = -6
        /**
         * Некорректные данные компонента комбооплаты, переданные в команде
         */
        const val ERROR_INVALID_DATA_IN_DELEGATOR = -7

        fun create(bundle: Bundle?): PayAndPrintSellReceiptCommandResult? {
            return bundle?.getString(KEY_RECEIPT_UUID)?.let { PayAndPrintSellReceiptCommandResult(it) }
        }
    }
}